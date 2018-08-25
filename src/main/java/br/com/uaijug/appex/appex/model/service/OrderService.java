package br.com.uaijug.appex.appex.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.uaijug.appex.appex.model.domain.Order;

public interface OrderService {

	Order save(Order order);

	List<Order> listAll();

	Order update(Long id, Order order);

	Optional<Order> findById(Long id);

	void remove(Long id);

	Optional<Order> findByName(String name);

	Page<Order> findAllPageable(Pageable pageable);

}