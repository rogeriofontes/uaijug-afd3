package br.com.uaijug.appex.appex.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.uaijug.appex.appex.model.domain.Product;

public interface ProductService {

	Product save(Product product);

	List<Product> listAll();

	Product update(Long id, Product product);

	Optional<Product> findById(Long id);

	void remove(Long id);

	Optional<Product> findByName(String name);

	Page<Product> findAllPageable(Pageable pageable);

}