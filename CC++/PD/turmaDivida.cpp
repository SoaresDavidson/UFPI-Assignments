#include <iostream>

using namespace std;

struct Node{
    int key;
    Node* right;
    Node* left;
    Node(int item){
        key = item;
        right = NULL;
        left = NULL;
    }
};

Node* insert(Node* node,int value){
    if (node == NULL)
        return new Node(value);

    if (node->key <= value)
        insert(node->left, value);

    if (node->key > value)
        insert(node->right, value);
    
    return node;
}

int main(){
    int q; cin >> q;
    Node* root = NULL;
    
    while(q--){
        int key; cin >> key;
        root = insert(root, key);
 }
    

}
