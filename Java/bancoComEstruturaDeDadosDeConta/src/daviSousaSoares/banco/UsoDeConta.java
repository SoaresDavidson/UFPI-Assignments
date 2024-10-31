package daviSousaSoares.banco;

import java.util.*;

public class UsoDeConta {

	public static void main(String[] args) {
		EstruturaArquivo e = new EstruturaArquivo();
		Banco b = new Banco(e);
		Pessoa p1 = new Pessoa(123, "123");
		Pessoa p2 = new Pessoa(456, "456");
		
		ContaArquivo c1 = new ContaArquivo(1,p1);
		ContaArquivo c2 = new ContaArquivo(2,p2);
		b.cadastrar(c1);
		b.cadastrar(c2);
		if(e.pesquisar(3) != null)
			System.out.println("cu");
		
	}
}
