package br.com.uaijug.appex.appex.web.resources;

import java.util.List;

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

import br.com.uaijug.appex.appex.web.dto.ProductDTO;
import br.com.uaijug.appex.appex.web.support.ProductSupport;

@RestController
@RequestMapping(path = "/products")
public class ProductResources {
	private static final Logger log = LogManager.getLogger(ProductResources.class);

	@Autowired
	private ProductSupport productSupport;

	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<List<ProductDTO>> getAll() {
		List<ProductDTO> productDTOs = productSupport.list();
		return new ResponseEntity<>(productDTOs, HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@CacheEvict(value = "productsInCache", allEntries = true)
	@Timed
	public ResponseEntity<ProductDTO> add(@Valid @RequestBody ProductDTO productDTO) {
		ProductDTO result = productSupport.convertToCreate(productDTO);
		return new ResponseEntity<ProductDTO>(result, HttpStatus.CREATED);
	}

	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@CacheEvict(value = "productsInCache", allEntries = true)
	@Timed
	public ResponseEntity<ProductDTO> change(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
		ProductDTO result = productSupport.convertToChange(id, productDTO);
		return new ResponseEntity<ProductDTO>(result, HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@CacheEvict(value = "productsInCache", allEntries = true)
	@Timed
	public ResponseEntity<?> remove(@PathVariable Long id) {
		productSupport.remove(id);
		return new ResponseEntity<>("Dados Deletados!", HttpStatus.OK);
	}

	@GetMapping(path = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<ProductDTO> getProductDTOByName(@PathVariable("name") String name) {
		ProductDTO productDTO = productSupport.convertToFindByName(name);

		log.info("ProductDTO: " + productDTO.toString());
		return new ResponseEntity<ProductDTO>(productDTO, HttpStatus.OK);
	}
}
