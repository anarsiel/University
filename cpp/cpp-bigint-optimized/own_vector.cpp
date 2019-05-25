#include <cstring>
#include "own_vector.h"

own_vector::own_vector() {
    is_big = false;
    memset(union_data.small_data, 0, LITTLE_ARRAY_SZ * sizeof(uint32_t));
    sz = 0;
}

own_vector::~own_vector() {
    if (is_big) {
        union_data.big_data.~big_number();
    }
}

uint32_t &own_vector::operator[](size_t i) {
    return begin()[i];
}

uint32_t const &own_vector::operator[](size_t i) const {
    return begin()[i];
}

own_vector::own_vector(size_t n) {
    sz = n;
    if (sz > LITTLE_ARRAY_SZ) {
        is_big = true;
        auto tmp = new uint32_t[n];

        new(&union_data.big_data) big_number(sz, tmp);
    } else {
        is_big = false;
    }
    memset(begin(), 0, sz * sizeof(uint32_t));

}

uint32_t *own_vector::begin() const noexcept {
    if (is_big) {
        return union_data.big_data.sharedPtr.get();
    } else {
        return const_cast<uint32_t *>(union_data.small_data);
    }
}

void own_vector::pop_back() {
    sz--;
    if (sz <= LITTLE_ARRAY_SZ && is_big) {
        auto tmp = new uint32_t[sz];
        memcpy(tmp, begin(), sz * sizeof(uint32_t));
        union_data.big_data.~big_number();
        memcpy(union_data.small_data, tmp, sz * sizeof(uint32_t));
        delete[] tmp;
        is_big = false;
    }
}

own_vector::own_vector(own_vector const &other) noexcept {
    sz = other.size();
    is_big = other.is_big;
    if (is_big) {
        new(&union_data.big_data) big_number(other.union_data.big_data);
    } else {
        memcpy(union_data.small_data, other.union_data.small_data, sz * sizeof(uint32_t));
    }
}

own_vector &own_vector::operator=(own_vector other) noexcept {
    swap(other);
    return *this;
}

uint32_t &own_vector::back() {
    make_copy();
    return begin()[sz - 1];
}


void own_vector::swap(own_vector &other) {

    if (is_big && other.is_big) {
        std::swap(union_data.big_data, other.union_data.big_data);
    } else if (!is_big && !other.is_big) {
        std::swap(union_data.small_data, other.union_data.small_data);
    } else if (is_big && !other.is_big) {
        swap_big_small(other.union_data);
    } else if (!is_big && other.is_big) {
        other.swap_big_small(union_data);
    }

    std::swap(sz, other.sz);
    std::swap(is_big, other.is_big);
}


void own_vector::big_number::ensure_capacity(size_t size) {
    if (size == capacity) {
        capacity *= 2;
        auto tmp = new uint32_t[capacity];
        memcpy(tmp, sharedPtr.get(), size * sizeof(uint32_t));
        sharedPtr.reset(tmp, std::default_delete<uint32_t[]>());
    }
}

own_vector::big_number::big_number(size_t size, uint32_t *array) : capacity(size), sharedPtr(array, std::default_delete<uint32_t[]>()) {}


void own_vector::push_back(uint32_t val) {
    if (!is_big && sz == LITTLE_ARRAY_SZ) {
        auto tmp = new uint32_t[sz + 1];
        memcpy(tmp, begin(), sz * sizeof(uint32_t));
        tmp[sz++] = val;
        new(&union_data.big_data) big_number(sz, tmp);
        is_big = true;
    } else {
        if (is_big) {
            union_data.big_data.ensure_capacity(sz);
        }
        begin()[sz++] = val;
    }
}

bool operator==(own_vector const &a, own_vector const &b) {
    if (a.size() == b.size()) {
        for (size_t i = 0; i < a.size(); ++i) {
            if (a.begin()[i] != b.begin()[i]) {
                return false;
            }
        }
        return true;
    }
    return false;
}

void own_vector::make_copy() {
    if (is_big && !union_data.big_data.sharedPtr.unique()) {
        auto tmp = new uint32_t[union_data.big_data.capacity];
        memcpy(tmp, begin(), union_data.big_data.capacity * sizeof(uint32_t));
        union_data.big_data.sharedPtr.reset(tmp, std::default_delete<uint32_t[]>());
    }
}

void own_vector::swap_big_small(own_vector::combined_data &b) {
    uint32_t temp[LITTLE_ARRAY_SZ];
    memcpy(temp, b.small_data, LITTLE_ARRAY_SZ * sizeof(uint32_t));
    new(&b.big_data) big_number(union_data.big_data);
    union_data.big_data.~big_number();
    memcpy(union_data.small_data, temp, LITTLE_ARRAY_SZ * sizeof(uint32_t));
}