package daviSousaSoares.bar;

import java.sql.SQLException;
import java.util.ArrayList;

public class Bar implements InterfaceBar{
	ArrayList<Conta> contas = new ArrayList<Conta>();
	CardapioDAO cardapio = new CardapioDAO();
	//ArrayList<Item> cardapio = new ArrayList<Item>();
	
	public void abrirConta(int numConta, int cpf, String nomeCliente) throws ContaAberta, ContaInexistente, DadosInvalidos {
		try {
			pesquisaConta(numConta);
			throw new ContaAberta();
		}catch(ContaInexistente e){
			checaDadosConta(numConta, cpf, nomeCliente);
			Conta c = new Conta(numConta, cpf, nomeCliente);
			contas.add(c);
		}
	}
	
	public void addPedido(int numConta, int numItem, int quant) throws ContaFechada, ContaInexistente, ItemInexistente, DadosInvalidos {
		Conta c = pesquisaConta(numConta);
		Item i = pesquisaItem(numItem);
		if (numConta <= 0 || numItem <= 0 ||quant <= 0) throw new DadosInvalidos();
		for (int j = 0; j < quant; j++)
			c.addPedido(i);
	}
	
	public double valorDaConta(int numConta) throws ContaInexistente {
		return pesquisaConta(numConta).getValor();
	}
	
	public double fecharConta(int numConta) throws ContaInexistente {
		Conta c = pesquisaConta(numConta);
		double somaBebidas = 0.0;
		double somaComidas = 0.0;
		double somaItem = 0.0;
		for (Item i : c.getPedidos()) {
			if (i instanceof Bebida) somaBebidas += i.getValor();
			if (i instanceof Comida) somaComidas += i.getValor();
			if (i instanceof ItemSemgorjeta) somaItem += i.getValor();
		}
		c.setContaFechada(true);
		c.setGorjetaComida(somaComidas * 0.15);
		c.setGorjetaBebida(somaBebidas * 0.10);
		
		return somaBebidas + c.getGorjetaBebida() + somaComidas + c.getGorjetaComida() + somaItem;
	}
	
	public void addCardapio(int nu, String n, double x, int tp) throws ItemJaCadastrado, DadosInvalidos {
		try {
			cardapio.inserirItem(nu, n, x, tp);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		try {
//			pesquisaItem(nu);
//			throw new ItemJaCadastrado();
//		}catch(ItemInexistente e){
//			checaDadosItem(nu, n, x,tp);
//			Item i = null;
//			
//			if (tp == 1) i = new ItemSemgorjeta(nu, n, x);
//			
//			if (tp == 2) i = new Bebida(nu, n, x);
//			
//			if (tp == 3) i = new Comida(nu, n, x);
//			
//			cardapio.add(i);
//		}
//		
		
	}
	
	public void registrarPagamento(int numConta, double val) throws PagamentoMaior, ContaInexistente, DadosInvalidos {
		Conta c = pesquisaConta(numConta);
		if (val > c.getValor()) throw new PagamentoMaior();
		c.pagamento(val);
	}
	
	public ArrayList<Consumo> extratoDeConta(int numConta) throws ContaInexistente {
		ArrayList<Consumo> extrato = new ArrayList<Consumo>();
		Conta c = pesquisaConta(numConta);
		for (Item i : c.getPedidos()) {
			extrato.add(new Consumo(i.getNum(), i.getValor(), i.getDesc()));
		}
		extrato.add(new Consumo(0,c.getGorjetaComida() + c.getGorjetaBebida(),"gorjetas"));
		return extrato;
	}
	
	public Conta pesquisaConta(int numConta) throws ContaInexistente{
		for (Conta c : contas) 
			if (c.getNumConta() == numConta)
				return c;
		
		throw new ContaInexistente();
		
	}
	
	public Item pesquisaItem(int numItem) throws ItemInexistente{
		try {
			return cardapio.recuperarItem(numItem);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new ItemInexistente(); //não entendi porque o eclipse reclama com que é obrigado ter esse throw,ele nunca vai chegar nele já que o 
		//recuperar item já da throw ItemInexistente
		
//		for (Item i : cardapio) 
//			if (i.getNum() == numItem)
//				return i;
//		
//		throw new ItemInexistente();
		
		
		
	}
	
	public Consumo pesquisaConsumo(int numItem, ArrayList<Consumo> extrato){
		for (Consumo c : extrato ) 
			if (c.getNum() == numItem)
				return c;
		
		return null;
	}
	
	public void checaDadosConta(int numConta, int cpf, String nomeCliente) throws DadosInvalidos{
		if (numConta <= 0 || cpf <= 0 || nomeCliente.isBlank() || !(nomeCliente instanceof String))
			throw new DadosInvalidos();
		return;
	}
	
	public void checaDadosItem(int nu, String n, double x, int tp) throws DadosInvalidos{
		if (nu <= 0 || tp <= 0 || tp > 3 || n.isBlank() || !(n instanceof String) || x <= 0) {
			throw new DadosInvalidos();
		}
		return;
	}
	
	public void apagaTodoCardapio() throws ClassNotFoundException, SQLException {
		cardapio.apagarTodos();
	}
	public void apagaItemCardapio(int num) throws ClassNotFoundException, SQLException {
		cardapio.apagar(num);
	}
}
