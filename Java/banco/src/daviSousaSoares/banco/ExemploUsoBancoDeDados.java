package daviSousaSoares.banco;

import java.sql.*;

public class ExemploUsoBancoDeDados {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver"); 
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/banco", "root", "veredinha2012");
		Statement st = con.createStatement();
		String comando = "";
		for (int i = 1000; i < 10000; i++) {
			comando = "insert into cliente values (" + i + ", \'1000-1000\', 'Cliente" + i + "\')";
			//System.out.println(comando);
			//insert into cliente values (9999, '1000-1000', 'Cliente9999')
			st.execute(comando);
		}
		comando = "select * from cliente";
		ResultSet rs = st.executeQuery(comando);
		while (rs.next()) {
			int cpf = rs.getInt("cpf");
			String tel = rs.getString("telefone");
			String nm = rs.getString("nome");
			System.out.println("CPF: " + cpf + ". Telefone: " + tel + ". Nome: " + nm + ".");
		}
		st.close();
		con.close();
	}
}