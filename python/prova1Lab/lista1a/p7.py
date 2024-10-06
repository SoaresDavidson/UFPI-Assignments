frase = ""
while len(frase) > 100 or len(frase) <= 0: 
    frase = input("digite uma frase(max de 100 caracteres e min de 1): ")

print(frase)
print(frase[::-1])