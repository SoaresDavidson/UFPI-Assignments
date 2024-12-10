package daviSousaSoares.banco;

public class SenhaIncorretaException extends Exception {

	public SenhaIncorretaException(int n) {
       super("A senha para a conta " + n + " não está correta.");
	}
}
