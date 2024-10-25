package daviSousaSoares.estoqueComProdutoPerecivel;
import java.util.ArrayList;
import java.util.Date; 
public class Estoque implements interfaceEstoque {
	private ArrayList<Produto> listaProdutos = new ArrayList<Produto>();

	public Estoque() {
		
	}

	public boolean incluir(Produto p) {
		if (!(p instanceof Produto)) return false;
		if (condicoesIncluirInvalidas(p)) return false;
		
		listaProdutos.add(p);
		return true;
	}
	
	public boolean condicoesIncluirInvalidas(Produto p) {
		return pesquisar(p.getCodigo()) != null || p.getCodigo() <= 0 || !(p.getDescricao() instanceof java.lang.String)|| p.getDescricao() == "" || p.getMin() <= 0 || p.getLucro() <= 0;
	}
	
	public boolean comprar(int cod, int quant, double preco, Date val) {
		Produto p = pesquisar(cod);
		
		if(p == null || quant <= 0) return false;
		
		return p.comprar(quant, preco, val);
		
    }
	public double vender(int cod, int quant) {
		Produto p = pesquisar(cod);
		if (p == null) return -1.0;
		
		return p.venda(quant);
    }
	
	public int quantidade(int cod) {
	  Produto p = pesquisar(cod);
	  if (p == null) return 0;
	  
	  return p.getQuant();
    }
	
	public String movimentacao(int cod, Date inicio, Date fim){
		Produto p = pesquisar(cod);
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
			if (p.getCodigo() == cod) p.getFornecedores().forEach(n -> fornecedores.add(n));
		
		return fornecedores;

    }
	
	public ArrayList<Produto> estoqueAbaixoDoMinimo(){
		ArrayList<Produto> produtosAbaixoMin = new ArrayList<Produto>();
		
		for (Produto p : listaProdutos) if (p.abaixoMin()) produtosAbaixoMin.add(p);
		
		return produtosAbaixoMin;
	}
	
	public ArrayList<Produto> estoqueVencido(){
		ArrayList<Produto> Vencidos = new ArrayList<Produto>();
		Date dataAtual = new Date();
		
		for (Produto p : listaProdutos) 
			for (Lote l : p.getLotes()) 
				if (produtoVencido(dataAtual, l) && !Vencidos.contains(p)) {
					Vencidos.add(p);	
				}
		return Vencidos;
	}
	public int quantidadeVencidos(int cod) {
		Date dataAtual = new Date();
		int count = 0;
		for (Produto p : listaProdutos) 
			for (Lote l : p.getLotes()) 
				if (produtoVencido(dataAtual,l)) 
					count += l.getQuant();
				
		return count;
	}
		
	public boolean produtoVencido(Date dataAtual, Lote l) {
		return dataAtual.getTime() >= l.getValidade().getTime();
	}
	public void adicionarFornecedor(int cod, Fornecedor f) {
		Produto p = pesquisar(cod);
		if (p == null) return;
		
		p.addFornecedor(f);
		
	}
	public double precoDeVenda(int cod) {
		Produto p = pesquisar(cod);
		if (p == null) return 0.0;
		
		return p.getPrecoVenda();
	}
	
	public double precoDeCompra(int cod) {
		Produto p = pesquisar(cod);
		if (p == null) return 0.0;
		
		return p.getPrecoCompra();
	}
	
	public Produto pesquisar(int cod) {
	
		for(Produto p : listaProdutos) {
			if (p.getCodigo() == cod) return p;
		}
		return null;
	}
	
		
}
