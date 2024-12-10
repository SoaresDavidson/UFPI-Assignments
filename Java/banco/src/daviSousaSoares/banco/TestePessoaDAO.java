package daviSousaSoares.banco;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

public class TestePessoaDAO {

	@Test
	public void testarInserirRecuperar() throws ClassNotFoundException, SQLException, PessoaJaCadastrada, PessoaInexistente {
		PessoaDAO dao = new PessoaDAO();
		dao.apagarTodos();
		
		Pessoa p1 = new Pessoa(1, "Fulano 1", "");
		Pessoa p2 = new Pessoa(2, "Fulano 2", "");
		Pessoa p3 = new Pessoa(3, "Fulano 3", "");
		
		dao.inserir(p1);
		dao.inserir(p2);
		dao.inserir(p3);
		
		try {
			dao.inserir(p1);
			fail("Não deveria ter cadastrado novamente pessoa 1");
		} catch (PessoaJaCadastrada e) {
			// Correto! Impediu cadastro novamente!
		}
		
		Pessoa outra1 = dao.recuperar(1);
		assertEquals(outra1.getNome(), p1.getNome());
		Pessoa outra2 = dao.recuperar(2);
		assertEquals(outra2.getNome(), p2.getNome());
		Pessoa outra3 = dao.recuperar(3);
		assertEquals(outra3.getNome(), p3.getNome());

		try {
			Pessoa outraX = dao.recuperar(33);
			fail("Recuperou uma pessoa que nao foi cadastrada");
		} catch (PessoaInexistente e) {
			// Correto. Pessoa realmente nao foi cadastrada!
		}
		
		ArrayList<Pessoa> array = dao.recuperarTodos();
		assertEquals(3, array.size());
	}
}
