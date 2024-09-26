#include <iostream>
#include <bits/stdc++.h>

using namespace std;

const int MAX = 1e5+10;

int a[MAX];
pair<int,int> seg[4*MAX];

pair<int,int> combina(pair<int, int> a, pair<int, int> b){
    if (a.first < b.first) return a;
    if (b.first < a.first) return b;
    return {a.first, a.second + b.second};
}

int main(){
    int n, q; cin >> n >> q;
    for (int i = 0; i < n; i++) cin >> a[i];   
    return 0;
}