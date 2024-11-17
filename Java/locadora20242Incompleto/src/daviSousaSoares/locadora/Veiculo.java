package daviSousaSoares.locadora;

public abstract class Veiculo {
	String marca;
	String modelo;
	int anoFabricação;
	String placa;
	double valorDiaria;
	double valorBem;
	
	public abstract double seguro();
	
	public Veiculo(String marc, String mod, int anoF, String pl, double valorD, double valorB) {
		placa = pl;
		marca = marc;
		modelo = mod;
		anoFabricação = anoF;
		valorDiaria = valorD;
		valorBem = valorB;
	}
	
	public double aluguel(int dias) {
		return (valorDiaria + seguro()) * dias;
	}
	
	public void aumentarDiaria(double taxa) {
		valorDiaria = valorDiaria * (1 + taxa);
	}
	
	public void diminuirValorBem(double taxa) {
		valorBem = valorBem * (1 - taxa);
	}
	
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String p) {
		this.placa = p;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAnoFabricação() {
		return anoFabricação;
	}

	public void setAnoFabricação(int anoFabricação) {
		this.anoFabricação = anoFabricação;
	}

	public double getValorDiaria() {
		return valorDiaria;
	}

	public void setValorDiaria(double valorDiaria) {
		this.valorDiaria = valorDiaria;
	}

	public double getValorBem() {
		return valorBem;
	}

	public void setValorBem(double valorBem) {
		this.valorBem = valorBem;
	}
	
}
