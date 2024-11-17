package daviSousaSoares.locadora;

public class Caminhao extends Veiculo {

	int carga;

	public Caminhao(String marc, String mod, int anoF, String pl, double valorD, double valorB, int cg) {
		super(marc,  mod,  anoF,  pl,  valorD, valorB);
		carga = cg;
	}

	public int getCarga() {
		return carga;
	}

	public void setCarga(int carga) {
		this.carga = carga;
	}

	public double seguro() {
		return (valorBem * 0.08) / 365;
	}
}
