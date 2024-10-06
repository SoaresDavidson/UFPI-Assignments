#única coisa que eu aprendi de programação dinamica e eu quero exibir ;-;
def fibonacci(num):
    if num == 1 or num == 0: return 1

    if dp[num] != -1: return dp[num]

    dp[num] = fibonacci(num-1) + fibonacci(num-2)

    return dp[num]


num = int(input("digite o indice de um número na sequencia fibonacci: "))
dp = [-1 for i in range(num + 1)]

print(f"O número de índice {num} na sequência de fibonacci é: {fibonacci(num)}")