package br.com.uaijug.appex.appex.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.uaijug.appex.appex.model.domain.Cliente;

@Repository
public class ClienteDAO {
	private List<Cliente> clientes = new ArrayList<>();

	public void salvar(Cliente cliente) {
		clientes.add(cliente);
	}

	public void alterar(int id, Cliente cliente) {
		clientes.set(id, cliente);
	}

	public void delete(int id) {
		clientes.remove(id);
	}

	public List<Cliente> listar() {
		return clientes;
	}

	public static void main(String[] args) {
		Cliente cliente = new Cliente();
		cliente.setNome("Rogerio Fontes");
		cliente.setEmail("root@localhost.com");
		cliente.setCpf("090889999");

		Cliente cliente2 = new Cliente();
		cliente2.setNome("Rogerio Fontes");
		cliente2.setEmail("root@localhost.com");
		cliente2.setCpf("090889999");

		ClienteDAO dao = new ClienteDAO();
		dao.salvar(cliente);
		dao.salvar(cliente2);
		
		Cliente clienteALt = new Cliente();
		clienteALt.setNome("Rogerio Fontes- Alterado");
		clienteALt.setEmail("root@localhost.com");
		clienteALt.setCpf("09088999933");
		
		dao.alterar(1, clienteALt);
		
		dao.delete(0);

		List<Cliente> resultadoDaLista = dao.listar();

		for (Cliente c : resultadoDaLista) {
			System.out.println(c.toString());
		}

	}
}
