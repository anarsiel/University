#ifndef BIG_INT
#define BIG_INT

//
// Created by blagoi on 12.04.19.
//

#include <vector>
#include <algorithm>
#include <string>
#include "own_vector.h"
#include <stdexcept>

struct big_integer {
    big_integer();

    big_integer(big_integer const &other);

    big_integer(big_integer && other) noexcept;

    big_integer(uint32_t a);

    big_integer(int a);

    explicit big_integer(std::string const &str);

    ~big_integer();

    big_integer operator-() const;

    big_integer operator+() const;

    big_integer &operator++();

    const big_integer operator++(int);

    big_integer &operator--();

    const big_integer operator--(int);

    big_integer operator~() const;


    big_integer &operator=(big_integer const &other);

    big_integer &operator+=(int other);

    big_integer &operator+=(big_integer const &other);

    big_integer &operator-=(big_integer const &other);

    big_integer &operator-=(int other);

    big_integer &operator*=(big_integer const &other);

    big_integer &operator/=(big_integer const &other);

    big_integer &operator/=(uint32_t a);

    big_integer &operator/=(int a);

    big_integer &operator&=(big_integer const &other);

    big_integer &operator^=(big_integer const &other);

    big_integer &operator|=(big_integer const &other);

    big_integer &operator<<=(int a);

    big_integer &operator>>=(int a);

    big_integer &operator%=(big_integer const &other);


    friend big_integer operator+(big_integer a, big_integer const &b);

    friend big_integer operator-(big_integer a, big_integer const &b);

    friend big_integer operator*(big_integer a, big_integer const &b);

    friend big_integer operator/(big_integer a, big_integer const &b);

    friend big_integer operator/(big_integer a, int b);

    friend big_integer operator/(big_integer a, uint32_t b);

    friend big_integer operator%(big_integer a, big_integer const &b);

    friend big_integer operator&(big_integer a, big_integer const &b);

    friend big_integer operator^(big_integer a, big_integer const &b);

    friend big_integer operator|(big_integer a, big_integer const &b);

    friend big_integer operator>>(big_integer a, int b);

    friend big_integer operator<<(big_integer a, int b);


    friend bool operator==(big_integer const &a, big_integer const &b);

    friend bool operator!=(big_integer const &a, big_integer const &b);

    friend bool operator<(big_integer const &a, big_integer const &b);

    friend bool operator>(big_integer const &a, big_integer const &b);

    friend bool operator<=(big_integer const &a, big_integer const &b);

    friend bool operator>=(big_integer const &a, big_integer const &b);


    big_integer abs() const;

    void swap(big_integer &b);

    void swap(big_integer &&b);

    const uint32_t get_digit(size_t i) const;

    bool below_zero() const;

    size_t length() const;

private:
    void normalize();

    big_integer(bool negate, own_vector const &data);
    own_vector ownVector;
    bool sign = false;
    static uint64_t const BASE = 1ll << 32;
    static size_t const BASE_SIZE = (1 << 5);
    static uint32_t const MAX_ELEM = UINT32_MAX;
};

std::string to_string(big_integer const &value);

#endif // BIG_INTEGER_H