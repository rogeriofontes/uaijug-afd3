package br.com.uaijug.appex.appex.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.uaijug.appex.appex.dao.ClienteDAO;
import br.com.uaijug.appex.appex.exception.CPFValidationException;
import br.com.uaijug.appex.appex.model.domain.Cliente;

@Service
public class ClienteService {

	@Autowired
	private ClienteDAO dao;

	public void salvar(Cliente cliente) {
		if (cliente.getCpf().length() < 11) {
			throw new CPFValidationException("Erro na validação do cpf!");
		} else {
			dao.salvar(cliente);
		}
	}

	public List<Cliente> listar() {
		return dao.listar();
	}

	public void alterar(int id, Cliente cliente) {
		dao.alterar(id, cliente);
	}

	public void delete(int id) {
		dao.delete(id);
	}

}
