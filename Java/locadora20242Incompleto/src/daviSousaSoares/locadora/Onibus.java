package daviSousaSoares.locadora;

public class Onibus extends Veiculo {
	
	int passageiros;
	
	public Onibus(String marc, String mod, int anoF, String pl, double valorD, double valorB, int pass) {
		super(marc,  mod,  anoF,  pl,  valorD, valorB);
		passageiros = pass;
	}
	
	public int getPassageiros() {
		return passageiros;
	}

	public void setPassageiros(int passageiros) {
		this.passageiros = passageiros;
	}

	public double seguro() {
		return (valorBem * 0.11) / 365; 
	}

}
