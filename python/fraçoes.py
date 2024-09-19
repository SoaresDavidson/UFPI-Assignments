def setNumDenAdd(f1,f2):
    den = f1.getDen() * f2.getDen()

    num = den//f1.getDen() * f1.getNum() + den//f2.getDen() * f2.getNum()

    return num,den

#the smallest number between num and den must be the divisor
def minMax(num, den):
    if num > den:
        max = num
        min = den
    else:
        max = den
        min = num
    
    return min,max

def div(f1, f2):
    return f1.getNum() / f1.getDen(), f2.getNum() / f2.getDen()
class Fracao():
    def __init__(self, num, den):
        self.__num = num
        self.__den = den
    
    #i didn't think it was nescessary to deal with the case where the denominator is equal to 1 (Ex.2/1) because float number are still float even 
    # if they are intengers like 21.0
    def __str__(self):
        return f"{self.getNum()}/{self.getDen()}"
    
    def getNum(self):
        return self.__num
    
    def getDen(self):
        return self.__den
    
    def setNum(self, x ):
        self.__num = x

    def setDen(self, x ):
        self.__den = x

    def __add__(self, f2):
        num,den =  setNumDenAdd(self, f2)

        min,max = minMax(num, den)

        mdc = euclides(max, min)

        return Fracao(num//mdc, den//mdc)

    def __sub__(self, f2):
        #set f2 num to the opposite sign
        f2.setNum(-(f2.getNum()))

        num,den = setNumDenAdd(self, f2)
        #after the sub Num goes back to its original sign
        f2.setNum(-(f2.getNum()))

        if num == 0: return Fracao(0,den)

        min,max = minMax(num, den)

        mdc = euclides(max, min)

        return Fracao(num//mdc, den//mdc)
    
    def __mul__(self, f2):
        den = self.getDen() * f2.getDen()

        num = self.getNum() * f2.getNum()

        min,max = minMax(num, den)

        mdc = euclides(max, min)

        return Fracao(num//mdc, den//mdc)
    
    def __truediv__(self, f2):
        den = self.getDen() * f2.getNum()

        num = self.getNum() * f2.getDen()
        
        min,max = minMax(num, den)

        mdc = euclides(max, min)

        return Fracao(num//mdc, den//mdc)
    #      >
    def __gt__(self, f2):
        n1,n2 = div(self,f2)
        return n1 > n2
    #     >=
    def __ge__(self, f2):
        n1,n2 = div(self,f2)
        return n1 >= n2
    #     <
    def __lt__(self, f2):
        n1,n2 = div(self,f2)
        return n1 < n2
    #     <=
    def __le__(self, f2):
        n1,n2 = div(self,f2)
        return n1 <= n2
    #     ==
    def __eq__(self, f2):
        n1,n2 = div(self,f2)
        return n1 == n2
    #     !=
    def __eq__(self, f2):
        n1,n2 = div(self,f2)
        return n1 != n2
    
def euclides(max, min):
    n = max % min

    if n == 0: return min

    return euclides(min, n)


f1 = Fracao(1, 2)
f2 = Fracao(1, 2)
f3 = f1 + f2
f4 = f1 - f2
f5 = f1 * f2
f6 = f1 / f2

print(f3,f4,f5,f6)
print(f1!=f2)