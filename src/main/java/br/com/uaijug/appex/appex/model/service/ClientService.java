package br.com.uaijug.appex.appex.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.uaijug.appex.appex.model.domain.Client;

public interface ClientService {

	Client save(Client client);

	List<Client> listAll();

	Client update(Long id, Client client);

	Optional<Client> findById(Long id);

	void remove(Long id);

	Optional<Client> findByName(String name);

	Page<Client> findAllPageable(Pageable pageable);

}