//
// Created by Anarsiel on 15/06/2020.
//

#ifndef HEADER_H
#define HEADER_H

#include <vector>
typedef S<equal , min_divisor, U<1, 1>> prime;

typedef S<prime, S<plus, U<3, 3>, U<3, 1>>> first_prime_check;

struct next_prime {
    static unsigned compute(std::vector<unsigned> x) {
        typedef S<N, U<3, 3>> start;
        typedef S<first<first_prime_check, 2>, start, zero3, S<plus, start, start>> result;
        typedef S<plus, start, result> real;
        return real::compute(x);
    }
};

#endif //HEADER_H
