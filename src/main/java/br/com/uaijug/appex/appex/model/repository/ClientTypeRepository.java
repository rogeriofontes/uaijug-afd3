package br.com.uaijug.appex.appex.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.uaijug.appex.appex.model.domain.ClientType;

public interface ClientTypeRepository extends JpaRepository<ClientType, Long> {
	Optional<ClientType> findByName(String name);
}

