num = int(input("digite um número: "))
print(f"soma dos números ímpares entre 0 e {num}: ", sum([x for x in range(num+1) if x % 2 != 0]))