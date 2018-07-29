package br.com.uaijug.appex.appex.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.uaijug.appex.appex.model.domain.Client;

@Repository
public class ClientDAO {
	private List<Client> clientes = new ArrayList<>();

	public void salvar(Client cliente) {
		clientes.add(cliente);
	}

	public void alterar(int id, Client cliente) {
		clientes.set(id, cliente);
	}

	public void delete(int id) {
		clientes.remove(id);
	}

	public List<Client> listar() {
		return clientes;
	}

	public static void main(String[] args) {
		Client cliente = new Client();
		cliente.setName("Rogerio Fontes");
		cliente.setEmail("root@localhost.com");
		cliente.setCpf("090889999");

		Client cliente2 = new Client();
		cliente2.setName("Rogerio Fontes");
		cliente2.setEmail("root@localhost.com");
		cliente2.setCpf("090889999");

		ClientDAO dao = new ClientDAO();
		dao.salvar(cliente);
		dao.salvar(cliente2);
		
		Client clienteALt = new Client();
		clienteALt.setName("Rogerio Fontes- Alterado");
		clienteALt.setEmail("root@localhost.com");
		clienteALt.setCpf("09088999933");
		
		dao.alterar(1, clienteALt);
		
		dao.delete(0);

		List<Client> resultadoDaLista = dao.listar();

		for (Client c : resultadoDaLista) {
			System.out.println(c.toString());
		}

	}
}
