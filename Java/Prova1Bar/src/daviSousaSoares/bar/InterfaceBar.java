package daviSousaSoares.bar;

import java.util.*;

public interface InterfaceBar {
	public void abrirConta(int numConta, int cpf, String nomeCliente) throws ContaAberta, ContaInexistente, DadosInvalidos;
	public void addPedido(int numConta, int numItem, int quant) throws ContaFechada, ContaInexistente, ItemInexistente, DadosInvalidos;
	public double valorDaConta(int numConta) throws ContaInexistente;
	public double fecharConta(int numConta) throws ContaInexistente;
	public void addCardapio(int nu, String n, double x, int tp) throws ItemJaCadastrado, DadosInvalidos;
	public void registrarPagamento(int numConta, double val) throws PagamentoMaior, ContaInexistente, DadosInvalidos;
	public ArrayList<Consumo> extratoDeConta(int numConta) throws ContaInexistente;
}
