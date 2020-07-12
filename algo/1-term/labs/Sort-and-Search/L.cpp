//
// Created by Anarsiel on 17/11/2018.
//

#include <iostream>
#include <vector>
#include <time.h>

using namespace std;

int MOD(int x, int mod) {
    return (x % mod + mod) % mod;
}

int partition(vector<double> &a, int l, int r) {
    int rrrrrrandom = rand();
    int index = MOD(rrrrrrandom, (r - l)) + l;
    double v = a[index];
    int i = l;
    int j = r;

    while (i <= j) {
        while (a[i] < v)
            i++;
        while (a[j] > v)
            j--;
        if (i >= j)
            break;
        swap(a[i++], a[j--]);
    }
    return j;
}

void quickSort(vector<double> &a, int l, int r) {
    if (l < r) {
        int q = partition(a, l, r);
        quickSort(a, l, q);
        quickSort(a, q + 1, r);
    }
}

int partition2(vector<pair<double, int>> &a, int l, int r) {
    int rrrrrrandom = rand();
    int index = MOD(rrrrrrandom, (r - l)) + l;
    pair<double, int> v = a[index];
    int i = l;
    int j = r;

    while (i <= j) {
        while (a[i] < v)
            i++;
        while (a[j] > v)
            j--;
        if (i >= j)
            break;
        swap(a[i++], a[j--]);
    }
    return j;
}

void quickSort2(vector<pair<double, int>> &a, int l, int r) {
    if (l < r) {
        int q = partition2(a, l, r);
        quickSort2(a, l, q);
        quickSort2(a, q + 1, r);
    }
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    freopen("kbest.in", "r", stdin);
    freopen("kbest.out", "w", stdout);
    srand((unsigned int)(time(NULL)));

//    int n;
//    cin >> n;
//    vector<int> v(n);
//    for (int i = 0; i < n; i++)
//        cin >> v[i];
//    quickSort(v, 0, int(v.size()) - 1);
//    for (int i = 0; i < n; i++)
//        cout << v[i] << " ";
//    cout << endl;

    int n, k;
    cin >> n >> k;

    vector<int> v(n), w(n);

    for (int i = 0; i < n; i++) {
        cin >> v[i] >> w[i];
    }

    double l = 0;
    double r = 1e7 + 2;

    for (int kek = 0; kek < 50; kek++) {
        double m = (l + r) / 2;

        vector<double> arr(n);
        for (int i = 0; i < n; i++) {
            arr[i] = v[i] - m * w[i];
        }
        quickSort(arr, 0, int(arr.size()) - 1);

        double sum = 0;
        for (int i = n - 1; i > n - 1 - k; i--) {
            sum += arr[i];
        }

//        cout << sum << " ";

        if (sum >= 0)
            l = m;
        else
            r = m;
    }

//    cout << endl << l << endl;
    vector<pair<double, int>> arr(n);
    for (int i = 0; i < n; i++) {
        arr[i] ={v[i] - l * w[i], i};
    }

    quickSort2(arr, 0, int(arr.size()) - 1);

    for (int i = n - k; i < n; i++) {
        cout << arr[i].second + 1 << ' ';
    }
    return 0;
}
/*
3 2
1 1
1 2
1 3
 */
