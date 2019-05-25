//
// Created by blagoi on 12.04.19.
//

#include "big_integer.h"
#include "own_vector.h"

big_integer::big_integer() {
    ownVector.make_copy();
    ownVector.push_back(0);
    sign = false;
}

big_integer::big_integer(big_integer const &other) {
    this->sign = other.sign;
    this->ownVector = other.ownVector;
    normalize();
}

big_integer::big_integer(uint32_t a) {
    this->sign = 0;
    ownVector.make_copy();
    ownVector.push_back(a);
}

big_integer::big_integer(int a) {
    this->sign = a < 0;
    ownVector.make_copy();
    ownVector.push_back(a);
}

big_integer::big_integer(std::string const &str) {
    bool start = !(str.empty()) && str[0] == '-';
    ownVector.make_copy();
    ownVector.push_back(0);
    sign = false;
    for (size_t i = start; i < str.size(); ++i) {
        (*this) *= 10;
        (*this) += (str[i] - '0');
    }
    if (start) *this = -*this;
    normalize();
}

big_integer::~big_integer() = default;

big_integer &big_integer::operator=(big_integer const &other) {
    ownVector = other.ownVector;
    sign = other.sign;
    return *this;
}


big_integer &big_integer::operator+=(big_integer const &other) {
    uint64_t carry = 0;
    own_vector res(std::max(other.length(), length()) + 2);
    res.make_copy();
    for (size_t i = 0; i < res.size(); ++i) {
        uint64_t sum = carry + get_digit(i) + other.get_digit(i);
        res[i] = sum;
        carry = sum >> BASE_SIZE;
    }
    ownVector.swap(res);
    sign = (ownVector.back() >> (BASE_SIZE - 1)) == 1;
    normalize();
    return *this;
}

big_integer &big_integer::operator+=(int other) {
    uint32_t a = other;
    uint64_t carry = 0;
    own_vector res(length() + 2);
    res.make_copy();
    for (size_t i = 0; i < res.size(); ++i) {
        uint64_t sum = carry + get_digit(i) + (i >= 1 ? (other < 0 ? MAX_ELEM : 0) : a);
        res[i] = sum;
        carry = sum >> BASE_SIZE;
    }
    ownVector.swap(res);
    sign = (ownVector.back() >> (BASE_SIZE - 1)) == 1;
    normalize();
    return *this;
}

big_integer &big_integer::operator-=(big_integer const &other) {
    return *this += (-other);
}


big_integer &big_integer::operator*=(big_integer const &other) {
    big_integer a(abs());
    big_integer b(other.abs());
    if (a == 0 || b == 0) {
        *this = 0;
        return *this;
    }
    own_vector res(a.length() + b.length() + 1);
    res.make_copy();
    for (size_t i = 0; i < a.length(); ++i) {
        uint32_t carry = 0;
        for (size_t j = 0; j < b.length(); ++j) {
            uint64_t sum = res[i + j] + static_cast<uint64_t>(a.ownVector[i]) * b.ownVector[j] + carry;
            res[i + j] = sum;
            carry = sum >> BASE_SIZE;
        }
        size_t x = b.length();
        while (carry != 0) {
            uint64_t sum = static_cast<uint64_t>(res[i + x]) + carry;
            res[i + x] = sum;
            carry = sum >> BASE_SIZE;
            x++;
        }
    }
    ownVector.swap(res);

    if (sign ^ other.sign) {
        sign = false;
        *this = -*this;
    } else {
        sign = false;
    }
    normalize();
    return *this;
}

big_integer &big_integer::operator/=(uint32_t a) {
    uint64_t carry = 0;
    own_vector v1;
    v1.make_copy();
    for (size_t i = length() - 1; i < length(); --i) {
        uint64_t sum = ownVector[i] + (carry << BASE_SIZE);
        v1.push_back(sum / a);
        carry = sum % a;
    }
    std::reverse(v1.begin(), v1.begin() + v1.size());
    ownVector = v1;
    normalize();
    return *this;
}

big_integer &big_integer::operator/=(int val) {
    return (*this) /= big_integer(val);
}

