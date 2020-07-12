//
// Created by Anarsiel on 17/06/2020.
//

#ifndef MATH_PRIMITIVE2_H
#define MATH_PRIMITIVE2_H


#include <iostream>
#include <vector>
#include <set>
#include <unordered_map>
#include <algorithm>
#include <string>
#include <unordered_set>
#include <functional>
#include <queue>
#include <vector>
#include <list>
#include <map>
#include <queue>
#include <math.h>
#include <iomanip>
#include <random>
#include <stdint.h>
#include <chrono>
#include <complex>
#include <math.h>
#include <iostream>
#include <vector>
#include <assert.h>

using namespace std;

struct Z {
    static unsigned compute(const std::vector <unsigned>& x) {
        // cout << "Z" << endl;
        if (x.size() != 1) {
            cout << "ERRR" << x.size() << endl;
        }
        assert(x.size() == 1);
        return 0;
    }
};

struct N {
    static unsigned compute(const std::vector <unsigned>& x) {
        // cout << "N" << endl;
        assert(x.size() == 1);
        return x[0] + 1;
    }
};

template <unsigned n, unsigned k>
struct U {
    static unsigned compute(const std::vector <unsigned>& x) {
        if (x.size() != n)
        {
            cout << "U:" << n << " " << k << " " << x.size() << endl;
        }
        assert(x.size() == n);
        return x[k - 1];
    }
};

template <class f, class ...args>
struct S {
    static unsigned compute(const std::vector <unsigned>& x) {
        auto arg = help<args...>(x);
        return f::compute(help<args...>(x));
    }

    template <typename T>
    static vector<unsigned> help(const std::vector <unsigned> &x) {
        vector<unsigned> v;
        v.push_back(T::compute(x));
        return v;
    }

    template <typename T, typename F, typename ...A>
    static vector<unsigned> help(const std::vector <unsigned>& x) {
        vector<unsigned> v = help<F, A...>(x);
        v.insert(v.begin(), T::compute(x));
        return v;
    }
};


template <class f, class g>
struct R {
    static unsigned compute(std::vector <unsigned> x) {
        if (x.back() == 0) {
            x.pop_back();
            return f::compute(x);
        }
        x.back()--;
        auto res = R::compute(x);
        x.push_back(res);
        return g::compute(x);
    }
};



// Task1
typedef R <U<1,1> , S<N, U<3,3> >> rec_add;


unsigned add(unsigned a, unsigned b) {
    return rec_add::compute({ a, b});
}

// Task2
typedef R < Z, S<rec_add, U<3,1>, U<3,3>> > rec_mul;


unsigned mul(unsigned a, unsigned b) {
    return rec_mul::compute({ a, b});
}

// Task3

typedef R <Z,U<3,2>> rec_dec;

unsigned dec(unsigned a) {
    return rec_dec::compute({a, a});
}

// Task4

typedef R < U<1, 1>, S<rec_dec, U<3,3>, U<3, 3>> > rec_sub;

unsigned sub(unsigned a, unsigned b) {
    return rec_sub::compute({ a, b });
}


// Task5

typedef S<rec_sub, U<1, 1>, S<rec_dec, U<1, 1>, U<1, 1>>> not_zero;
typedef S<rec_sub, S<N, S<Z, U<1, 1>>>, S<not_zero, U<1, 1>>> is_zero;

// a < b -> a - b < 0 -> a + 1 - b == 0
typedef S<is_zero, S<rec_sub, S<rec_add, U<2,1>, S<N, S<Z, U<2, 1>>>>, U<2,2>>> rec_less;
// typedef S<is_zero, S < rec_sub, U<2, 1>, S<rec_add, U<2, 2>, S<N, S<Z, U<2, 1>>>>>> rec_less;
typedef S<N, S<rec_sub, U<2,1> ,U<2,2> > > rec_less_equal;
// typedef S<rec_less_equal, S<N, U<2, 1>>, U<2,2>> rec_less;

