package daviSousaSoares.locadora;

public class Carro extends Veiculo {

	int tipo;
	public static final int PASSEIO = 1;
	public static final int SUV = 2;
	public static final int PICKUP = 3;

	public Carro(String marc, String mod, int anoF, String pl, double valorD, double valorB, int tp) {
		super(marc,  mod,  anoF,  pl,  valorD, valorB);
		tipo = tp;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public double seguro() {
		return (valorBem * 0.03) / 365;
	}
}