big_integer &big_integer::operator/=(big_integer const &other) {
    big_integer a = abs();
    big_integer b = other.abs();
    if (a < b) {
        *this = 0;
        return *this;
    }
    if (b.length() == 1) {
        if (sign ^ other.sign) {
            return *this = -(a / (b.ownVector[0]));
        }
        return *this = a / (b.ownVector[0]);
    }
    big_integer mod(0);
    uint32_t f = (BASE / (b.ownVector.back() + static_cast<uint64_t> (1)));
    a *= f;
    b *= f;
    size_t n = a.length(), m = b.length();
    own_vector data(n - m + 1);
    mod = a >> ((n - m + 1) * BASE_SIZE);
    uint64_t top = b.ownVector.back();
    mod.ownVector.make_copy();
    data.make_copy();
    for (size_t i = n - m; i <= n - m; --i) {
        mod <<= BASE_SIZE;
        mod.ownVector[0] = a.ownVector[i];
        uint64_t mod_top = mod.ownVector.back();
        if (mod.length() > m) {
            mod_top <<= BASE_SIZE;
            mod_top += mod.ownVector[mod.length() - 2];
        }
        uint32_t guess = std::min(mod_top / top, BASE - 1);
        big_integer res_guess = guess * b;
        while (mod < res_guess) {
            guess--;
            res_guess -= b;
        }
        data[i] = guess;
        mod -= res_guess;
    }
    big_integer tmp(false, data);
    if (sign ^ other.sign) {
        tmp = -tmp;
    }
    swap(tmp);
    normalize();
    return *this;
}

big_integer &big_integer::operator&=(big_integer const &other) {
    ownVector.make_copy();
    for (size_t i = 0; i < length(); ++i) {
        ownVector[i] &= other.get_digit(i);
    }
    sign &= other.sign;
    normalize();
    return *this;
}

big_integer &big_integer::operator^=(big_integer const &other) {
    ownVector.make_copy();
    for (size_t i = 0; i < std::max(length(), other.length()); ++i) {
        if (i < length()) ownVector[i] ^= other.get_digit(i);
    }
    sign ^= other.sign;
    normalize();
    return *this;
}

big_integer &big_integer::operator|=(big_integer const &other) {
    ownVector.make_copy();
    for (size_t i = 0; i < std::max(length(), other.length()); ++i) {
        ownVector[i] |= other.get_digit(i);
    }
    sign |= other.sign;
    normalize();
    return *this;
}

big_integer &big_integer::operator<<=(int a) {
    if (a < 0) {
        return (*this) >>= a;
    }
    size_t div = a >> 5;
    size_t mod = a & (BASE_SIZE - 1);
    size_t new_size = length() + div + 1;
    own_vector tmp(new_size);
    tmp.make_copy();
    tmp[div] = static_cast<uint32_t >(static_cast<uint64_t>(get_digit(0)) << mod);
    for (size_t i = div + 1; i < new_size; i++) {
        uint32_t x = static_cast<uint64_t>(get_digit(i - div)) << mod;
        uint32_t y = static_cast<uint64_t>(ownVector[i - div - 1]) >> (BASE_SIZE - mod);
        tmp[i] = x | y;
    }
    ownVector.swap(tmp);
    normalize();
    return *this;
}

big_integer &big_integer::operator>>=(int a) {
    if (a < 0) {
        return (*this) <<= a;
    }
    size_t div = a >> 5;
    uint32_t mod = a & (BASE_SIZE - 1);
    size_t new_size = 0;
    if (div < (*this).length()) {
        new_size = (*this).length() - div;
    }
    own_vector tmp(new_size);
    tmp.make_copy();
    for (size_t i = 0; i < new_size; i++) {
        uint32_t x = static_cast<uint64_t>(ownVector[i + div]) >> mod;
        uint32_t y = static_cast<uint64_t>(get_digit(i + div + 1)) << (BASE_SIZE - mod);
        tmp[i] = x | y;
    }
    ownVector.swap(tmp);
    normalize();
    return *this;
}


big_integer &big_integer::operator%=(big_integer const &other) {
    big_integer a = (*this / other) * other;
    return *this -= a;
}

big_integer big_integer::operator-() const {
    if (*this == 0) {
        return *this;
    }
    big_integer tmp(*this);
    tmp.ownVector.make_copy();
    for (size_t i = 0; i < tmp.length(); ++i) {
        tmp.ownVector[i] = ~tmp.ownVector[i];
    }
    ++tmp;
    tmp.sign = !sign;
    return tmp;
}

big_integer big_integer::operator+() const {
    big_integer tmp(*this);
    return tmp;
}

