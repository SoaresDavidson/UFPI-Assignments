package pedroSantosNeto.banco;

public class VetorDeContas implements EstruturaDeDadosDeContas {

	private Conta contas[] = new Conta[10];
	private int pos = 0;
	
	public void cadastrar(Conta c) throws ContaJaCadastradaException {
		Conta outra;
		try {
			outra = pesquisar(c.getNumero());
			throw new ContaJaCadastradaException(c.getNumero());
		} catch (ContaInexistenteException e) {
		  contas[pos++] = c;
		}
	}

	public Conta pesquisar(int num) throws ContaInexistenteException {
		for (int i = 0; i < pos; i++) {
			if (contas[i].getNumero() == num) {
				return contas[i];
			}
		}
		throw new ContaInexistenteException(num);
	}
}
