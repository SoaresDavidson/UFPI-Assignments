package daviSousaSoares.bar;

public class ContaFechada extends Exception {
	public ContaFechada() {
		super("Está conta já foi fechada");
	}
}
