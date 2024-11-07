package pedroSantosNeto.banco;

import java.util.ArrayList;

public class Banco {
	
	public static final int INEXISTENTE = -9999999;

	// private VetorDeContas contas = new VetorDeContas();
	private EstruturaDeDadosDeContas contas;	
	
	public Banco(EstruturaDeDadosDeContas x) {
		contas = x;
	}
	
	public void cadastrar(Conta c) throws ContaJaCadastradaException {
		contas.cadastrar(c);
	}
	
	public void saque(int num, double val, String senha) throws SaldoInsuficienteException, SenhaIncorretaException, ContaInexistenteException {
		Conta c = contas.pesquisar(num);
		c.debito(val, senha);
	}
	
	public void juros(int num, double tx) throws ContaInexistenteException, NaoEhPoupancaException {
		Conta c = contas.pesquisar(num);
		if (c instanceof Poupanca p) {
			p.rendeJuros(tx);
		} else {
			throw new NaoEhPoupancaException(num);
		}
	}
	
	public void deposito(int num, double val) throws ContaInexistenteException {		
		Conta c = contas.pesquisar(num);
		c.credito(val);
	}
	
	public String extrato(int num) throws ContaInexistenteException {
		Conta c = contas.pesquisar(num);
		return c.getExtrato();
	}
	
	public double saldo(int num) throws ContaInexistenteException {
		Conta c = contas.pesquisar(num);
		return c.getSaldo();
	}
	
	public void transferencia(int num1, int num2, double val, String senha) throws SaldoInsuficienteException, SenhaIncorretaException, ContaInexistenteException {
		Conta c1 = contas.pesquisar(num1);
		Conta c2 = contas.pesquisar(num2);
		c1.debito(val, senha);
		c2.credito(val);
	}
}
