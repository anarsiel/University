//
// Created by blagoi on 19.04.19.
//

#include "big_integer.h"
#include <string>
#include <vector>
#include <stdexcept>

big_integer::big_integer() {
    data.push_back(0);
    sign = false;
}

big_integer::big_integer(big_integer const &other) {
    sign = other.sign;
    data = other.data;
    normalize();
}

big_integer::big_integer(unsigned a) {
    sign = false;
    data.push_back(a);
}

big_integer::big_integer(int a) {
    sign = a < 0;
    data.push_back(a);
}

big_integer::big_integer(std::string const &str) {
    bool start_index = !(str.empty()) && str[0] == '-';
    data.push_back(0);
    sign = false;
    for (size_t i = start_index; i < str.size(); ++i) {
        (*this) *= 10;
        (*this) += (str[i] - '0');
    }
    if (start_index) *this = -*this;
    normalize();
}

big_integer::~big_integer() {
    data.clear();
}

big_integer &big_integer::operator=(big_integer const &other) {
    data = other.data;
    sign = other.sign;
    return *this;
}

bool operator==(big_integer const &a, big_integer const &b) {
    return a.data == b.data && a.sign == b.sign;
}

bool operator!=(big_integer const &a, big_integer const &b) {
    return !(a == b);
}

bool operator<=(big_integer const &a, big_integer const &b) {
    if (a.sign != b.sign) return a.sign;

    if (a.data.size() != b.data.size()) {
        return a.sign ^ (a.data.size() < b.data.size());
    }

    for (size_t i = a.data.size() - 1; i < a.data.size(); --i) {
        if (a.data[i] != b.data[i]) {
            return a.sign ^ (a.data[i] < b.data[i]);
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

big_integer &big_integer::operator+=(big_integer const &other) {
    ull carry = 0;
    std::vector<unsigned> res(std::max(other.data.size(), data.size()) + 1);

    for (size_t i = 0; i < res.size(); ++i) {
        ull sum = carry + get_digit(i) + other.get_digit(i);
        res[i] = static_cast<unsigned>(sum);
        carry = sum / BASE;
    }

    std::swap(data, res);
    sign = data.back() & (1 << (BASE_SIZE - 1));
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

    big_integer res(0);
    res.data.resize(a.length() + b.length() + 1, 0);

    for (size_t i = 0; i < a.length(); ++i) {
        unsigned carry = 0;
        for (size_t j = 0; j < b.length(); ++j) {
            ull sum = res.data[i + j] + static_cast<ull>(a.data[i]) * b.data[j] + carry;
            res.data[i + j] = sum;
            carry = sum / BASE;
        }
        size_t x = b.length();
        while (carry != 0) {
            ull sum = static_cast<ull>(res.data[i + x]) + carry;
            res.data[i + x] = sum;
            carry = sum / BASE;
            x++;
        }
    }

    if (sign ^ other.sign) {
        *this = -res;
    } else {
        *this = res;
    }
    normalize();
    return *this;
}

big_integer &big_integer::operator/=(big_integer const &other) {
    if (other == 0) {
        throw std::runtime_error("division by zero");
    }

    big_integer a = abs();
    big_integer b = other.abs();

    if (a < b) {
        *this = 0;
        return *this;
    }

    if (a == b) {
        *this = 1;
        return *this;
    }

    big_integer res(0), mod(0);
    unsigned f = (BASE / (b.data.back() + 1ll));
    a *= f;
    b *= f;
    size_t n = a.data.size(), m = b.data.size();
    data.resize(n, 0);
    std::vector<unsigned> data(n - m + 1);
    mod = a >> ((n - m + 1) * BASE_SIZE);
    ull top = b.data.back();
    for (size_t i = 0; i <= n - m; ++i) {
        size_t idx = n - m - i;
        mod <<= BASE_SIZE;
        mod.data[0] = a.data[idx];
        ull mod_top = mod.data.back();
        if (mod.data.size() > m) {
            mod_top <<= BASE_SIZE;
            mod_top += mod.data[mod.data.size() - 2];
        }

        unsigned guess = std::min(mod_top / top, BASE - 1);
        big_integer res_guess = guess * b;
        while (mod < res_guess) {
            guess--;
            res_guess -= b;
        }
        data[idx] = guess;
        mod -= res_guess;
    }

    big_integer tmp(false, data);
    tmp.normalize();
    sign ^= other.sign;
    if (sign) {
        tmp = -tmp;
    }
    *this = tmp;
    normalize();
    return *this;
}

big_integer &big_integer::operator&=(big_integer const &other) {
    for (size_t i = 0; i < data.size(); ++i) {
        data[i] &= other.get_digit(i);
    }
    sign &= other.sign;
    normalize();
    return *this;
}

big_integer &big_integer::operator^=(big_integer const &other) {
    for (size_t i = 0; i < std::max(data.size(), other.data.size()); ++i) {
        if (i < data.size()) data[i] ^= other.get_digit(i);
        else data.push_back(other.data[i]);
    }
    sign ^= other.sign;
    normalize();
    return *this;
}

big_integer &big_integer::operator|=(big_integer const &other) {
    for (size_t i = 0; i < std::max(data.size(), other.data.size()); ++i) {
        if (i < data.size()) data[i] |= other.get_digit(i);
        else data.push_back(other.data[i]);
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
    std::vector<unsigned> temp(new_size);
    temp[div] = static_cast<unsigned >(static_cast<ull>(get_digit(0)) << mod);

    for (size_t i = div + 1; i < new_size; i++) {
        unsigned x = static_cast<ull >(get_digit(i - div)) << mod;
        unsigned y = static_cast<ull>(data[i - div - 1]) >> (BASE_SIZE - mod);
        temp[i] = x | y;
    }
    data = temp;
    normalize();
    return *this;
}

big_integer &big_integer::operator>>=(int a) {
    if (a < 0) {
        return (*this) <<= a;
    }
    size_t div = a >> 5;
    unsigned mod = a & (BASE_SIZE - 1);
    size_t new_size = 0;
    if (div < (*this).length()) {
        new_size = (*this).length() - div;
    }

    std::vector<unsigned> temp(new_size);
    for (size_t i = 0; i < new_size; i++) {
        unsigned x = static_cast<ull>(data[i + div]) >> mod;
        unsigned y = static_cast<ull>(get_digit(i + div + 1)) << (BASE_SIZE - mod);
        temp[i] = x | y;
    }
    data = temp;
    normalize();
    return *this;
}


big_integer &big_integer::operator%=(big_integer const &other) {
    normalize();
    big_integer a = (*this / other) * other;
    return *this -= a;
}

big_integer big_integer::operator-() const {
    if (*this == 0) {
        return *this;
    }
    big_integer tmp(*this);
    tmp = ~tmp;
    ++tmp;
    tmp.sign = !sign;
    return tmp;
}

big_integer big_integer::operator+() const {
    big_integer tmp(*this);
    return tmp;
}

big_integer &big_integer::operator++() {
    normalize();
    return *this += 1;
}

big_integer big_integer::operator++(int) {
    normalize();
    big_integer tmp = *this;
    ++(*this);
    return tmp;
}

big_integer &big_integer::operator--() {
    normalize();
    return *this -= 1;
}

big_integer big_integer::operator--(int) {
    normalize();
    big_integer tmp(*this);
    --(*this);
    return tmp;
}

big_integer big_integer::operator~() const {
    big_integer tmp(*this);
    for (size_t i = 0; i < tmp.data.size(); ++i) {
        tmp.data[i] = ~data[i];
    }
    tmp.sign = !tmp.sign;
    return tmp;
}

big_integer big_integer::abs() const {
    if (sign) return -(*this);
    return *this;
}

void big_integer::swap(big_integer &b) {
    big_integer temp(*this);
    sign = b.sign;
    data = b.data;
    b.sign = temp.sign;
    b.data = temp.data;
    normalize();
}

const unsigned big_integer::get_digit(size_t i) const {
    if (i < data.size()) return data[i];
    if (sign) return MAX_ELEM;
    return 0;
}

bool big_integer::below_zero() const {
    return sign;
}

big_integer::big_integer(bool negate, std::vector<unsigned> const &data) {
    this->data = data;
    sign = negate;
    normalize();
}

void big_integer::normalize() {
    while (data.size() > 1 && ((data.back() == 0 && !sign) || (data.back() == MAX_ELEM && sign))) {
        data.pop_back();
    }
}

size_t big_integer::length() const {
    return data.size();
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

std::string to_string(big_integer const &value) {
    big_integer a = value.abs();
    if (value == 0) {
        return "0";
    }
    std::string s;
    while (a != 0) {
        unsigned val = (a % 10).get_digit(0);
        s.push_back(val + '0');
        a /= 10;
    }
    if (value.below_zero()) {
        s.push_back('-');
    }
    std::reverse(s.begin(), s.end());
    return s;
}