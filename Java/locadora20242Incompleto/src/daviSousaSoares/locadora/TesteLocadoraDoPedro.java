package daviSousaSoares.locadora;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class TesteLocadoraDoPedro {

	@Test
	public void testarInserirVeiculo() {
		Locadora l = new LocadoraDoPedro();
		
		Carro c = new Carro("Ford", "Ranger", 2023, "ABC-1234", 400, 200000, Carro.PICKUP);
		
		l.inserir(c);
		
		Veiculo outro = l.pesquisar("ABC-1234");
		
		assertEquals("Ford", outro.getMarca());
		assertEquals("Ranger", outro.getModelo());
		assertEquals(2023, outro.getAnoFabricação());
		assertEquals(400, outro.getValorDiaria(), 0.001);
		assertEquals(200000, outro.getValorBem(), 0.001);
		assertEquals(Carro.PICKUP, ((Carro) outro).getTipo());
	}
	
	@Test
	public void testarInserirVariosCarros() {
		Locadora l = new LocadoraDoPedro();
		
		Carro c1 = new Carro("Ford", "Ranger", 2023, "ABC-1234", 400, 200000, Carro.PICKUP);
		Carro c2 = new Carro("Toyota", "Corolla", 2017, "ABC-5555", 200, 80000, Carro.PASSEIO);
		Carro c3 = new Carro("GM", "Celta", 2003, "ABC-1111", 100, 10000, Carro.SUV);
		Carro c4 = new Carro("Mitshubish", "L200", 2023, "ABC-9999", 400, 200000, Carro.PICKUP);
		
		l.inserir(c1);
		l.inserir(c2);
		l.inserir(c3);
		l.inserir(c4);
		
		ArrayList<Veiculo> lista = l.pesquisarCarro(Carro.PICKUP);
		assertEquals(2, lista.size());
		lista = l.pesquisarCarro(Carro.SUV);
		assertEquals(1, lista.size());
		lista = l.pesquisarCarro(Carro.PASSEIO);
		assertEquals(1, lista.size());
	}
	
	@Test
	public void testarInserirVariosCaminhoes() {
		Locadora l = new LocadoraDoPedro();
		
		Caminhao c1 = new Caminhao("Ford", "F3000", 2023, "ABC-1234", 400, 200000, 1000);
		Caminhao c2 = new Caminhao("Mercedez", "CLC5000", 2017, "ABC-5555", 200, 80000, 2000);
		Caminhao c3 = new Caminhao("GM", "S30", 2003, "ABC-1111", 100, 10000, 3000);
		Caminhao c4 = new Caminhao("Mitshubish", "L300", 2023, "ABC-9999", 400, 200000, 4000);
		
		l.inserir(c1);
		l.inserir(c2);
		l.inserir(c3);
		l.inserir(c4);
		
		ArrayList<Veiculo> lista = l.pesquisarCaminhao(1000);
		assertEquals(4, lista.size());
		lista = l.pesquisarCaminhao(2000);
		assertEquals(3, lista.size());
		lista = l.pesquisarCaminhao(3000);
		assertEquals(2, lista.size());
		lista = l.pesquisarCaminhao(4000);
		assertEquals(1, lista.size());
		lista = l.pesquisarCaminhao(5000);
		assertEquals(0, lista.size());	
	}
	
	@Test
	public void testarInserirVariosOnibus() {
		Locadora l = new LocadoraDoPedro();
		
		Onibus c1 = new Onibus("Ford", "F3000", 2023, "ABC-1234", 400, 200000, 10);
		Onibus c2 = new Onibus("Mercedez", "CLC5000", 2017, "ABC-5555", 200, 80000, 20);
		Onibus c3 = new Onibus("GM", "S30", 2003, "ABC-1111", 100, 10000, 30);
		Onibus c4 = new Onibus("Mitshubish", "L300", 2023, "ABC-9999", 400, 200000, 40);
		
		l.inserir(c1);
		l.inserir(c2);
		l.inserir(c3);
		l.inserir(c4);
		
		ArrayList<Veiculo> lista = l.pesquisarOnibus(10);
		assertEquals(4, lista.size());
		lista = l.pesquisarOnibus(20);
		assertEquals(3, lista.size());
		lista = l.pesquisarOnibus(30);
		assertEquals(2, lista.size());
		lista = l.pesquisarOnibus(40);
		assertEquals(1, lista.size());
		lista = l.pesquisarOnibus(50);
		assertEquals(0, lista.size());	
	}
	
	@Test
	public void testarInserirVariasMotos() {
		Locadora l = new LocadoraDoPedro();
		
		Moto c1 = new Moto("Ford", "F3000", 2023, "ABC-1234", 400, 200000, 100);
		Moto c2 = new Moto("Mercedez", "CLC5000", 2017, "ABC-5555", 200, 80000, 200);
		Moto c3 = new Moto("GM", "S30", 2003, "ABC-1111", 100, 10000, 300);
		Moto c4 = new Moto("Mitshubish", "L300", 2023, "ABC-9999", 400, 200000, 400);
		
		l.inserir(c1);
		l.inserir(c2);
		l.inserir(c3);
		l.inserir(c4);
		
		ArrayList<Veiculo> lista = l.pesquisarMoto(100);
		assertEquals(4, lista.size());
		lista = l.pesquisarMoto(200);
		assertEquals(3, lista.size());
		lista = l.pesquisarMoto(300);
		assertEquals(2, lista.size());
		lista = l.pesquisarMoto(400);
		assertEquals(1, lista.size());
		lista = l.pesquisarMoto(500);
		assertEquals(0, lista.size());	
	}
}
