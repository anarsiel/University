//
// Created by Anarsiel on 27/05/2020.
//

#include <iostream>
#include <string>
#include <vector>
#include <stdio.h>
#include <algorithm>
#include <cassert>
#include <unordered_set>
#include <unordered_map>
#include <set>
#include <map>
#include <queue>
#include <random>
#include <stack>
#include <bitset>

#define right ashapsbdkh
#define prev asasddlsa
#define rank aljds

#define rep(i, l, r) for (int i = l; i < r; i++)
#define repb(i, r, l) for (int i = r; i >= l; i++)
#define endl '\n'

using namespace std;

typedef long long ll;
typedef double dbl;

template<typename T>
void print(const vector<T>& s){
    for (auto x : s)
        cout << x << " ";
    cout << endl;
}

template<class T>
void print(const T* s, int n){
    for (int i = 0; i < n; i++)
        cout << s[i] << ' ';
    cout << endl;
}

template<class T>
void print(vector<vector<T>> s){
    for (int i = 0; i < s.size(); i++)
        print(s[i]);
}

namespace fft{

    using dbl = double;

    struct complex{
        dbl re, im;
        complex(dbl re = 0, dbl im = 0) : re(re), im(im) {}
    };

    std::ostream& operator << (std::ostream& out, complex a){
        return out << a.re << ' ' << a.im << 'i' << endl;
    }

    complex operator + (const complex& a, const complex& b) { return {a.re + b.re, a.im + b.im}; }
    complex operator - (const complex& a, const complex& b) { return {a.re - b.re, a.im - b.im}; }
    complex operator * (const complex& a, const complex& b) { return {a.re * b.re - a.im * b.im, a.re * b.im + a.im * b.re}; }
    complex conj(const complex& a) { return complex(a.re, -a.im); }

    int current_base = 2;
    vector<int> reverse{0, 1};
    vector<complex> roots{{0, 0}, {1, 0}};

    const dbl pi = atan2(1, 1) * 4;

    void ensure_base(int n){
        if (n <= current_base)
            return;
        reverse.resize(n);
        for (int i = 0; i < n; ++i){
            reverse[i] = reverse[i >> 1] >> 1;
            if (i & 1)
                reverse[i] |= n >> 1;
        }
        while (current_base < n){
            complex step{cos(pi / current_base), sin(pi / current_base)};
            for (int i = current_base / 2; i < current_base; ++i){
                roots.push_back(roots[i]);
                roots.push_back(roots[i] * step);
            }
            current_base <<= 1;
        }
    }

    void fft(vector<complex>& f){
        int shift = 0;
        int n = f.size();
        ensure_base(f.size());
        while ((n << shift) < current_base)
            shift++;
        for (int i = 0; i < n; i++){
            if (i > (reverse[i] >> shift))
                swap(f[i], f[reverse[i] >> shift]);
        }
        for (int len = 1; len < n; len <<= 1){
            for (int i = 0; i < n; i += (len << 1)){
                for (int j = i, k = i + len; j < i + len; ++j, ++k){
                    complex z = roots[k - i] * f[k];
                    f[k] = f[j] - z;
                    f[j] = f[j] + z;
                }
            }
        }
    }

    vector<ll> multiply(const vector<int>& a, const vector<int>& b){
        int need = a.size() + b.size() - 1;
        int n = 1;
        while (n < need)
            n *= 2;
        vector<complex> f(n);
        for (int i = 0; i < n; i++){
            f[i].re = i < a.size() ? a[i] : 0;
            f[i].im = i < b.size() ? b[i] : 0;
        }
        fft(f);
        complex r{0, -0.25 / n};
        for (int i = 0; i <= n / 2; i++){
            int j = i == 0 ? 0 : n - i;
            complex fi{f[i] * f[i]}, fj{f[j] * f[j]};
            f[i] = (fj - conj(fi)) * r;
            if (i < j)
                f[j] = (fi - conj(fj)) * r;
        }
        fft(f);
        vector<ll> res(need);
        for (int i = 0; i < need; i++)
            res[i] = ll(f[i].re + 0.5);
        return res;
    }
}


int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.precision(10);
    cout << fixed;
    freopen("duel.in", "r", stdin);
    freopen("duel.out", "w", stdout);
    string s;
    cin >> s;
    vector<int> a(s.size()), b(s.size());
    for (int i = 0; i < s.size(); i++){
        a[i] = int(s[i] - '0');
        b[i] = int(s[i] - '0');
    }
    auto res = fft::multiply(a, b);
    ll ans = 0;
    for (int i = 0; i < s.size(); i++){
        if (s[i] == '1')
            ans += (res[2 * i] - 1) / 2;
    }
    cout << ans << endl;
    return 0;
}