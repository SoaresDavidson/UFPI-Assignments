package pedroSantosNeto.banco;

public class ContaJaCadastradaException extends Exception {

	public ContaJaCadastradaException(int n) {
		super("A conta com numero " + n + " já está cadastrada.");
	}
}
