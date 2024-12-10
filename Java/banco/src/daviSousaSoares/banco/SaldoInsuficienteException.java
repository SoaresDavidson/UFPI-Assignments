package daviSousaSoares.banco;

public class SaldoInsuficienteException extends Exception {

	public SaldoInsuficienteException(int n, double s) {
		super("A conta " + n + " não possui saldo suficiente para a operação desejada. Saldo atual: " + s + ".");
	}
}
