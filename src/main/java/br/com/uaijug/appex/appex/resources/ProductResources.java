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

import br.com.uaijug.appex.appex.model.domain.Product;
import br.com.uaijug.appex.appex.model.service.ProductService;

@RestController
@RequestMapping(path = "/products")
public class ProductResources {
	private static final Logger log = LogManager.getLogger(ProductResources.class);

	@Autowired
	private ProductService service;

	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<List<Product>> getAll() {
		List<Product> products = service.listAll();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@CacheEvict(value = "productsInCache", allEntries = true)
	@Timed
	public ResponseEntity<Product> add(@Valid @RequestBody Product product) {
		Product result = service.save(product);
		return new ResponseEntity<Product>(result, HttpStatus.CREATED);
	}

	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@CacheEvict(value = "productsInCache", allEntries = true)
	@Timed
	public ResponseEntity<Product> change(@PathVariable Long id, @RequestBody Product product) {
		Product result = service.update(id, product);
		return new ResponseEntity<Product>(result, HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@CacheEvict(value = "productsInCache", allEntries = true)
	@Timed
	public ResponseEntity<?> remove(@PathVariable Long id) {
		service.remove(id);
		return new ResponseEntity<>("Dados Deletados!", HttpStatus.OK);
	}

	@GetMapping(path = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<Product> getProductByName(@PathVariable("name") String name) {
		Optional<Product> product = service.findByName(name);

		log.info("Product: " + product.get().toString());
		return new ResponseEntity<Product>(product.get(), HttpStatus.OK);
	}
}
