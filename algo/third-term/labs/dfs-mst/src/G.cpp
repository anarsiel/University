#include <iostream>
#include <vector>
#include <algorithm>
#include <math.h>
#include <cstdio>
#include <set>
#include <unordered_map>

using namespace std;

bool used[10000];
int cond[10000];
vector<pair<int, int> > tout;
vector<vector<int> > g(10000), reversed(10000);
int timer = 0;

void dfs(int current){
    used[current] = true;
    for(int i = 0; i < g[current].size(); i++){
        if (!used[g[current][i]]){
            dfs(g[current][i]);
        }
    }
    tout.push_back(make_pair(timer, current));
    timer++;
}

void dfs2(int current, int cnt){
    cond[current] = cnt;
    for(int i = 0; i < reversed[current].size(); i++){
        if (!cond[reversed[current][i]]){
            dfs2(reversed[current][i], cnt);
        }
    }
}

string getNeg(string &s) {
    if (s[0] == '!') {
        return s.substr(1);
    } else {
        return "!" + s;
    };
}

int main()
{
    //freopen("file.in", "r", stdin);
    ios_base::sync_with_stdio(false);

    int n, m;
    cin >> n >> m;
    unordered_map<string, int> namesToNum;
    vector<string> numToName(2 * n);
    int counter = 0;
    for (int i = 0; i < n; ++i) {
        string name;
        cin >> name;

        namesToNum[name] = counter;
        namesToNum["!" + name] = counter + 1;

        numToName[counter] = name;
        numToName[counter + 1] = "!" + name;

        counter += 2;
    }

    for(int i = 0; i < m; i++) {
        string first, second;
        cin >> first >> second >> second;

        if (first[0] == '-') {
            first[0] = '!';
        } else {
            first = first.substr(1);
        }

        if (second[0] == '-') {
            second[0] = '!';
        } else {
            second = second.substr(1);
        }

        g[namesToNum[first]].push_back(namesToNum[second]);
        g[namesToNum[getNeg(second)]].push_back(namesToNum[getNeg(first)]);

        reversed[namesToNum[second]].push_back(namesToNum[first]);
        reversed[namesToNum[getNeg(first)]].push_back(namesToNum[getNeg(second)]);
    }

    for(int i = 0; i < 2 * n; i++) {
        if(!used[i]) {
            dfs(i);
        }

    }
    int cnt = 0;
    reverse(tout.begin(), tout.end());
    for(int i = 0; i < 2 * n; i++){
        if (cond[tout[i].second] == 0){
            cnt++;
            dfs2(tout[i].second, cnt);
        }
    }

    vector<string> answ;
    for (int i = 0; i < n; ++i) {
        if (cond[2*i] > cond[2*i +1]) {
            answ.push_back(numToName[2*i]);
        } else if (cond[2 * i] == cond[2 * i + 1]) {
            cout << -1;
            return 0;
        }
    }

    cout << answ.size() << endl;
    for (auto x : answ)
        cout << x << endl;

    return 0;
}