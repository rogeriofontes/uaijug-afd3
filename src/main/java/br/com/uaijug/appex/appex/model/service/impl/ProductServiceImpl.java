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
import br.com.uaijug.appex.appex.model.domain.Product;
import br.com.uaijug.appex.appex.model.repository.ProductRepository;
import br.com.uaijug.appex.appex.model.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger log = LogManager.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductRepository productRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.uaijug.appex.appex.model.service.ProductSerivce#salvar(br.com.uaijug.
	 * appex.appex.model.domain.Product)
	 */
	@Override
	public Product save(Product product) {
		if (product != null) {
			Optional<Product> result = findByName(product.getName());
			if (!result.isPresent()) {
				return productRepository.save(product);
			} else {
				throw new ResourceFoundException("Product já existe");
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.uaijug.appex.appex.model.service.ProductSerivce#listar()
	 */
	@Override
	@Cacheable("productsInCache")
	public List<Product> listAll() {
		return productRepository.findAll();
	}

	@Override
	@Cacheable("productsInCache")
	public Page<Product> findAllPageable(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.uaijug.appex.appex.model.service.ProductSerivce#alterar(java.lang.
	 * Long, br.com.uaijug.appex.appex.model.domain.Product)
	 */
	@Override
	public Product update(Long id, Product product) {
		if (id != null && product != null) {
			Optional<Product> result = findById(id);
			if (result.isPresent()) {
					result.get().updade(id, product);
					log.info("Objeto Gravado!");
					return productRepository.save(result.get());
			} else {
				throw new ResourceNotFoundException("Product não existe");
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.uaijug.appex.appex.model.service.ProductSerivce#buscarPorId(java.
	 * lang. Long)
	 */
	@Override
	public Optional<Product> findById(Long id) {
		return productRepository.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.uaijug.appex.appex.model.service.ProductSerivce#buscarPorId(java.
	 * lang. Long)
	 */
	@Override
	public Optional<Product> findByName(String name) {
		return productRepository.findByName(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.uaijug.appex.appex.model.service.ProductSerivce#delete(java.lang.
	 * Long)
	 */
	@Override
	public void remove(Long id) {
		productRepository.deleteById(id);
	}

}
