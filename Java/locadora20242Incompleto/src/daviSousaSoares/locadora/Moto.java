package daviSousaSoares.locadora;

public class Moto extends Veiculo {
	
	int cilindrada;
	
	public Moto(String marc, String mod, int anoF, String pl, double valorD, double valorB, int cc) {
		super(marc,  mod,  anoF,  pl,  valorD, valorB);
		cilindrada = cc;
	}
	
	public int getCilindrada() {
		return cilindrada;
	}

	public void setCilindrada(int cilindrada) {
		this.cilindrada = cilindrada;
	}

	public double seguro() {
		return (valorBem * 0.11) / 365; 
	}
}
