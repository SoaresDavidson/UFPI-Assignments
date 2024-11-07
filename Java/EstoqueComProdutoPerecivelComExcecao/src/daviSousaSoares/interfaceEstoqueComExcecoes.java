package daviSousaSoares;

import java.util.ArrayList;
import java.util.Date;

public interface interfaceEstoqueComExcecoes {
	
	// Retorna false se ouver algum campo numerico invalido ou texto vazio ou null ou produto ja cadastrado.
	public void incluir(Produto p) throws ProdutoJaCadastrado, DadosInvalidos;

   	//Retorna false se o produto nao existir, se houver valores numericos invalidos ou se enviar data e o produto comprado nao for perecivel
	public void comprar(int cod, int quant, double preco, Date val) throws ProdutoInexistente, DadosInvalidos, ProdutoNaoPerecivel;;
	
	// Retorna -1 se o produto nao existir, se for vencido ou se houver um dado invalido em numeros.
	public double vender(int cod, int quant) throws ProdutoInexistente,DadosInvalidos,ProdutoVencido;
	
	// Retorna null se o produto nao existir
	public Produto pesquisar (int cod) throws ProdutoInexistente;	

	// Retorna lista de produtos abaixo do minimo. Lista vazia se nao tiver nenhum.
	public ArrayList<Produto> estoqueAbaixoDoMinimo();
	
	// Retorna lista de produtos vencidos. Lista vazia se nao tiver nenhum.
	public ArrayList<Produto> estoqueVencido();
	
	// Retorna 0 se nao houver nenhum produto vencido ou a quantidade de produtos vencidos de um determinado codigo.
	public int quantidadeVencidos(int cod);
	
	// Adiciona um fornecedor à lista de fornecedores do produto
	public void adicionarFornecedor(int cod, Fornecedor f) throws ProdutoInexistente ;
	
	// Retorna o preço de venda de um produto
	public double precoDeVenda(int cod) throws ProdutoInexistente ;
	
	// Retorna o preço de compra de um produto
	public double precoDeCompra(int cod) throws ProdutoInexistente;

}
