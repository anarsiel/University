#ifndef BIGINT_BIG_INTEGER_H
#define BIGINT_BIG_INTEGER_H

//
// Created by blagoi on 12.04.19.
//

#include <string>
#include <vector>
#include <algorithm>
#include <stdexcept>

typedef long long ll;
typedef unsigned long long ull;

struct big_integer {
    big_integer();

    big_integer(big_integer const &other);

    big_integer(ll a);

    big_integer(unsigned a);

    big_integer(int a);

    big_integer(bool negate, std::vector<unsigned int> const &data);

    explicit big_integer(std::string const &str);


    ~big_integer();

    big_integer &operator=(big_integer const &other);

    big_integer &operator+=(big_integer const &other);

    big_integer &operator-=(big_integer const &other);

    big_integer &operator*=(big_integer const &other);

    big_integer &operator/=(big_integer const &other);


    big_integer &operator%=(big_integer const &other);

    big_integer &operator&=(big_integer const &other);

    big_integer &operator^=(big_integer const &other);

    big_integer &operator|=(big_integer const &other);

    big_integer &operator<<=(int a);


    big_integer &operator>>=(int a);

    big_integer operator-() const;

    big_integer operator+() const;

    big_integer &operator++();

    big_integer operator++(int);

    big_integer &operator--();

    big_integer operator--(int);


    big_integer operator~() const;

    friend big_integer operator+(big_integer a, big_integer const &b);

    friend big_integer operator-(big_integer a, big_integer const &b);

    friend big_integer operator*(big_integer a, big_integer const &b);

    friend big_integer operator/(big_integer a, big_integer const &b);

    friend big_integer operator%(big_integer a, big_integer const &b);

    friend big_integer operator&(big_integer a, big_integer const &b);

    friend big_integer operator^(big_integer a, big_integer const &b);

    friend big_integer operator|(big_integer a, big_integer const &b);

    friend big_integer operator>>(big_integer a, int b);

    friend big_integer operator<<(big_integer a, int b);

    friend bool operator==(big_integer const &a, big_integer const &b);

    friend bool operator!=(big_integer const &a, big_integer const &b);

    friend bool operator<=(big_integer const &a, big_integer const &b);

    friend bool operator>(big_integer const &a, big_integer const &b);

    friend bool operator<(big_integer const &a, big_integer const &b);

    friend bool operator>=(big_integer const &a, big_integer const &b);


    big_integer abs() const;

    void swap(big_integer &b);

    const unsigned get_digit(size_t i) const;

    bool below_zero() const;

    size_t length() const;

private:
    void normalize();

    std::vector<unsigned int> data;
    bool sign;
    ull const BASE = 1ll << 32;
    size_t const BASE_SIZE = 32;
    unsigned MAX_ELEM = UINT32_MAX;
};

std::string to_string(big_integer const &value);

#endif //BIGINT_BIG_INTEGER_H