package br.com.uaijug.appex.appex.web.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.uaijug.appex.appex.model.domain.Order;
import br.com.uaijug.appex.appex.model.service.OrderService;

@Component
public class OrderSupport {

	private static final Logger log = LogManager.getLogger(OrderSupport.class);

	@Autowired
	private OrderService service;

	public Order convertToFindByName(String name) {
		Optional<Order> order = service.findByName(name);
		log.info("User: " + order.get().toString());
		return order.get();
	}

	public List<Order> list() {
		List<Order> orders = new ArrayList<>();
		service.listAll().forEach(order -> {
			orders.add(order);
		});
		return orders;
	}

	public Order convertToCreate(Order order) {
		return service.save(order);
	}

	public Order convertToChange(Long id, Order order) {
		return service.update(id, order);
	}

	public void remove(Long id) {
		service.remove(id);
	}
}
