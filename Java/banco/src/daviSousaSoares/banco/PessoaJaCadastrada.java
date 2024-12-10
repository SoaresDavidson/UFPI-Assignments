package daviSousaSoares.banco;

public class PessoaJaCadastrada extends Exception {

		public PessoaJaCadastrada(int cpf) {
			super("A pessoa com cpf " + cpf + " já está cadastrada.");
		}
}
