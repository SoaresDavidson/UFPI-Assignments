class Node():
    def __init__(self,item):
        self.item = item
        self.next = None
    
    def get_item(self):
        return self.item
    
    def set_item(self, item):
        self.item = item

    def get_next(self):
        return self.next
    
    def set_next(self, next):
        self.next = next

    def __str__(self):
        return str(self.item)
    
class Pilha():
    def __init__(self):
        self.head = None
        self.size

    def push(self, item):
        self.size += 1
        node = Node(item)
        node.setNext(self.head)
        self.head = node

    def pop(self):
        self.size -= 1 if self.size > 0 else 0
        pop = self.head
        self.head = pop.getNext()
        return pop
    
    def top(self):
        return self.head
    
    def size(self):
        return self.size

    def is_empty(self):
        return self.size > 0

    def __repr__(self):
        return str(self)
    
stk = Pilha()
print(stk.top())
stk.push(1)
print(stk.top())
print(stk.pop())
print(stk.top())