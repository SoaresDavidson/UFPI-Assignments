package daviSousaSoares.locadora;

public class Cliente {
	
	int cpf;
	String nome;
	
	public Cliente(int c, String nm) {
		cpf = c;
		nome = nm;
	}
	
	public int getCpf() {
		return cpf;
	}
	
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

}
