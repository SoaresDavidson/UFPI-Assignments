package daviSousaSoares;

public class ProdutoNaoPerecivel extends Exception {
	public ProdutoNaoPerecivel(Produto p) {
		super("produto "+ p.getDescricao( )+" não é perecivel,mas possui data");
	}
}
