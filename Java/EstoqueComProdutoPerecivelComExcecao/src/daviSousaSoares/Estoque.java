package daviSousaSoares;
import java.util.ArrayList;
import java.util.Date; 
public class Estoque implements interfaceEstoqueComExcecoes {
	private ArrayList<Produto> listaProdutos = new ArrayList<Produto>();

	public Estoque() {
		
	}

	public void incluir(Produto p) throws DadosInvalidos, ProdutoJaCadastrado{
		try {
			condicoesIncluirInvalidas(p);
			pesquisar(p.getCodigo());
			throw new ProdutoJaCadastrado(p);
			
		}
		catch(ProdutoInexistente e) {
			listaProdutos.add(p);
		}
		catch(NullPointerException e) {
			throw new DadosInvalidos();
		}

	}
	
	public void condicoesIncluirInvalidas(Produto p) throws DadosInvalidos {
		if (p.getCodigo() <= 0 || !(p.getDescricao() instanceof java.lang.String)|| p.getDescricao().isBlank() || p.getMin() <= 0 || p.getLucro() <= 0) throw new DadosInvalidos();
		
	}
	
	public void comprar(int cod, int quant, double preco, Date val) throws ProdutoInexistente, DadosInvalidos, ProdutoNaoPerecivel {
		Produto p = pesquisar(cod);
		p.comprar(quant, preco, val);
		
    }
	public double vender(int cod, int quant) throws ProdutoInexistente,DadosInvalidos,ProdutoVencido {
		Produto p = pesquisar(cod);
		return p.venda(quant);
    }
	
	public int quantidade(int cod) throws ProdutoInexistente {
	  Produto p = pesquisar(cod);
	  return p.getQuant();
    }
	
	public String movimentacao(int cod, Date inicio, Date fim) throws ProdutoInexistente {
		Produto p = pesquisar(cod);
		
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
	public void adicionarFornecedor(int cod, Fornecedor f) throws ProdutoInexistente {
		Produto p = pesquisar(cod);
		p.addFornecedor(f);

	}
	public double precoDeVenda(int cod) throws ProdutoInexistente  {
		Produto p = pesquisar(cod);
		if (p == null) return 0.0;
		
		return p.getPrecoVenda();
	}
	
	public double precoDeCompra(int cod) throws ProdutoInexistente {
		Produto p = pesquisar(cod);
		if (p == null) return 0.0;
		
		return p.getPrecoCompra();
	}
	
	public Produto pesquisar(int cod) throws ProdutoInexistente {
		for(Produto p : listaProdutos) {
			if (p.getCodigo() == cod) return p;
		}
		throw new ProdutoInexistente();
	}
		
}
