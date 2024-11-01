package daviSousaSoares.banco;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

public class UsoDeConta {

	public static void main(String[] args) {
		EstruturaArquivo v = new EstruturaArquivo();
		Banco b = new Banco(v);
		
		Pessoa p1 = new Pessoa(123, "123");

		Conta c2 = new Poupanca(2, p1);
		
		b.cadastrar(c2);
		
		b.deposito(2, 100);
		assertEquals(100, b.saldo(2), 0.001);
		b.juros(2, 0.01);
		assertEquals(101, b.saldo(2), 0.001);
	}
}
