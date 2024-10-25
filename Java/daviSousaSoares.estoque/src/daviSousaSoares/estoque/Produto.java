package daviSousaSoares.estoque;
import java.util.ArrayList;
import java.util.Date; 

public class Produto {
	private int cod;
	private String desc;
	private int min;
	private double lucro;
	
	private int quant=0;
	private double precoCompra=0.0;
	
	private ArrayList<String>movimentacoes = new ArrayList<String>();
	private ArrayList<Date>dateMovimentacoes = new ArrayList<Date>();
	
	private ArrayList<Fornecedor>fornecedores = new ArrayList<Fornecedor>();
	
	public Produto(int cod, String desc, int min, double lucro) {
		this.cod = cod;
		this.desc = desc;
		this.min = min;
		this.lucro = lucro;
	}
	
	public boolean abaixoMin() {
		return this.min > this.quant;
	}
	
	public void comprar(int quant,double val) {
		if (quant < 0 || val < 0) return;
		Date dataAtual = new Date();

		dateMovimentacoes.add(dataAtual);
		
		int newQuant = this.quant + quant;
		precoCompra = (this.quant * precoCompra + quant * val) / newQuant;
		this.quant = newQuant;
		//strdt + ". Compra. Valor: 5.0. Quant: 20.\n" 
		int dia = dataAtual.getDate();
		int mes = dataAtual.getMonth()+1;
		int ano = dataAtual.getYear()+1900;
		String strdt = dia + "/" + mes + "/" + ano;
		movimentacoes.add(strdt + ". Compra. Valor: " + val + ". Quant: " + quant+".\n");
		
	}
	
	public double getPrecoVenda() {
		return precoCompra * (1+lucro);
	}
	
	public ArrayList<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public int getCod() {
		return this.cod;
	}

	public String getDesc() {
		return desc;
	}

	public int getMin() {
		return min;
	}

	public double getLucro() {
		return lucro;
	}

	public int getQuant() {
		return quant;
	}

	public double getPrecoCompra() {
		return precoCompra;
	}

	public ArrayList<String> getMovimentacoes() {
		return movimentacoes;
	}

	public ArrayList<Date> getDateMovimentacoes() {
		return dateMovimentacoes;
	}

	public double venda(int quant) {
		if (quant < 0 || this.quant < 0) return -1;
		
		this.quant -= quant;
		Date dataAtual = new Date();
		
		int dia = dataAtual.getDate();
		int mes = dataAtual.getMonth()+1;
		int ano = dataAtual.getYear()+1900;
		String strdt = dia + "/" + mes + "/" + ano;
		
		movimentacoes.add(strdt + ". Venda. Valor: " +getPrecoVenda()+". Quant: "+quant+".\n");
		this.dateMovimentacoes.add(new Date());
		
		return quant * getPrecoVenda();
		
	}
	
	public void addFornecedor(Fornecedor f) {
		fornecedores.add(f);
	}
	
}
