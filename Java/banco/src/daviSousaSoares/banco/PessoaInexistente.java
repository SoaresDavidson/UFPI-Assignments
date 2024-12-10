package daviSousaSoares.banco;

public class PessoaInexistente extends Exception {

	public PessoaInexistente(int cpf) {
		super("A pessoa com cpf " + cpf + " não está cadastrada.");
	}
}
