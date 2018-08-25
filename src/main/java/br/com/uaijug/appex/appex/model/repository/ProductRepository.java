package br.com.uaijug.appex.appex.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.uaijug.appex.appex.model.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	Optional<Product> findByName(String name);
}

