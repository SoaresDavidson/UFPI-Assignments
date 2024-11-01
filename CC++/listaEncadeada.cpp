#include <iostream>
#include <string>
using namespace std;

template <typename T>
class Node
{
public:
    T dado;
    Node *proximo;

    Node() {
    };
    Node(T dado)
    {
        this->dado = dado;
        this->proximo = nullptr;
    }
};

template <typename T>
class PilhaEncadeada
{

public:
    Node<T> *ptr_top;
    int error;

    void initialize()
    {
        this->ptr_top = NULL;
        error = 0;
    };

    bool isEmpty()
    {

        return this->ptr_top == NULL;
    }

    void push(T value)
    {
        Node<T> *newNode = new Node<T>(value);
        error = 0;

        if (newNode == NULL)
        {
            cout << "Erro de alocação de memoria\n";
            error = 1;
            return;
        }

        newNode->proximo = this->ptr_top;
        this->ptr_top = newNode; // Atualiza o topo da pilha
    }

    T pop()
    {
        error = 0;
        if (isEmpty())
        {
            cout << "Pilha vazia";
            error = 1;

            return T();
        }
        Node<T> *temp = this->ptr_top;
        T poppedValue = temp->dado;
        this->ptr_top = this->ptr_top->proximo;
        delete temp;
        return poppedValue;
    };

    int top()
    {
        error = 0;
        if (isEmpty())
        {
            cout << "Pilha vazia" << endl;
            error = 1;
        }
        return this->ptr_top->dado;
    };

    void printStack()
    {
        if (isEmpty())
        {
            cout << "Pilha vazia" << endl;
            return;
        }
        Node<T> *current = this->ptr_top;
        cout << "Elementos da pilha: ";
        while (current != NULL)
        {
            cout << current->dado << " ";
            current = current->proximo;
        }
        cout << endl;
    }
}; // fim da classe

template <typename T>
class LinkedList
{
public:
    Node<T> *cabeca;

    LinkedList()
    {
        cabeca = nullptr;
    }

    void adicionarFim(int dado)
    {
        Node<T> *newDado = new Node<T>(dado);

        if (cabeca == nullptr)
        {
            cabeca = newDado;
            return;
        }
        Node<T> *temp = cabeca;
        while (temp->proximo != nullptr)
        {
            temp = temp->proximo;
        }
        temp->proximo = newDado;
    };
}; // fim da classe LinkedList;

void decimalParaBinario(int decimal)
{
    PilhaEncadeada<int> stack;
    stack.initialize();

    // Dividir o número por 2 repetidamente, empilhando os restos
    while (decimal > 0)
    {
        int resto = decimal % 2;
        stack.push(resto);
        decimal = decimal / 2;
    }

    // Desempilhar para formar o binário
    cout << "Número binário: ";
    while (!stack.isEmpty())
    {
        cout << stack.pop();
    }
    cout << endl;
}

PilhaEncadeada<int> reverterListaEncadeada(LinkedList<int> lista)
{
    PilhaEncadeada<int> pilha;
    pilha.initialize();

    if (lista.cabeca == nullptr)
    {
        cout << "\nError= Lista vazia.\n";
        return pilha;
    }

    Node<int> *temp = lista.cabeca;

    cout << "Lista: ";
    while (temp != nullptr)
    {
        cout << temp->dado << ", ";
        pilha.push(temp->dado);
        temp = temp->proximo;
    }
    cout << "Nullptr.\n";
    return pilha;
};

string posfixaParaInfixa(string exp)
{
    PilhaEncadeada<string> stack;
    stack.initialize();

    for (char c : exp)
    {
        if (isdigit(c))
        {
            stack.push(string(1, c));
        }
        else
        {
            string op1 = stack.pop();
            string op2 = stack.pop();
            string temp = "(" + op2 + c + op1 + ")";
            stack.push(temp);
        }
    }

    return stack.pop();
}

int fibonacci(int n)
{
    if (n <= 1)
        return n;

    PilhaEncadeada<int> stack;
    stack.initialize();
    stack.push(n);

    int fibN_minus_2 = 0;
    int fibN_minus_1 = 1;

    int result = 0;
    while (n > 1)
    {
        result = fibN_minus_1 + fibN_minus_2;
        fibN_minus_2 = fibN_minus_1;
        fibN_minus_1 = result;
        n--;
    }

    return fibN_minus_1;
}




    int main()
    {
        system("cls");
        cout << "\n***Resolvendo utilizando pilhas encadeadas**********\n\n";
        cout << "\nInicio da questao 7.\n\n";

        int numeroDecimal = 10;

        decimalParaBinario(numeroDecimal);
        cout << "\nFim da questao 7.\n\n";

        //------------------------------------------------------------
        cout << "\nInicio da questao 8.\n\n";

        LinkedList<int> lista;
        lista.adicionarFim(10);
        lista.adicionarFim(20);
        lista.adicionarFim(30);
        lista.adicionarFim(40);

        PilhaEncadeada<int> listaInvertida = reverterListaEncadeada(lista);
        listaInvertida.printStack();
        cout << "\nFim da questao 8.\n\n";

        //--------------------------------
        cout << "\nInicio da questao 9.\n\n";

        string exp = "56+2*";
        cout << "Expressao pos-fixa: " << exp << endl;
        cout << "Expressao infixa: " << posfixaParaInfixa(exp) << endl;

        cout << "\nFim da questao 9.\n\n";

        //--------------------------
        cout << "\nInicio da questao 10.\n\n";
        int n = 10;

        cout << "O " << n << "-esimo numero de Fibonacci eh: " << fibonacci(n) << endl;
        cout << "\nFim da questao 10.\n\n";
        return 0;
    }