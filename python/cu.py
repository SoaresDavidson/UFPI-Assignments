class Node:
    def __init__(self, i_data):
        self.next = None
        self.data = str(i_data)

    def getData(self):
        return self.data

    def setData(self, n_data):
        self.data = str(n_data) 

class List:
    def __init__(self):
        self.head = None
        self.back = None

    def empty(self):
        return self.head is None

    def size(self):
        sum = 0
        aux = self.head
        while aux is not None:
            sum+=1
            aux = aux.next
        return sum     

    def search(self,value):
        value = str(value).upper()
        aux = self.head

        while aux is not None:
            if aux.getData().upper() == value:
                return value
            aux = aux.next 

        return None    

    def push_back(self, value):
        if self.search(value) is not None:
            print('item ja cadastrado')
            return

        node = Node(value)
        if self.empty(): 
            self.head = node
            self.back = node
        else:
            self.back.next = node 
            self.back = node 
                   

    def erase(self,value):
        if self.head == value: self.head = self.head.next
        
        current_node = self.head
        previus_node = None
        while current_node is not None:
            if current_node.getData() != str(value):
                previus_node = current_node    
                current_node = current_node.next  
                continue
            if previus_node is None:
                self.head = current_node.next
                if self.empty():
                    self.back = None
                    return
            else:
                previus_node.next = current_node.next 
            if current_node is self.back:
                self.back = previus_node 

            current_node = current_node.next              
                    

    def push_front(self, value):
        if self.search(value) is not None:
            print('item ja cadastrado ')
            return 
        novo = Node(value)
        novo.next = self.head
        self.head = novo

        if self.back is None:  
            self.back = novo
               

    def __repr__(self):
        aux = self.head
        msg = ''
        while aux is not None:
            msg += str(aux.getData()) + ' '
            aux = aux.next
        return msg.strip() 


if __name__ == "__main__":
    l = List() 
    for i in range(51):
        l.push_back(i)
    for i in range(-1,-51,-1):
        l.push_front(i)
    print(l)
    print(l.size())
    for i in range(51):
        l.erase(i)
    print(l)
    print(l.size())

    l1 = List()
    l1.push_back("ana")
    l1.push_back("ANA")
    l1.push_back("Ana")
    l1.push_back("beatriz")
    l1.push_front("carlos")
    print(l1)