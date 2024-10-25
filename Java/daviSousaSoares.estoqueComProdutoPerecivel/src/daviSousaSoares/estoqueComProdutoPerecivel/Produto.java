package daviSousaSoares.estoqueComProdutoPerecivel;

import java.util.ArrayList;
import java.util.Date;

public class Produto {
	protected int cod;
	protected String desc;
	protected int min;
	protected double lucro;

	protected int quant = 0;
	protected double precoCompra = 0.0;

	protected ArrayList<String> movimentacoes = new ArrayList<String>();
	protected ArrayList<Date> dateMovimentacoes = new ArrayList<Date>();
	protected ArrayList<Lote> lotes = new ArrayList<Lote>();

	protected ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();

	public Produto(int cod, String desc, int min, double lucro) {
		this.cod = cod;
		this.desc = desc;
		this.min = min;
		this.lucro = lucro;
	}

	public boolean abaixoMin() {
		return this.min > this.quant;
	}

	public boolean comprar(int quant, double val, Date validade) {
		Date dataAtual = new Date();

		if (valoresInvalidos(quant, val, validade) || validadeMenorDataAtual(validade, dataAtual) || ProdutoPerecivelComValidadeNula(validade) )
			return false;

		dateMovimentacoes.add(dataAtual);

		if (dataNaoNula(validade))
			lotes.add(new Lote(quant, validade));

		int newQuant = this.quant + quant;
		precoCompra = (this.quant * precoCompra + quant * val) / newQuant;
		this.quant = newQuant;

		// strdt + ". Compra. Valor: 5.0. Quant: 20.\n"
		int dia = dataAtual.getDate();
		int mes = dataAtual.getMonth() + 1;
		int ano = dataAtual.getYear() + 1900;
		String strdt = dia + "/" + mes + "/" + ano;
		movimentacoes.add(strdt + ". Compra. Valor: " + val + ". Quant: " + quant + ".\n");
		return true;
	}
	public boolean ProdutoPerecivelComValidadeNula(Date validade) {
		return this instanceof ProdutoPerecivel && validade == null;
	}
	
	public boolean validadeMenorDataAtual(Date validade, Date dataAtual) {
		if (!dataNaoNula(validade))
			return false;
		return validade.getTime() < dataAtual.getTime();
	}

	public boolean dataNaoNula(Date data) {
		return data != null;
	}

	public boolean ProdutoNaoPerecivelComData(Date data) {
		return !(this instanceof ProdutoPerecivel) && dataNaoNula(data);
	}

	public boolean valoresInvalidos(int quant, double val, Date data) {
		return quant <= 0 || val <= 0 || ProdutoNaoPerecivelComData(data);
	}

	public double getPrecoVenda() {
		return precoCompra * (1 + lucro);
	}

	public ArrayList<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public int getCodigo() {
		return this.cod;
	}

	public String getDescricao() {
		return this.desc;
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

	public ArrayList<Lote> getLotes() {
		return lotes;
	}

	public double venda(int quant) {
		if (vendaInvalida(quant))
			return -1;

		this.quant -= quant;
		Date dataAtual = new Date();

		int dia = dataAtual.getDate();
		int mes = dataAtual.getMonth() + 1;
		int ano = dataAtual.getYear() + 1900;
		String strdt = dia + "/" + mes + "/" + ano;

		movimentacoes.add(strdt + ". Venda. Valor: " + getPrecoVenda() + ". Quant: " + quant + ".\n");
		this.dateMovimentacoes.add(new Date());

		return quant * getPrecoVenda();

	}

	public void addFornecedor(Fornecedor f) {
		fornecedores.add(f);
	}

	public boolean vendaInvalida(int quant) {
		return quant <= 0 || this.quant <= 0 || this.quant < quant;
	}
}