unsigned cmp(unsigned a, unsigned b) {
    return rec_less::compute({a,b});
}


// Task I


// a/b = min t : b * t > a
// b * t > a -> (b * t <= a) != 1 -> (b * t <= a) == 0 + (b * t <= a) - 1 != 0
// div_helper(a,b,t) = (b * t < a) == 0 + (b * t < a) - 1 != 0
typedef S<rec_add, S<is_zero, S<rec_less_equal, S<rec_mul, U<3, 2>, U<3, 3>>, U<3, 1>>>,
        S<not_zero, S<rec_sub, S<rec_less_equal, S<rec_mul, U<3, 2>, U<3, 3>>, U<3, 1>>, S<N, S<Z, U<3, 1>>>>>> div_helper;


typedef div_helper F;
const int NumArgs = 3;
// Help : (f(x) == 0 * (i - res - 1) == 0)
typedef S < rec_mul, S<is_zero,
        S<F, U<NumArgs + 1,1>, U<NumArgs + 1,2>, U<NumArgs + 1, 3>>
>,
        S<is_zero, S<rec_dec, U<NumArgs + 1, 1>, S < rec_sub, U<NumArgs + 1, 3>,
                U<NumArgs + 1,4> > >>
> helper;

typedef R < S<Z, U<2,1>>, S<rec_add, S<helper, U<NumArgs + 1,1>, U<NumArgs + 1,2>, U<NumArgs + 1,3>, U<NumArgs + 1, 4>>, U<NumArgs + 1,4>> > rec_first;


typedef S<rec_dec, U<NumArgs, 1>, rec_first> rec_div;

unsigned first(unsigned a, unsigned n) {
    return rec_first::compute({ a, n });
}

unsigned div(unsigned a, unsigned b) {
    return rec_div::compute({ a, b, a / b + 1 });
}


// Task J


typedef S<rec_sub, U<3,1>, S<rec_mul, U<3,2>, rec_div>> rec_mod;

unsigned mod(unsigned a, unsigned b) {
    return rec_mod::compute({ a, b, a / b + 1 });
}

// Task K

typedef R < S < N, S<Z, U<1, 1>>>,
        S<rec_mul, U<3,1>, U<3,3>>> rec_power;


unsigned power(unsigned a, unsigned b) {
    return rec_power::compute({ a, b });
}

// Task L


// min t: n % k^p == 0
typedef S<rec_mod, U<3, 1>, S<rec_power, U<3,2>, U<3,3>>, U<3,1>> is_log_divider;

typedef S < rec_mul, S<is_zero,
        S<is_log_divider, U<NumArgs + 1, 1>, U<NumArgs + 1, 2>, U<NumArgs + 1, 3>>
>,
        S<is_zero, S<rec_dec, U<NumArgs + 1, 1>, S < rec_sub, U<NumArgs + 1, 3>,
                U<NumArgs + 1, 4> > >>
> plog_helper;

typedef R < S<Z, U<2, 1>>, S<rec_add, S<plog_helper, U<NumArgs + 1, 1>, U<NumArgs + 1, 2>, U<NumArgs + 1, 3>, U<NumArgs + 1, 4>>, U<NumArgs + 1, 4>> > rec_first_plog;


typedef rec_first_plog rec_plog;

unsigned plog(unsigned n, unsigned k) {
    return rec_plog::compute({ n,k, (unsigned)(log10(n) / log10(k) + 1u) });
}


unsigned isLogDivider(unsigned n, unsigned k, unsigned p) {
    return is_log_divider::compute({ n,k,p });
}
// Task M

typedef R<S<N, Z>, S<rec_mul, U<3,3>, S<N,U<3,2>>>> rec_fact;

unsigned factorial(unsigned a) {
    return rec_fact::compute({ a, a });
}


// Task N

