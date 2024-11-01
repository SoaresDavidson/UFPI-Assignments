package daviSousaSoares.banco;

import org.json.simple.JSONObject;

public abstract class Conta {
			
		public static int quantidade;
		
		private int numero;
		protected double saldo;
		protected String extrato = "";
		protected Pessoa dono;
		
		public Pessoa getDono() {
			return dono;
		}

		public void setDono(Pessoa dono) {
			this.dono = dono;
		}

		public Conta (int n, Pessoa p) {
			if (p.getConta() != null) {
				System.out.println("Essa pessoa já possui conta!");
				System.exit(1);
			}
			numero = n;
			dono = p;
			dono.setConta(this);
			quantidade++;
		}
		
		public int getNumero() {
			return numero;
		}
		
		public String getExtrato() {
			return extrato;
		}
		
		public double getSaldo() {
			return saldo;
		}

		void credito(double val) {
			if (val > 0) {
			  saldo = saldo + val;
			  extrato = extrato + "Credito: " + val + ". Saldo: " + saldo + "\n";
			} else {
				System.out.println("Valor invalido.");
			}
		}
		
		public void setSaldo(double saldo) {
			this.saldo = saldo;
		}

		public void setExtrato(String extrato) {
			this.extrato = extrato;
		}

		abstract boolean debito(double val, String senha);
		
		public JSONObject pessoaToJson(Pessoa pessoa) {
	        JSONObject jsonPessoa = new JSONObject();
	        jsonPessoa.put("name", pessoa.getNome());
	        jsonPessoa.put("cpf", pessoa.getCpf());
	        jsonPessoa.put("senha", pessoa.getSenha());
	        
	        return jsonPessoa;
	    }
}
