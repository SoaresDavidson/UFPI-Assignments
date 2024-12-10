package daviSousaSoares.banco;

public class ContaInexistenteException extends Exception {

	public ContaInexistenteException(int n) {
		super("A conta com numero " + n + " não existe.");	
	}
}
