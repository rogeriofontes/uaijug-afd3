package br.com.uaijug.appex.appex.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.uaijug.appex.appex.model.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	Optional<Order> findByName(String name);
}

