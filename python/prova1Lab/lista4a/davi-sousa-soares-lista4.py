import os,json
def clear():
    os.system("cls" if os.name == "nt" else "clear");

class Pessoa():
    def __init__(self,nome, cpf, telefones, endereco):
        self.nome = nome
        self.cpf = cpf
        self.telefone = telefones
        self.endereco = endereco
    
    def __str__(self):
        return f"nome = {self.nome}, CPF = {self.cpf}, telefone(s) = {self.telefone}, endereço = {self.endereco}"
    
#le o arquvio(caso não exista cria um) e manipula a string por linha para preencher a classe Pessoa
def le_arquivo():
    try:
        with open("dados.json", "r") as arquivo: jsonDados = json.load(arquivo)
        for dados in jsonDados: resgistrar(dados["nome"], dados["cpf"], dados["telefone"], dados["endereco"])
        #####antes de aplicar json
        #for i in arquivo.readlines(): 
            #if i == "\n": continue
            #dados = i.split("/")#dentro do arquivo os dados são separados por "/"
            #resgistrar(dados[0], dados[1], dados[2], dados[3])#nome,cpf,lista de telefones e endereço respectivamante
    except FileNotFoundError:
        arquivo = open("dados.json","w")
        arquivo.close()
 
#pergunta alguma informação da pessoa e retorna o valor
def query(info:str):
    return input(f"digite o {info} da pessoa: ")

def get_telefones():
    telefones = []
    num = int(input("Quantos telefones deseja registrar?: "))
    for j in range(num): telefones.append(query("telefone"))
    return telefones

#realiza o registro
def resgistrar(nome:str = None, cpf:str = None,telefones:list = None,endereco:str = None):
    if nome == None: nome = query("nome")
    if cpf == None: cpf = query("CPF")
    if telefones == None: telefones = get_telefones()
    if endereco == None: endereco = query("endereço")

    if busca_cpf(cpf) != None:
        print("CPF já cadastrado!Digite um CPF válido")
        return resgistrar()

    cadastros.append(Pessoa(nome,cpf,telefones,endereco))
    print(f"{nome} cadastrado com sucesso")

#checa se o cpf ja está presente no dicionario
def busca_cpf(cpf = None):
    if cpf == None: cpf = query("CPF")

    for i in cadastros:
        if i.cpf == cpf: return i

    return None
#realiza de fato a opção 3 de buscar alguém por cpf e retorna sua instância
def realiza_busca_cpf():
    result = busca_cpf()

    if result == None:
        print("CPF não cadastrado!")
        return
    print("Pessoal econtrada: ",end=" ")
    print(result)

#checa se o telefone existe nos valores do dicionario
def busca_telefone():
    telefone = query("telefone")

    for i in cadastros:
        if telefone in i.telefone: return i
            
    return None

#de fato realiza a opção 4 de buscar alguém pelo numeto de telefone e retorna sua instância
def realiza_busca_telefone():
    result = busca_telefone()

    if result == None:
        print("Telefone não cadastrado",end = "\n\n")
        return
        
    print(f"Pessoa encontrada: {result}")

#realiza a opção 5 de procurar um cpf e remover seu dono
def remove_cpf():
    result = busca_cpf()

    if result == None:
        print("CPF não cadastrado!")
        return
    
    cadastros.remove(result)
    print("Cadastro apagado com sucesso!")

#função do menu
def menu(action):
    clear()
    
    if action == 1:
        resgistrar()

    if action == 2:
        for i,v in enumerate(cadastros):
            print(f"{i+1} - {v}")

    if action == 3:
        realiza_busca_cpf()

    if action == 4:
        realiza_busca_telefone()
    
    if action == 5:
        remove_cpf()

    if action == 6:
        listaDados = list()
        with open("dados.json","w") as arquivo:
            for objeto in cadastros:
                dados = {"nome" : objeto.nome, 
                         "cpf" : objeto.cpf,
                         "telefone" : objeto.telefone,
                         "endereco" : objeto.endereco}
                listaDados.append(dados)    
            json.dump(listaDados, arquivo, indent=2)
            #arquivo.write(json.dump(listaDados))
                #####antes de aplicar json
                #nome = i.nome
                #cpf = i.cpf
                #telefone = i.telefone
                #endereco = i.endereco
                #arquivo.write(f"{nome}/{cpf}/{telefone}/{endereco}\n")
        exit()

    print()

#real execução do codigo(modularizado)
cadastros = []
#preenche a lista de cadastro com instâncias da classe Pessoa a partir das leitura do arquivo
le_arquivo()
while True:
    print("1 - inserir pessoa\n2 - listar pessoas cadastradas\n3 - buscar pessoa por CPF\n4 - buscar pessoa por telefone\n5 - remover pessoa por CPF\n6 - sair")
    menu(int(input()))
