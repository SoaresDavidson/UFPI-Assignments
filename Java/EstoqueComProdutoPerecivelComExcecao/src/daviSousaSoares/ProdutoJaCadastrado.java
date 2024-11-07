package daviSousaSoares;

public class ProdutoJaCadastrado extends Exception {
	
	public ProdutoJaCadastrado(Produto p) {
		super("Produto "+ p.getDescricao() +" já cadastrado");
	}
}
