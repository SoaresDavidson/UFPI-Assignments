import os
os.chdir("/home/davi/Documentos/TrabalhosProgramacao/python/arquivos")
with open("teste","r") as arquivo:
    print(arquivo)
    tam = arquivo.readlines()
    for i,linha in enumerate(tam):
        if i > 0 and i < len(tam)-1: continue
        print(linha,end="")