#include <iostream>

using namespace std;

const int MAX = 1e5 + 10;
const int INF = 1e9 + 10;

int v[MAX];
int seg[4*MAX];

int build(int p, int l, int r){
    if (l == r) return seg[p] = v[l];
    int m = (l+r)/2;
    return seg[p] = min(build(2*p, 1, m), build(2*p+1, m+1,r));
}

int query(int a, int b, int p, int l, int r){
    if (b < l or r < a) return INF;
    if (a <= l and r <= b) return seg[p];
    int m = (l+r)/2;
    return min(query(a, b, 2*p, l, m), query(a, b, 2*p+1, m+1 ,1));
}

int update(int i, int x, int p, int l, int r){
    if (i < l or r < i) return seg[p];
    if (l == r) return seg[p] = x;
    int m = (l+r)/2;
    return seg[p] = min(update(i, x, 2*p, l, m), update(i, x, 2*p+1, m+1, r));
 }