package br.com.uaijug.appex.appex.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.uaijug.appex.appex.model.domain.ClientType;

public interface ClientTypeService {

	ClientType save(ClientType clientType);

	List<ClientType> listAll();

	ClientType update(Long id, ClientType clientType);

	Optional<ClientType> findById(Long id);

	void remove(Long id);

	Optional<ClientType> findByName(String name);

	Page<ClientType> findAllPageable(Pageable pageable);

}