big_integer &big_integer::operator++() {
    return *this += 1;
}

const big_integer big_integer::operator++(int) {
    big_integer tmp = *this;
    ++(*this);
    return tmp;
}

big_integer &big_integer::operator--() {
    return *this -= 1;
}

const big_integer big_integer::operator--(int) {
    big_integer tmp(*this);
    --(*this);
    return tmp;
}

big_integer big_integer::operator~() const {
    big_integer tmp(*this);
    tmp.ownVector.make_copy();
    for (size_t i = 0; i < tmp.length(); ++i) {
        tmp.ownVector[i] = ~ownVector[i];
    }
    tmp.sign = !tmp.sign;
    return tmp;
}

big_integer big_integer::abs() const {
    if (sign) return -(*this);
    return *this;
}

void big_integer::swap(big_integer &b) {
    big_integer tmp(*this);
    sign = b.sign;
    ownVector = b.ownVector;
    b.sign = tmp.sign;
    b.ownVector = tmp.ownVector;
}

const uint32_t big_integer::get_digit(size_t i) const {
    if (i < length()) return ownVector[i];
    if (sign) return MAX_ELEM;
    return 0;
}

bool big_integer::below_zero() const {
    return sign;
}

big_integer::big_integer(bool negate, own_vector const &data) {
    ownVector = data;
    sign = negate;
    normalize();
}

void big_integer::normalize() {
    ownVector.make_copy();
    while (length() > 1 && ((ownVector.back() == 0 && !sign) || (ownVector.back() == MAX_ELEM && sign))) {
        ownVector.pop_back();
    }
}

size_t big_integer::length() const {
    return ownVector.size();
}

big_integer operator+(big_integer a, big_integer const &b) {
    return a += b;
}

big_integer operator-(big_integer a, big_integer const &b) {
    return a -= b;
}

big_integer operator*(big_integer a, big_integer const &b) {
    return a *= b;
}

big_integer operator/(big_integer a, big_integer const &b) {
    return a /= b;
}

big_integer operator/(big_integer a, uint32_t b) {
    return a /= b;
}

big_integer operator/(big_integer a, int b) {
    return a /= b;
}

big_integer operator%(big_integer a, big_integer const &b) {
    return a %= b;
}

big_integer operator&(big_integer a, big_integer const &b) {
    return a &= b;
}

big_integer operator^(big_integer a, big_integer const &b) {
    return a ^= b;
}

big_integer operator|(big_integer a, big_integer const &b) {
    return a |= b;
}

big_integer operator<<(big_integer a, int b) {
    return a <<= b;
}

big_integer operator>>(big_integer a, int b) {
    return a >>= b;
}


bool operator==(big_integer const &a, big_integer const &b) {
    return a.ownVector == b.ownVector && a.sign == b.sign;
}

bool operator!=(big_integer const &a, big_integer const &b) {
    return !(a == b);
}

bool operator<=(big_integer const &a, big_integer const &b) {
    if (a.sign != b.sign) return a.sign;
    if (a.length() != b.length()) {
        return a.sign ^ (a.length() < b.length());
    }
    for (size_t i = a.length() - 1; i < b.length(); --i) {
        if (a.ownVector[i] != b.ownVector[i]) {
            return a.sign ^ (a.ownVector[i] < b.ownVector[i]);
        }
    }
    return true;
}

bool operator>(big_integer const &a, big_integer const &b) {
    return !(a <= b);
}

bool operator<(big_integer const &a, big_integer const &b) {
    return (a <= b) && (a != b);
}

bool operator>=(big_integer const &a, big_integer const &b) {
    return !(a < b);
}

big_integer::big_integer(big_integer &&other) noexcept {
    swap(other);
}

void big_integer::swap(big_integer &&b) {
    ownVector.swap(b.ownVector);
    std::swap(sign, b.sign);
}

big_integer &big_integer::operator-=(int other) {
    if (other == INT32_MIN) {
        return *this += (-big_integer(other));
    }
    return *this += -other;
}


std::string to_string(big_integer const &value) {
    big_integer a = value.abs();
    if (value == 0) {
        return "0";
    }
    std::string s;
    while (a != 0) {
        uint32_t val = (a % 10).get_digit(0);
        s.push_back(val + '0');
        a /= 10;
    }
    if (value.below_zero()) {
        s.push_back('-');
    }
    std::reverse(s.begin(), s.end());
    return s;
}