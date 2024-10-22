#include <iostream>

using namespace std;

struct Node{
    int key;
    Node* left;
    Node* right;
    Node(int item) {
        key = item;
        left = NULL;
        right = NULL;
    }
};
Node* insert(Node* node,int key){
    if (node == NULL) 
        return new Node(key);

    if (node->key <= key) 
        node->right = insert(node->right, key);

    else 
        node->left = insert(node->left, key);

    return node;
}

Node* search(Node* root, int key){
    if (root == NULL || root->key == key) 
        return root;

    if (root->key < key) 
        return search(root->right, key);

    return search(root->left, key);
}

Node* getSucessor(Node* node){
    node = node->right;

    while (node != NULL && node->left != NULL)
        node = node->left;

    return node;
}

Node* delNode(Node*  root, int val){
    if (root == NULL)
        return root;

    if (root->key > val)
        root->left = delNode(root->left, val);
    else if (root->key < val)
        root->right = delNode(root->right,val);
}
int main() {
    Node* root = NULL;
    return 0;
}
