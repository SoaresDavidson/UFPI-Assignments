package daviSousaSoares.locadora;

import java.util.ArrayList;

public interface Locadora {

		public void inserir(Veiculo v);
		public void inserir(Cliente c);
		public Veiculo pesquisar(String placa); 

	    public ArrayList<Veiculo> pesquisarMoto(int cilindrada);
		// tipo de carro
		// 1 (passeio), 2 (SUV), 3 (pickup)
	    public ArrayList<Veiculo> pesquisarCarro(int tipoCarro);
	    public ArrayList<Veiculo> pesquisarCaminhao(int carga);
	    public ArrayList<Veiculo> pesquisarOnibus(int passageiros);
	    
	    //Seguro Moto = (valor do bem * 11%)/365
	    //Seguro Carro = (valor do bem * 3%)/365
	    //Seguro Caminhão = (valor do bem * 8%)/365
	    //Seguro Ônibus = (valor do bem * 20%)/365
	    //Aluguel = (valor da diária + seguro) * quantidade de dias
	    public double calcularAluguel(String placa, int dias);
	    public void registrarAluguel(String placa, int dias, Cliente c);
	    public void registrarDevolucao(String placa, Cliente c);
	   
		// tipo de veiculo
		// 0 (todos), 1 (moto), 2 (carro), 3 (ônibus), 4 (caminhão)
	    public void depreciarVeiculos(int tipo, double taxaDepreciacao);
	    public void aumentarDiaria(int tipo, double taxaAumento);

		// Só alugueis fechados (com registro e devolução)
	    public double faturamentoTotal(int tipo);
		// Só alugueis fechados (com registro e devolução)
	    public int quantidadeTotalDeDiarias(int tipo);
}
