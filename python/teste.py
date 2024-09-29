class bi():
    def __init__(self,nn):
        self.nn = nn
        pass
    
    def __iter__(self):
        print("iteration started")
        return self

    def __next__(self):
        self.nn += 1
        if self.nn >= 100: raise StopIteration
        print("next iteration")
        return self.nn
    
for i in bi(10):
    print(i)