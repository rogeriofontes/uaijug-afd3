package br.com.uaijug.appex.appex.model.service;

import java.util.List;
import java.util.Optional;

import br.com.uaijug.appex.appex.model.domain.Cliente;

public interface ClienteService {

	void salvar(Cliente cliente);

	List<Cliente> listar();

	void alterar(Long id, Cliente cliente);

	Optional<Cliente> buscarPorId(Long id);

	void delete(Long id);

	Optional<Cliente> buscarPorNome(String nome);

}