def fatorial(num):
    if num == 1 or num == 0: return 1
    return num * fatorial(num-1)


num = int(input("digite um número para ver seu fatorial: "))

print(f"{num}! = {fatorial(num)}")