package br.com.uaijug.appex.appex.resources;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.uaijug.appex.appex.model.domain.Order;
import br.com.uaijug.appex.appex.model.service.OrderService;

@RestController
@RequestMapping(path = "/orders")
public class OrderResources {
	private static final Logger log = LogManager.getLogger(OrderResources.class);

	@Autowired
	private OrderService service;

	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<List<Order>> getAll() {
		List<Order> orders = service.listAll();
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@CacheEvict(value = "ordersInCache", allEntries = true)
	@Timed
	public ResponseEntity<Order> add(@Valid @RequestBody Order order) {
		Order result = service.save(order);
		return new ResponseEntity<Order>(result, HttpStatus.CREATED);
	}

	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@CacheEvict(value = "ordersInCache", allEntries = true)
	@Timed
	public ResponseEntity<Order> change(@PathVariable Long id, @RequestBody Order order) {
		Order result = service.update(id, order);
		return new ResponseEntity<Order>(result, HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@CacheEvict(value = "ordersInCache", allEntries = true)
	@Timed
	public ResponseEntity<?> remove(@PathVariable Long id) {
		service.remove(id);
		return new ResponseEntity<>("Dados Deletados!", HttpStatus.OK);
	}

	@GetMapping(path = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<Order> getOrderByName(@PathVariable("name") String name) {
		Optional<Order> order = service.findByName(name);

		log.info("User: " + order.get().toString());
		return new ResponseEntity<Order>(order.get(), HttpStatus.OK);
	}
}
