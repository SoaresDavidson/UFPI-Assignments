package pedroSantosNeto.banco;

public interface EstruturaDeDadosDeContas {

	public abstract void cadastrar(Conta c) throws ContaJaCadastradaException;
		
	public abstract Conta pesquisar(int num) throws ContaInexistenteException;
}
