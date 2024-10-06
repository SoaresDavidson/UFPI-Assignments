num = int(input("digite um número: "))
print(f"numeros pares entre 0 e {num}: ", [x for x in range(num+1) if x % 2 == 0])