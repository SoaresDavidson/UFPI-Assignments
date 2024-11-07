package daviSousaSoares;

import java.util.Date;

public class ProdutoPerecivel extends Produto {
	
	public ProdutoPerecivel(int cod, String desc, int min, double lucro) {
		super(cod, desc, min, lucro);
		
	}
	
	public double venda(int quant) throws ProdutoVencido,DadosInvalidos{
		if (vendaInvalida(quant)) throw new DadosInvalidos();

		int quantLote = quant;
		
		Date dataAtual = new Date();
		
		
		while (quantLote > 0 && this instanceof ProdutoPerecivel) {
			Date min = new Date(Long.MAX_VALUE);
			Lote loteVenda = null;
			
			for (Lote l : lotes) {
				if (loteValido(min,l, dataAtual)) {
					loteVenda = l;
					min = l.getValidade();
				}
			
			}
			if (loteVenda == null) throw new ProdutoVencido();
				
			int aux = loteVenda.getQuant();
			loteVenda.setQuant(loteVenda.getQuant() - aux);
			quantLote -= aux;
		}
		this.quant -= quant;
		int dia = dataAtual.getDate();
		int mes = dataAtual.getMonth() + 1;
		int ano = dataAtual.getYear() + 1900;
		String strdt = dia + "/" + mes + "/" + ano;

		movimentacoes.add(strdt + ". Venda. Valor: " + getPrecoVenda() + ". Quant: " + quant + ".\n");
		this.dateMovimentacoes.add(new Date());
		return quant * getPrecoVenda();

	}

	public boolean loteValido(Date min, Lote l, Date dataAtual) {
		return l.getValidade().getTime() < min.getTime() && dataAtual.getTime() < l.getValidade().getTime() && l.getQuant() > 0;
	}
}

