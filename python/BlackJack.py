import random,os
def clear():
    os.system("cls" if os.name == "nt" else "clear");

class card():
    cartaNum = {
        0:"Ás",
        1:"Dois",
        2:"Três",
        3:"Quatro",
        4:"Cinco",
        5:"Seis",
        6:"Sete",
        7:"Oito",
        8:"Nove",
        9:"Dez",
        10:"Valete",
        11:"Rainha",
        12:"Rei"
    }
    cartaAtt = {
        0:"\u2660",
        1:"\u2663",
        2:"\u2665",
        3:"\u2666"
    }
    def __init__(self, num , att):
        self.__num = num
        self.__att = att

    def __str__(self):
        return f"{self.cartaNum[self.__num]} {self.cartaAtt[self.__att]}"
    
    def getNum(self):
        return self.__num
    
class baralho():
    __pilhaCarta = []
    def __init__(self):
        num = []
        for i in range(52):
            num.append(i)

        while (len(num) > 0):
            carta = random.choice(num)
            self.addPilha(carta)
            num.remove(carta)


    def addPilha(self, num):
        cardNum = num % 13 
        cardAtt = num // 13
        self.__pilhaCarta.append(card(cardNum,cardAtt))
    
    def __str__(self):
        return "\n".join(str(carta) for carta in self.__pilhaCarta)

    def getCartaPilha(self):
        carta = self.__pilhaCarta[0]
        self.__pilhaCarta.remove(carta)
        return carta

def sumHand(cards:list):
    sumHand = 0
    for i in cards:
        if i.getNum() == 0: continue
        sumHand += i.getNum() + 1

    for i in cards:
        if i.getNum() != 0: continue

        if sumHand <= 10: sumHand += 11
        else: sumHand += 1

    return sumHand
while True:
    pilha = baralho()

    cardHide = pilha.getCartaPilha()
    cardShow = pilha.getCartaPilha()

    cardPlayer = [pilha.getCartaPilha()]
    sumPlayer = cardPlayer[0].getNum() +1

    cardDealer = [cardHide, cardShow]
    sumDealer = cardHide.getNum() + cardShow.getNum() + 2

   
    while True:
        clear() 
        print(f"Mão da casa: {cardShow} , ???", end="\n\n")

        print("sua mão: ",end="")

        for i in cardPlayer:
            print(i,end=",")

        print(end="\n\n")

        if input("pressione 1 para comprar: ") == "1":
            cardPlayer.append(pilha.getCartaPilha())
            sumPlayer += cardPlayer[-1].getNum() +1
        else:
            break
        clear()
        if sumPlayer >= 21: break
        
    sumPlayer = sumHand(cardPlayer)
    sumDealer = sumHand(cardDealer)

    print(f"Mão da casa: {cardShow} , {cardHide}", end="\n\n")

    print("sua mão: ",end="")

    for i in cardPlayer:
        print(i,end=",")

    print("\n\n")

    print(sumDealer,sumPlayer)

    if sumPlayer > sumDealer and sumPlayer <= 21:
        print("Você venceu!!!")
    else:
        print("Você perdeu :( ")

    if input("Quer tentar denovo?(0 para cancelar): ") == "0":
        break
    clear()