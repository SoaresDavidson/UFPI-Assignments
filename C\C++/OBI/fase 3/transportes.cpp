#include <iostream>
#include <vector>
#include <algorithm>
#include <utility>
#include <queue>
#include <map>


using namespace std;
#define ll long long
const ll INF = 0x3f3f3f3f3f3f3f3f; 
ll n, m;

vector<vector<pair<int, int>>> g;
priority_queue<pair<ll, ll>, vector<pair<ll, ll>>, greater<pair<ll, ll>>> fila;

vector<int> precos;
ll solve(int x,int y){
    vector<ll> dist(n, INF);
    fila.push({0, x});
    dist[x] = 0;

    while(!fila.empty()){
        bool ok = true;
        auto [d,u] = fila.top();
        fila.pop();

        if (d > dist[u]) continue;

        for (auto [v, c] : g[u]){

            if (dist[v] > dist[u] + c){
                dist[v] = dist[u] + c;
                fila.push({dist[v],v});
            }
        }
    }

    if (dist[y] == INF) return -1;  // Ou outro valor indicando que não há caminho
    return dist[y];
}

int main(){
    ll k; cin >> n >> m >> k;

    precos.resize(k);
    g.resize(n);  // Redimensiona o grafo para ter n vértices
    
    for (int i = 0;i < k;i++){
        int a;cin >> a;
        precos[i] = a;
    }

    for (int i = 0; i < m; i++){
        int a, b, w; cin >> a >> b >> w;

        g[a-1].push_back({b-1, precos[w-1]});  // Ajuste para índices baseados em 0
        g[b-1].push_back({a-1, precos[w-1]});
    }
    int x, y; cin >> x >> y;x--;y--;
    cout << solve(x, y) << endl;  // Ajuste para índices baseados em 0
    return 0;
}