unsigned getRight(unsigned a) {
    return mod(a, 1u << 2);
}


unsigned getLeft(unsigned a) {
    return div(a, 1u << 2);
}

// a * (1 << 2) + b
typedef S<rec_add, S<rec_mul, U<3,1>, U<3,3>>, U<3,2>> make_pair;

unsigned makePair(unsigned a, unsigned b) {
    return make_pair::compute({ a, b, 1 << 2});
}

// Task O

typedef S<is_zero, S<rec_mod, U<3, 1>, S<N, S<N, U<3, 3>>>, U<3, 1>>> is_divider;
// const int NumArgs = 3;
// Help : (f(x) == 0 * (i - res - 1) == 0)
typedef S < rec_mul, S<is_zero,
        S<is_divider, U<NumArgs + 1, 1>, U<NumArgs + 1, 2>, U<NumArgs + 1, 3>>
>,
        S<is_zero, S<rec_dec, U<NumArgs + 1, 1>, S < rec_sub, U<NumArgs + 1, 3>,
                U<NumArgs + 1, 4> > >>
> helper_prime;

typedef R < S<Z, U<2, 1>>, S<rec_add, S<helper_prime, U<NumArgs + 1, 1>, U<NumArgs + 1, 2>, U<NumArgs + 1, 3>, U<NumArgs + 1, 4>>, U<NumArgs + 1, 4>> > rec_first_prime;

typedef S<is_zero, S<rec_sub, U<3, 2>, S<rec_first_prime, U<3, 1>, U<3, 3>, U<3, 3>>>> check_prime;

// x - 1 != 0
typedef S<not_zero, S<rec_sub, U<3,1>, S<N, S<Z, U<3,1>>>>> gt_one;
typedef S<rec_mul, check_prime, gt_one> is_prime_impl;


typedef S < is_prime_impl,
        U<1, 1>,
        S<rec_dec, U<1, 1>, S<rec_dec, U<1, 1>, U<1, 1>>>,
        S<rec_dec, U<1, 1>, S<rec_dec, U<1, 1>, U<1, 1>>>
> is_prime;

unsigned checkPrime(unsigned a) {
    // cout << rec_first_prime::compute({ a, a - 2, a - 2}) << endl;
    return is_prime::compute({ a });
}


// Task P


typedef R<Z, S<rec_add, U<3, 3>, S<is_prime, S<rec_add, U<3, 2>, S<N, S<Z, U<3,1>>>>>>> count_prime_segment;

// res += countPrime([0, i]) - (k - 1) == 0
typedef S<rec_sub, S<count_prime_segment, U<3,3>, U<3,3>>, S<rec_dec, U<3,2>, U<3,2>>> count_prime;

typedef S < rec_mul, S<is_zero,
        S<count_prime, U<NumArgs + 1, 1>, U<NumArgs + 1, 2>, U<NumArgs + 1, 3>>
>,
        S<is_zero, S<rec_dec, U<NumArgs + 1, 1>, S < rec_sub, U<NumArgs + 1, 3>,
                U<NumArgs + 1, 4> > >>
> count_prime_helper;

typedef R < S<Z, U<2, 1>>, S<rec_add, S<count_prime_helper, U<NumArgs + 1, 1>, U<NumArgs + 1, 2>, U<NumArgs + 1, 3>,
        U<NumArgs + 1, 4>>, U<NumArgs + 1, 4>> > rec_first_kth_prime;

unsigned kthPrime(unsigned k) {
    return rec_first_kth_prime::compute( {k,k, 5} );
}


// Task F

