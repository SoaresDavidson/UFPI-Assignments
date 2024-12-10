package daviSousaSoares.banco;


public class ContaComum extends Conta {
	

	public ContaComum (int n, Pessoa p) {
       super(n, p);
	}
		
	void debito(double val, String senha) throws SaldoInsuficienteException, SenhaIncorretaException {
		if (val > 0) {
			if (dono.getSenha() == senha) {
			  if (val <= saldo) {
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
