//
// Created by Anarsiel on 14/06/2020.
//

#ifndef PRIMITIVE_PRIMITIVE_H
#define PRIMITIVE_PRIMITIVE_H

#include <iostream>
#include <vector>
#include <cassert>

namespace primitive {

    struct Z {
        static unsigned compute(std::vector<unsigned> x) {
            assert(x.size() == 1);
            return 0;
        }
    };

    struct N {
        static unsigned compute(std::vector<unsigned> x) {
            assert(x.size() == 1);
            return x[0] + 1;
        }
    };

    template<unsigned n, unsigned k>
    struct U {
        static unsigned compute(std::vector<unsigned> x) {
            assert(x.size() == n);
            return x[k - 1];
        }
    };


    template<class f, class ... g>
    struct S {
        static unsigned compute(std::vector<unsigned> x) {
            std::vector<unsigned> result = {g::compute(x)...};
            return f::compute(result);
        }
    };

    template<class f, class g>
    struct R {
        static unsigned compute(std::vector<unsigned> xy) {
            unsigned y = xy.back();
            if (y == 0) {
                xy.pop_back();
                return f::compute(xy);
            }
            --xy.back();
            xy.push_back(R<f, g>::compute(xy));
            return g::compute(xy);
        }
    };

    typedef S<Z, U<1, 1>> zero1;
    typedef S<Z, U<2, 1>> zero2;
    typedef S<Z, U<3, 1>> zero3;
    typedef S<Z, U<4, 1>> zero4;
    typedef S<N, Z> one1;
    typedef S<N, S<Z, U<2, 1>>> one2;
    typedef S<N, S<Z, U<3, 1>>> one3;
    typedef S<N, S<Z, U<4, 1>>> one4;
    typedef S<N, one1> two1;
    typedef S<N, one2> two2;
    typedef S<N, one3> two3;
    typedef S<N, one4> two4;

    typedef R<U<1, 1>, S<N, U<3, 3>>> plus;

    typedef R<Z, S<plus, U<3, 3>, U<3, 1>>> multiply;

    typedef S<R<Z, U<3, 2>>, Z, U<1, 1>> minus_one;

    typedef R<U<1, 1>, S<minus_one, U<3, 3>>> minus;

    typedef S<R<Z, one3>, U<2, 1>, S<minus, U<2, 2>, U<2, 1>>> less;

    typedef S<minus, one2, S<plus, less, S<less, U<2, 2>, U<2, 1>>>> equal;

    typedef S<R<U<2, 2>, U<4, 1>>, U<3, 2>, U<3, 3>, U<3, 1>> iff;

    typedef S<iff, less, zero2, one2> not_less;

    typedef S<iff, less, zero2, S<iff, equal, zero2, one2>> greater;

    typedef S<N, Z> nil;

    template <class f, int k>
    struct first {
        static unsigned compute(std::vector<unsigned> x) {
            typedef U<k + 2, k + 2> last;
            typedef S<N, last> next;
            typedef R<zero2, S<iff,
                    S<equal, S<f, U<k + 2, 1>, U<k + 2, 2>, last>, zero4>,
                    next,
                    last>> result;
            return result::compute(x);
        }
    };

    typedef S<greater, S<multiply, U<3, 2>, U<3, 3>>, U<3, 1>> divide_check;

    typedef S<minus_one, S<first<divide_check, 2>, U<2, 1>, U<2, 2>, S<N, U<2, 1>>>> divide;

    typedef S<minus, U<2, 1>, S<multiply,
            U<2, 2>,
            S<divide, U<2, 1>, U<2, 2>>>> module;

    typedef R<one1, S<multiply, U<3, 3>, U<3, 1>>> power;

    typedef S<greater, S<power, U<3, 2>, U<3, 3>>, U<3, 1>> log_check;

    typedef S<minus_one, S<first<log_check, 2>, U<2, 1>, U<2, 2>, S<N, U<2, 1>>>> log;

    typedef S<equal, zero3, S<module,
            U<3, 1>,
            S<power,
                    U<3, 2>,
                    S<minus, S<log, U<3, 1>, U<3, 2>>, U<3, 3>>>>> plog_check;

    typedef S<minus, log, S<first<plog_check, 2>, U<2, 1>, U<2, 2>, log>> plog;

    typedef S<R<one1, S<multiply, U<3, 3>, S<N, U<3, 2>>>>, Z, U<1, 1>> factorial;

    typedef S<equal, zero3, S<module, U<3, 1>, S<N, S<N, U<3, 3>>>>> min_divisor_check;

    typedef S<N, S<N, S<first<min_divisor_check, 2>, U<1, 1>, Z, U<1, 1>>>> min_divisor;

    typedef S<equal , min_divisor, U<1, 1>> prime;



    typedef S<multiply,
            S<power, two2, U<2, 1>>,
            S<power, S<N, two2>, U<2, 2>>> make_pair;

    typedef S<plog, U<1, 1>, two1> get_left;
    typedef S<plog, U<1, 1>, S<N, two1>> get_right;

    typedef S<prime, S<plus, U<3, 3>, U<3, 1>>> first_prime_check;

    struct next_prime {
        static unsigned compute(std::vector<unsigned> x) {
            typedef S<N, U<3, 3>> start;
            typedef S<first<first_prime_check, 2>, start, zero3, S<plus, start, start>> result;
            typedef S<plus, start, result> real;
            return real::compute(x);
        }
    };

    typedef S<R<S<N, one1>, next_prime>, Z, U<1, 1>> kth_prime;

    typedef S<plog,
            U<2, 1>,
            S<kth_prime, U<2, 2>>> index;

}

#endif //PRIMITIVE_PRIMITIVE_H

