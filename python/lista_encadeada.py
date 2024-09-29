class Node():
    def __init__(self, item):
        self.item =  item
        self.next = None
        self.prev = None

    def getItem(self):
        return self.item
    
    def setItem(self, item):
        self.item = item

    def __str__(self):
        return str(self.item)
    
class List():
    def __init__(self):
        self.head = None

    def add(self, elem):
        if self.head == None: 
            self.head = elem
            elem.prev = None
            return
        
        item = self.head
        while item.next is not None:
            item = item.next
        item.next = elem
        elem.prev = item
        return
    
    def __str__(self):
        item = self.head
        msg = ""
        while item is not None:

            msg += str(item) + "\n"
            item = item.next
        return msg.strip()
    
lista = List()

elemento1 = Node(22)
elemento2 = Node(3)

lista.add(elemento1)
lista.add(elemento2)

print(lista)