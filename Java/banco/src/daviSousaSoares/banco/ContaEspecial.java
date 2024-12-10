package daviSousaSoares.banco;

public class ContaEspecial extends ContaComum {
	
	private double limite;

	public ContaEspecial(int n, Pessoa p, double l) {
		super(n, p);
		limite = l;
	}
	
	void debito(double val, String senha) throws SaldoInsuficienteException, SenhaIncorretaException {
		if (val > 0) {
			if (dono.getSenha() == senha) {
			  if (val <= saldo + limite) {
			    saldo = saldo - val;
			    extrato = extrato + "Debito: " + val + ". Saldo: " + saldo + "\n";
			  } else {
				  throw new SaldoInsuficienteException(numero, saldo);
			  }
			} else {
				throw new SenhaIncorretaException(numero);
			}
		}
	}
}
