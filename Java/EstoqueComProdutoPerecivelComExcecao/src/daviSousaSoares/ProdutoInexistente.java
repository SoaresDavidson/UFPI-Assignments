package daviSousaSoares;

public class ProdutoInexistente extends Exception {
	public ProdutoInexistente() {
		super("Produto não existe");
	}
}
