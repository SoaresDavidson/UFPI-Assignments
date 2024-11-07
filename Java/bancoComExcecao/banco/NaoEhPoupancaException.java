package pedroSantosNeto.banco;

public class NaoEhPoupancaException extends Exception {

	public NaoEhPoupancaException(int n) {
		super("A conta solicitada para render juros não é uma poupança. Numero: " + n);
	}
}
