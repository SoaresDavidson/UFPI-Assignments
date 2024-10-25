package daviSousaSoares.estoque;
import java.util.ArrayList;
import java.util.Date; 

public class Estoque {
	private ArrayList<Produto> listaProdutos = new ArrayList<Produto>();

	public Estoque() {
		
	}

	public void incluir(Produto p) {
		if (findProduto(p.getCod()) == null) listaProdutos.add(p);
	}
	
	public void comprar(int cod, int quant, double preco) {
		Produto p = findProduto(cod);
		if(p != null)
			p.comprar(quant, preco);
		
    }
	public double vender(int cod, int quant) {
		Produto p = findProduto(cod);
		if (p == null) return -1.0;
		return (p.getQuant() >= quant && quant > 0) ? p.venda(quant) : -1.0;
    }
	
	public int quantidade(int cod) {
	  Produto p = findProduto(cod);
	  if (p == null) return -1;
	  
	  return p.getQuant();
    }
	
	public String movimentacao(int cod, Date inicio, Date fim){
		Produto p = findProduto(cod);
		if (p == null) return null;
		
		String datas = "";
		ArrayList<String> listaMov = p.getMovimentacoes();
		ArrayList<Date> listaDatas = p.getDateMovimentacoes();
		//d.getTime() > inicio.getTime() && d.getTime() < fim.getTime()
		for(int i = 0; i < listaDatas.size(); i++) {
			long mili = listaDatas.get(i).getTime();
			if (mili > inicio.getTime() && mili < fim.getTime()) {
				datas += listaMov.get(i);
			}
		}
		return datas;
		
    }
	
	public ArrayList<Fornecedor> fornecedores(int cod){
		ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
		
		for (Produto p : listaProdutos) 
			if (p.getCod() == cod) p.getFornecedores().forEach(n -> fornecedores.add(n));
		
		return fornecedores;
	  /*num1 = 10;
		num2 = 20;

		res=(num1>num2) ? (num1+num2):(num1-num2)

		Since num1<num2, 
		the second operation is performed
		res = num1-num2 = -10*/
    }
	
	public ArrayList<Produto> estoqueAbaixoDoMinimo(){
		ArrayList<Produto> produtosAbaixoMin = new ArrayList<Produto>();
		for (Produto p : listaProdutos) if (p.abaixoMin()) produtosAbaixoMin.add(p);
		return produtosAbaixoMin;
	}
	public void adicionarFornecedor(int cod, Fornecedor f) {
		Produto p = findProduto(cod);
		if (p == null) return;
		
		p.addFornecedor(f);
		
	}
	public double precoDeVenda(int cod) {
		Produto p = findProduto(cod);
		if (p == null) return 0.0;
		
		return p.getPrecoVenda();
	}
	
	public double precoDeCompra(int cod) {
		Produto p = findProduto(cod);
		if (p == null) return 0.0;
		
		return p.getPrecoCompra();
	}
	
	public Produto findProduto(int cod) {
		for(Produto p : listaProdutos) 
			  if (p.getCod() == cod) return p;
		return null;
	}
}
