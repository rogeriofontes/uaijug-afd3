package br.com.uaijug.appex.appex.model.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.uaijug.appex.appex.exception.ResourceFoundException;
import br.com.uaijug.appex.appex.exception.ResourceNotFoundException;
import br.com.uaijug.appex.appex.model.domain.Order;
import br.com.uaijug.appex.appex.model.repository.OrderRepository;
import br.com.uaijug.appex.appex.model.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	private static final Logger log = LogManager.getLogger(OrderServiceImpl.class);

	@Autowired
	private OrderRepository orderRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.uaijug.appex.appex.model.service.OrdereSerivce#salvar(br.com.uaijug.
	 * appex.appex.model.domain.Ordere)
	 */
	@Override
	public Order save(Order order) {
		if (order != null) {
			Optional<Order> result = findByName(order.getName());
			if (!result.isPresent()) {
					return orderRepository.save(order);
			} else {
				throw new ResourceFoundException("Ordere já existe");
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.uaijug.appex.appex.model.service.OrdereSerivce#listar()
	 */
	@Override
	@Cacheable("ordersInCache")
	public List<Order> listAll() {
		return orderRepository.findAll();
	}

	@Override
	@Cacheable("ordersInCache")
	public Page<Order> findAllPageable(Pageable pageable) {
		return orderRepository.findAll(pageable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.uaijug.appex.appex.model.service.OrdereSerivce#alterar(java.lang.
	 * Long, br.com.uaijug.appex.appex.model.domain.Ordere)
	 */
	@Override
	public Order update(Long id, Order order) {
		if (id != null && order != null) {
			Optional<Order> result = findById(id);
			if (result.isPresent()) {
					result.get().updade(id, order);
					log.info("Objeto Gravado!");
					return orderRepository.save(result.get());
			} else {
				throw new ResourceNotFoundException("Ordere não existe");
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.uaijug.appex.appex.model.service.OrdereSerivce#buscarPorId(java.lang.
	 * Long)
	 */
	@Override
	public Optional<Order> findById(Long id) {
		return orderRepository.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.uaijug.appex.appex.model.service.OrdereSerivce#buscarPorId(java.lang.
	 * Long)
	 */
	@Override
	public Optional<Order> findByName(String name) {
		return orderRepository.findByName(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.uaijug.appex.appex.model.service.OrdereSerivce#delete(java.lang.Long)
	 */
	@Override
	public void remove(Long id) {
		orderRepository.deleteById(id);
	}

}
