#include <iostream>
#include <vector>
#include <algorithm>
#include <utility>
#include <queue>
#include <map>

using namespace std;
#define ll long long
const ll MAX = 1e5+10;
const ll INF = 0x3f3f3f3f3f3f3f3f; 
ll n, m;

vector<vector<pair<ll, ll>>> g(MAX);
priority_queue<pair<ll, ll>, vector<pair<ll, ll>>, greater<pair<ll, ll>>> fila;

vector<ll> solve(ll x){
    vector<ll> dist(n, INF);
    fila.push({0, x});
    dist[x] = 0;

    while(!fila.empty()){
        auto [d, u] = fila.top();
        fila.pop();

        if (d > dist[u]) continue;

        for (auto [v, c] : g[u]){
            if (dist[v] > dist[u] + c){
                dist[v] = dist[u] + c;
                fila.push({dist[v], v});
            }
        }
    }
    return dist;
}

int main(){
    cin >> n >> m;
    map<pair<ll, ll>, ll> peso;

    for (int i = 0; i < m; i++){
        ll x, y, z; cin >> x >> y >> z; x--; y--;

        if(peso.find({x, y}) != peso.end()) {
            z = min(peso[{x, y}], z);
        }
        peso[{x, y}] = z;

        g[x].push_back({y, z});
    }

    vector<ll> dist = solve(0);
    
    for (auto x : dist){
        cout << x << ' ';
        
    }
    cout << endl;
    return 0;
}
