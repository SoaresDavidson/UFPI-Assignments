package daviSousaSoares.banco;

import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PessoaDAO {
	
	public void inserir(Pessoa p) throws ClassNotFoundException, SQLException, PessoaJaCadastrada {
		Connection con = Conexao.conectar();
		String cmd = "insert into pessoa value (" + p.getCpf() + ", \'" + p.getNome() + "\')";
		Statement st = con.createStatement();
		try {
			Pessoa outra = recuperar(p.getCpf());
			throw new PessoaJaCadastrada(p.getCpf());
		} catch (PessoaInexistente e) {
			st.execute(cmd);
		}
		st.close();
	}
	
	public void alterar(Pessoa p) {
		
	}
	
	public Pessoa recuperar(int cpf) throws SQLException, ClassNotFoundException, PessoaInexistente {
		Connection con = Conexao.conectar();
		String cmd = "select * from pessoa where cpf = " + cpf;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(cmd);
		Pessoa p = null;
		if (rs.next()) {
			String nome = rs.getString("nome");
			p = new Pessoa(cpf, nome, "");
		} else {
			throw new PessoaInexistente(cpf);
		}
		st.close();
		return p;
	}
	
	public ArrayList<Pessoa> recuperarTodos() throws SQLException, ClassNotFoundException {
		Connection con = Conexao.conectar();
		String cmd = "select * from pessoa";
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(cmd);
		Pessoa p = null;
		ArrayList<Pessoa> todos =  new ArrayList<Pessoa>();
		while (rs.next()) {
			int cpf = rs.getInt("cpf");
			String nome = rs.getString("nome");
			p = new Pessoa(cpf, nome);
			todos.add(p);
		}
		st.close();
		return todos;
	}
	
	public void apagar(int cpf) throws SQLException, ClassNotFoundException {
		Connection con = Conexao.conectar();
		String cmd = "delete from pessoa where cpf = " + cpf;
		Statement st = con.createStatement();
		st.execute(cmd);
		st.close();
	}
	
	public void apagarTodos() throws SQLException, ClassNotFoundException {
		Connection con = Conexao.conectar();
		String cmd = "delete from pessoa where cpf > 0 ";
		Statement st = con.createStatement();
		st.execute(cmd);
		st.close();
	}

}
