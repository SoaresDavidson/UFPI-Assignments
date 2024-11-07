package daviSousaSoares.banco;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

public class UsoDeConta {

	public static void main(String[] args) {
		EstruturaArquivo v = new EstruturaArquivo();
		Banco b = new Banco(v);
		
		Pessoa p1 = new Pessoa(123, "123");
		Pessoa p2 = new Pessoa(456, "456");

		Conta c1 = new ContaComum(1, p1);
		Conta c2 = new ContaComum(2, p2);
		b.extrato(1);
		b.cadastrar(c1);
		b.cadastrar(c2);
		b.deposito(1, 101);
		assertEquals(101, b.saldo(1), 0.001);
		b.saque(1, 99, "123");
		assertEquals(2, b.saldo(1), 0.001);
	}
}
