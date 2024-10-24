#include <iostream>

using namespace std;

bool flag = false;

struct Node{
    char key;
    Node* left;
    Node* right;
    Node(char item) {
        key = item;
        left = NULL;
        right = NULL;
    }
};

Node* insert(Node* node,char key){
    if (node == NULL) 
        return new Node(key);

    if (node->key <= key) 
        node->right = insert(node->right, key);

    else 
        node->left = insert(node->left, key);

    return node;
}

Node* search(Node* root, char key){
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

Node* delNode(Node*  root, char val){
    if (root == NULL)
        return root;

    if (root->key > val)
        root->left = delNode(root->left, val);

    else if (root->key < val)
        root->right = delNode(root->right,val);

    else {
        if (root->left == NULL){
            Node* temp = root->right;
            delete root;
            return temp;
        }
        if (root->right == NULL){
            Node* temp = root->left;
            delete root;
            return temp;
        }
        Node* succ = getSucessor(root);
        root->key = succ->key;
        root->right = delNode(root->right, succ->key);
    }
    return root;
}

void prcharInorder(Node* node){
    if (node == NULL)
        return;

    prcharInorder(node->left);

    if (flag) flag = false;
    else cout << " ";
    cout << node->key;

    prcharInorder(node->right);

}

void prcharPreOrder(Node* node){
    if (node == NULL)
        return;

    if (flag) flag = false;
    else cout << " ";
    cout << node->key;

    prcharPreOrder(node->left);
    prcharPreOrder(node->right);
}

void prcharPostOrder(Node* node){
    if (node == NULL)
        return;
        
    prcharPostOrder(node->left);
    prcharPostOrder(node->right);

    if (flag) flag = false;
    else cout << " ";
    cout << node->key;
}



int main() {
    Node* root = NULL;
    string entrada;

    while(cin >> entrada){
        if (entrada == "I"){
            char x;cin >> x;
            root = insert(root, x);
        }

        if (entrada == "INFIXA"){
            flag = true;
            prcharInorder(root);
            cout << endl;
        }
        
        if (entrada == "PREFIXA"){
            flag = true;
            prcharPreOrder(root);
            cout << endl;
        }

        if (entrada == "POSFIXA"){
            flag = true;
            prcharPostOrder(root);
            cout << endl;
        }

        if (entrada == "P"){
            char x; cin >> x;
            Node* elem = search(root, x);
            if (elem == NULL) cout << x << " nao existe";
            else cout << x << " existe";
            cout << endl;
        }   
    }
    
    //root = insert(root,10);
    //root = insert(root,9);
    //cout << root->key;
    return 0;
}