// foo(a,b,i, res) mul(a >> i % 2, b>> i % 2)
typedef S<rec_add,
        S<rec_mul, S<rec_power, S<N, S<N, S<Z, U<4, 1>>>>, U<4, 3>>,
                S<rec_mul,
                        S<rec_mod,
                                S<rec_div, U<4, 1>, S<rec_power, S<N, S<N, S<Z, U<4, 1>>>>, U<4, 3>>, S<N,U<4, 1>>>,
                                S<N, S<N, S<Z, U<4, 1>>>>,
                                U<4, 1>>,
                        S<rec_mod,
                                S<rec_div, U<4, 2>, S<rec_power, S<N, S<N, S<Z, U<4, 1>>>>, U<4, 3>>, S<N,U<4,2>>>,
                                S<N, S<N, S<Z, U<4, 2>>>>,
                                U<4, 2>>
                >>,
        U<4, 4>> and_bit;


typedef R<S<Z, U<2,1>>, and_bit> rec_and;

unsigned and(unsigned a, unsigned b) {
return rec_and::compute({a, b, 4});
}

// Task G

typedef S<rec_sub, S<N, S<Z,U<1,1>>>, U<1,1>> not;

// a xor b = !a & b + a & !b

typedef S<rec_add,
        S<rec_mul, S<rec_power, S<N, S<N, S<Z, U<4, 1>>>>, U<4, 3>>,
                S<rec_add, S<rec_mul,
                        S<not, S<rec_mod,
                                S<rec_div, U<4, 1>, S<rec_power, S<N, S<N, S<Z, U<4, 1>>>>, U<4, 3>>, S<N, U<4, 1>>>,
                                S<N, S<N, S<Z, U<4, 1>>>>,
                                U<4, 1>>>,
                        S<rec_mod,
                                S<rec_div, U<4, 2>, S<rec_power, S<N, S<N, S<Z, U<4, 1>>>>, U<4, 3>>, S<N, U<4, 2>>>,
                                S<N, S<N, S<Z, U<4, 2>>>>,
                                U<4, 2>>>,
                        S<rec_mul,
                                S<rec_mod,
                                        S<rec_div, U<4, 1>, S<rec_power, S<N, S<N, S<Z, U<4, 1>>>>, U<4, 3>>, S<N, U<4, 1>>>,
                                        S<N, S<N, S<Z, U<4, 1>>>>,
                                        U<4, 1>>,
                                S<not, S<rec_mod,
                                        S<rec_div, U<4, 2>, S<rec_power, S<N, S<N, S<Z, U<4, 1>>>>, U<4, 3>>, S<N, U<4, 2>>>,
                                        S<N, S<N, S<Z, U<4, 2>>>>,
                                        U<4, 2>>>>
                >
        >,
        U<4, 4>> xor_bit;

typedef R<S<Z, U<2, 1>>, xor_bit> rec_xor;

unsigned xor(unsigned a, unsigned b) {
return rec_xor::compute({ a, b, 4 });
}

// Task 2A

typedef S < N, S<Z, U<1, 1>>> rec_nil;

unsigned nil(unsigned a) {
    return rec_nil::compute({ a });
}

// Task 2B

struct rec_kth_prime {
    static unsigned compute(const vector<unsigned>& x) {
        assert(x.size() == 1);
        int k = x[0];
        if (x[0] == 0) {
            return 2;
        }
        for (int i = 2; i < 1e6; ++i) {
            bool is_prime = true;
            if (i % 2 == 0) {
                continue;
            }
            for (int j = 3; j * j <= i; j += 2) {
                if (i % j == 0) {
                    is_prime = false;
                    break;
                }
            }

            k -= is_prime;
            if (k <= 0) {
                return i;
            }
        }
        return 1;
    }
};


// Task 2E

// rec_get(arr, index)
// typedef rec_plog rec_get;

unsigned binpow(unsigned a, unsigned b) {
    if (b == 0)
        return 1;
    long long res = binpow(a, b / 2);
    if (b % 2)
        return res * res * a;
    else
        return res * res;
}


