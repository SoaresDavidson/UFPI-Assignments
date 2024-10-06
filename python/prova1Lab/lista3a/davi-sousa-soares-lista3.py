import os

def clear():
    os.system("cls" if os.name == "nt" else "clear");

#pergunta alguma informação da pessoa
def query(info:str):
    return input(f"digite o {info} da pessoa: ")
#mostra todos os atributos da pessoa
def mostrar(key):
    return f"nome = {cadastros[key][0]},cpf = {key}, telefone = {cadastros[key][1]}, endereco = {cadastros[key][2]}"
#realiza o registro
def resgistrar():
    nome = query("nome")
    cpf = query("CPF")
    telefone = query("telefone")
    endereco = query("endereço")

    if busca_cpf(cpf) != None:
        print("CPF já cadastrado!")
        return
    
    cadastros[cpf] = (nome,telefone,endereco)
    telefones.append(telefone)
    print(f"{nome} cadastrado com sucesso")

#checa se o cpf ja está presente no dicionario
def busca_cpf(cpf = None):
    if cpf == None: cpf = query("CPF")
    try:
        cadastros[cpf] #checa se é possivel acessar o dicionario com a chave cpf se sim manda a chave
        return cpf
    except KeyError:
        return None
    
#realiza de fato a opção 3 de buscar alguém por cpf e retorna sua instância
def realiza_busca_cpf():
    result = busca_cpf()

    if result == None:
        print("CPF não cadastrado!")
        return

    print(mostrar(result))

#checa se o telefone existe nos valores do dicionario
def busca_telefone():
    telefone = query("telefone")

    for i in cadastros:
        if cadastros[i][1] == telefone:
            return i
        
    return None

#de fato realiza a opção 4 de buscar alguém pelo numeto de telefone e retorna sua instância
def realiza_busca_telefone():
    result = busca_telefone()

    if result == None:
        print("Telefone não cadastrado",end = "\n\n")
        return
        
    print(f"Pessoa encontrada: {mostrar(result)}")

#realiza a opção 4 de procura um cpf e remover seu dono
def remove_cpf():
    result = busca_cpf()

    if result == None:
        print("CPF não cadastrado!")
        return
    
    print("Cadastro apagado com sucesso!")
    telefones.remove(cadastros[result][1])
    del cadastros[result]

#funcção do menu
def menu(action):
    clear()
    
    if action == 1:
        resgistrar()

    if action == 2:
        for i,v in enumerate(cadastros):
            print(f"{i+1} - {mostrar(v)}")

    if action == 3:
        realiza_busca_cpf()

    if action == 4:
        realiza_busca_telefone()
    
    if action == 5:
        remove_cpf()
        
    if action == 6:
        exit()

    print()

cadastros = {}
telefones = []

while True:
    print("1 - inserir pessoa\n2 - listar pessoas cadastradas\n3 - buscar pessoa por CPF\n4 - buscar pessoa por telefone\n5 - remover pessoa por CPF\n6 - sair")
    menu(int(input()))