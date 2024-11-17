package daviSousaSoares.locadora;

import java.util.ArrayList;

public class LocadoraDoPedro implements Locadora {
	
	ArrayList<Veiculo> frota = new ArrayList<Veiculo>();
	ArrayList<Cliente> clientes = new ArrayList<Cliente>();

	@Override
	public void inserir(Veiculo v) {
		Veiculo veiculo = pesquisar(v.getPlaca());
		if (veiculo == null) {
			frota.add(v);
		}		
	}

	@Override
	public void inserir(Cliente c) {
		Cliente cli = pesquisar(c.getCpf());
		if (cli == null) {
			clientes.add(c);
		}		
	}

	@Override
	public Veiculo pesquisar(String placa) {
		for (Veiculo v : frota) {
			if (v.getPlaca() == placa) {
				return v;
			}
		}
		return null;
	}
	
	public Cliente pesquisar(int cpf) {
		for (Cliente c : clientes) {
			if (c.getCpf() == cpf) {
				return c;
			}
		}
		return null;
	}

	@Override
	public ArrayList<Veiculo> pesquisarMoto(int cilindrada) {
		ArrayList<Veiculo> lista = new ArrayList<Veiculo>();
		for (Veiculo v : frota) {
			if (v instanceof Moto m) {
				if (m.getCilindrada() >= cilindrada) {
					lista.add(m);
				}
			}
		}
		return lista;
	}

	@Override
	public ArrayList<Veiculo> pesquisarCarro(int tipoCarro) {
		ArrayList<Veiculo> lista = new ArrayList<Veiculo>();
		for (Veiculo v : frota) {
			if (v instanceof Carro m) {
				if (m.getTipo() == tipoCarro) {
					lista.add(m);
				}
			}
		}
		return lista;
	}

	@Override
	public ArrayList<Veiculo> pesquisarCaminhao(int carga) {
		ArrayList<Veiculo> lista = new ArrayList<Veiculo>();
		for (Veiculo v : frota) {
			if (v instanceof Caminhao m) {
				if (m.getCarga() >= carga) {
					lista.add(m);
				}
			}
		}
		return lista;
	}

	@Override
	public ArrayList<Veiculo> pesquisarOnibus(int passageiros) {
		ArrayList<Veiculo> lista = new ArrayList<Veiculo>();
		for (Veiculo v : frota) {
			if (v instanceof Onibus m) {
				if (m.getPassageiros() >= passageiros) {
					lista.add(m);
				}
			}
		}
		return lista;
	}

	@Override
	public double calcularAluguel(String placa, int dias) {
		Veiculo v = pesquisar(placa);
		return (v.valorDiaria + v.seguro()) * dias;
	}

	@Override
	public void registrarAluguel(String placa, int dias, Cliente c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registrarDevolucao(String placa, Cliente c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void depreciarVeiculos(int tipo, double taxaDepreciacao) {
		for (Veiculo v : frota) {
			if (tipo == 1) {
				v.valorBem -= v.valorBem * taxaDepreciacao;
			}
			else if (tipo == 2) {
				v.valorBem -= v.valorBem * taxaDepreciacao;
			}
			else if (tipo == 3) {
				v.valorBem -= v.valorBem * taxaDepreciacao;
			}
			else if (tipo == 4) {
				v.valorBem -= v.valorBem * taxaDepreciacao;
			}
		}		
	}

	@Override
	public void aumentarDiaria(int tipo, double taxaAumento) {
		for (Veiculo v : frota) {
			if (tipo == 1) {
				v.valorDiaria += v.valorDiaria * taxaAumento;
			}
			else if (tipo == 2) {
				v.valorDiaria += v.valorDiaria * taxaAumento;
			}
			else if (tipo == 3) {
				v.valorDiaria += v.valorDiaria * taxaAumento;
			}
			else if (tipo == 4) {
				v.valorDiaria += v.valorDiaria * taxaAumento;
			}
		}
		
	}

	@Override
	public double faturamentoTotal(int tipo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int quantidadeTotalDeDiarias(int tipo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
