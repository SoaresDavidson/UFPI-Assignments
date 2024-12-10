package daviSousaSoares.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	private static Connection conexoes[] = null;
	private static int atual = 0;
	private static boolean conectou = false;
	private static final int quantidade = 10;
	
	public static Connection conectar() throws ClassNotFoundException, SQLException {	
		
		if (!conectou) {
	      conexoes = new Connection[quantidade];
		  Class.forName("com.mysql.cj.jdbc.Driver"); 
		  System.out.println(conexoes);
		  for (int i = 0; i < quantidade; i++) {
			conexoes[i] = DriverManager.getConnection("jdbc:mysql://localhost/banco", "root", "12345678");			
		  }
		  conectou = true;
		}
		
		if (atual == quantidade) {
			atual = 0;
		}
		return conexoes[atual++];
	}
	
	private Conexao() {
	}

}
