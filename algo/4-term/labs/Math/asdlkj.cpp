//
// Created by Anarsiel on 27/05/2020.
//

#include <iostream>
#include <cmath>

using namespace std;

long long fast_pow(long long a, long long b, long long module) {
    if (b == 0) {
        return 1;
    }
    long long half = fast_pow(a, b / 2, module);
    long long result = (half * 1ll * half) % module;
    if (b % 2 == 1) {
        result = (result * a) % module;
    }
    return result;
}

long long euler_function(long long x) {
    long long euler = x;
    for (int i = 2; i <= sqrt(x); ++i) {
        if(x % i != 0) {
            continue;
        }
        while (x % i == 0) {
            x /= i;
        }
        euler -= euler / i;
    }
    if (x > 1) {
        euler -= euler / x;
    }
    return euler;
}

int main() {
    long long a, b, n, m;
    cin >> a >> b >> n >> m;
    long long mul = n * m;
    long long x = 0;
    x += a * mul / n * fast_pow(mul / n, euler_function(n) - 1, n);
    x += b * mul / m * fast_pow(mul / m, euler_function(m) - 1, m);
    cout << x % mul;
    return 0;
}