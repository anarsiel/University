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
#include <numeric>
#include <time.h>
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

    typedef double dbl;
    typedef long long ll;

    struct Complex{
        dbl x, y;
        Complex(dbl x = 0, dbl y = 0) : x(x), y(y) {}
    };

    Complex operator + (Complex a, Complex b) { return Complex(a.x + b.x, a.y + b.y); }
    Complex operator - (Complex a, Complex b) { return Complex(a.x - b.x, a.y - b.y); }
    Complex operator * (Complex a, Complex b) { return Complex(a.x * b.x - a.y * b.y, a.x * b.y + a.y * b.x); }
    Complex conj(Complex a) { return Complex(a.x, -a.y); }

    int base = 1;
    const dbl PI = atan2(0, -1);
    vector<Complex> roots = {{0, 0}, {1, 0}};
    vector<int> rev = {0, 1};

    void ensureBase(int b){
        if (b <= base)
            return;

        rev.resize(1 << b);
        for (int i = 0; i < (1 << b); i++)
            rev[i] = (rev[i >> 1] >> 1) + ((i & 1) << (b - 1));

        roots.resize(1 << b);
        while (base < b){
            dbl angle = PI / (1 << base);
            for (int i = (1 << (base - 1)); i < (1 << base); i++){
                roots[i << 1] = roots[i];
                dbl angle_i = (2 * i + 1 - (1 << base)) * angle;
                roots[(i << 1) + 1] = Complex(cos(angle_i), sin(angle_i));
            }
            base++;
        }
    }

    void fft(vector<Complex> &f, int n){
        int shift = 0;
        while ((1 << shift) < n)
            shift++;
        ensureBase(shift);
        shift = base - shift;
        for (int i = 0; i < n; i++)
            if (i > (rev[i] >> shift))
                swap(f[i], f[rev[i] >> shift]);

        for (int len = 1; len < n; len *= 2){
            for (int i = 0; i < n; i += 2 * len){
                for (int j = i, k = i + len; j < i + len; j++, k++){
                    Complex z = f[k] * roots[k - i];
                    f[k] = f[j] - z;
                    f[j] = f[j] + z;
                }
            }
        }
    }

    vector<Complex> f;
    vector<ll> answer;

    vector<ll> multiply(vector<int> &a, vector<int> &b){
        int need = a.size() + b.size() - 1;
        int bb = 0;
        while ((1 << bb) < need)
            bb++;
        ensureBase(bb);
        int n = (1 << bb);
        f.resize(n);
        for (int i = 0; i < n; i++){
            f[i].x = (i < a.size() ? a[i] : 0);
            f[i].y = (i < b.size() ? b[i] : 0);
        }
        fft(f, n);
        Complex r = Complex(0, -0.25 / n);
        for (int i = 0; i <= n / 2; i++){
            int j = (n - i) & (n - 1);
            Complex z = (f[j] * f[j] - conj(f[i] * f[i])) * r;
            if (i != j)
                f[j] = (f[i] * f[i] - conj(f[j] * f[j])) * r;
            f[i] = z;
        }
        fft(f, n);
        answer.resize(need);
        for (int i = 0; i < need; i++)
            answer[i] = (ll)(f[i].x + 0.5);
        // print(answer);
        return answer;
    }

    vector<ll> ans;

    const int k = 15;

    vector<ll> multiplyWithMod(vector<ll> &a, vector<ll> &b, int mod){
        int kk = (1 << k);
        int kkk = (kk << k) % mod;
        int need = a.size() + b.size() - 1;
        ans.resize(need);
        fill(ans.begin(), ans.end(), 0);
        vector<int> aa(a.size());
        vector<int> bb(b.size());

        for (int i = 0; i < a.size(); i++)
            aa[i] = a[i] >> k;
        for (int i = 0; i < b.size(); i++)
            bb[i] = b[i] >> k;
        vector<ll> c = multiply(aa, bb);
        for (int i = 0; i < need; i++){
            ll x = c[i] % mod;
            ans[i] += x * (kkk - kk);
        }

        for (int i = 0; i < a.size(); i++)
            aa[i] = a[i] & (kk - 1);
        for (int i = 0; i < b.size(); i++)
            bb[i] = b[i] & (kk - 1);
        c = multiply(aa, bb);
        for (int i = 0; i < need; i++){
            ll x = c[i] % mod;
            ans[i] += x * (1 - kk);
        }

        for (int i = 0; i < a.size(); i++)
            aa[i] += a[i] >> k;
        for (int i = 0; i < b.size(); i++)
            bb[i] += b[i] >> k;
        c = multiply(aa, bb);
        for (int i = 0; i < need; i++){
            ans[i] += c[i] % mod * kk;
            ans[i] %= mod;
            if (ans[i] < 0)
                ans[i] += mod;
        }
        return ans;
    }

};


int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.precision(10);
    cout << fixed;
    string sa, sb;
    cin >> sa >> sb;
    int sign = 1;
    reverse(sa.begin(), sa.end());
    if (sa.back() == '-'){
        sign *= -1;
        sa.pop_back();
    }
    reverse(sb.begin(), sb.end());
    if (sb.back() == '-'){
        sign *= -1;
        sb.pop_back();
    }
    vector<int> a, b;
    for (char c : sa)
        a.push_back(int(c - '0'));
    for (char c : sb)
        b.push_back(int(c - '0'));
    auto res = fft::multiply(a, b);
    for (int i = 0; i < res.size(); i++){
        if (res[i] > 9){
            if (i + 1 == res.size())
                res.push_back(0);
            res[i + 1] += res[i] / 10;
            res[i] %= 10;
        }
    }
    while (res.size() > 1 && res.back() == 0)
        res.pop_back();
    if (sign == -1 && (res.size() > 1 || res[0] != 0))
        cout << '-';
    while (res.size()){
        cout << res.back();
        res.pop_back();
    }
    cout << endl;
    return 0;
}