//plog
// rec_get(arr, index)
struct rec_get {
    static unsigned compute(const vector<unsigned> & x) {
        if (x.size() < 2) {
            cout << x[0] << endl;
        }
        assert(x.size() == 2);
        for (int i = 0; i < 1e6; ++i) {
            // cout << x[0] << " " << x[1] << endl;
            if (x[0] % binpow(x[1], i) != 0) {
                return i - 1;
            }
        }
        return 0;
    }
};

struct fast_mod {
    static unsigned compute(const vector<unsigned> & x) {
        assert(x.size() == 2);
        if (x[1] == 0) {
            return 0;
        }
        return x[0] % x[1];
    }
};
//Task 2F

// min t: arr % t != 0
typedef S<fast_mod, U<3,2>, S<rec_kth_prime, U<3,3>>> list_help_rec;

typedef S < rec_mul, S<is_zero,
        S<list_help_rec, U<NumArgs + 1, 1>, U<NumArgs + 1, 2>, U<NumArgs + 1, 3>>
>,
        S<is_zero, S<rec_dec, U<NumArgs + 1, 1>, S < rec_sub, U<NumArgs + 1, 3>,
                U<NumArgs + 1, 4> > >>
> list_helper;

typedef R < S<Z, U<2, 1>>, S<rec_add, S<list_helper, U<NumArgs + 1, 1>, U<NumArgs + 1, 2>, U<NumArgs + 1, 3>, U<NumArgs + 1, 4>>, U<NumArgs + 1, 4>> > rec_list;


typedef rec_list rec_len;

unsigned len(unsigned arr) {
    return rec_len::compute({ arr, arr, 10 });
}

struct len_one_arg {
    static unsigned compute(const vector<unsigned> & x) {
        assert(x.size() == 1);
        return rec_len::compute({ x[0], x[0], 10 });
    }
};

struct fast_power {
    static unsigned compute(const vector<unsigned> & x) {
        assert(x.size() == 2);
        return binpow(x[0], x[1]);
    }
};

// Task 2B
//rec_set(index, value)
typedef S<rec_power, S<rec_kth_prime, U<2,1>>, U<2,2>> rec_set;

//add_head_zero(arr) = new_arr * next_prime(arr)^(arr[i])
typedef R< rec_nil, S<rec_mul, U<3, 3>, S<rec_set, S<N,U<3,2>>, S<rec_get, U<3, 1>, S<rec_kth_prime, U<3,2>>>>>> add_head_zero;

// cons(x, arr)
typedef S<rec_mul, S<fast_power, S<N, S<N, S<Z, U<2,1>>>>, U<2, 1>>, S<add_head_zero, U<2,2>, S<len_one_arg, U<2,2>>> > rec_cons;

unsigned cons(unsigned val, unsigned arr) {
    return rec_cons::compute({ val, arr });
}

//Task 2C

//head(arr) = rec_get(0, arr)
typedef S<rec_get, U<1, 1>,  S<N,rec_nil>> rec_head;

unsigned head(unsigned arr) {
    return rec_head::compute({ arr });
}


//Task 2D

//tail(arr) = rec_get(len - 1, arr)
typedef S<rec_get, U<1, 1>, S<rec_kth_prime, S<rec_dec, U<1,1>, len_one_arg>>> rec_tail;

unsigned tail(unsigned arr) {
    return rec_tail::compute({ arr });
}

//Task 2G
// mul(2^a[i]*head(b))
typedef R<U<2,2>,
        S<rec_cons,
                S<rec_get, U<4,1>, S<rec_kth_prime, U<4,3>>>,
                U<4,4> >> rec_concat;

unsigned concat(unsigned a, unsigned b) {
    return rec_concat::compute({ a, b, len_one_arg::compute({a}) });
}
int main() {
    unsigned a, b;
    cin >> a >> b;
    cout << concat(a, b) << endl;
    // cout << list_help_rec::compute({ a, a, 3 });
    // cout << add_head_zero::compute({ b, 1}) << endl;

}

#endif //MATH_PRIMITIVE2_H
