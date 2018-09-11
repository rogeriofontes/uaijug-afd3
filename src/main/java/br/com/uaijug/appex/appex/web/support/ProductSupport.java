package br.com.uaijug.appex.appex.web.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import br.com.uaijug.appex.appex.model.domain.Product;
import br.com.uaijug.appex.appex.model.service.ProductService;
import br.com.uaijug.appex.appex.web.dto.ProductDTO;

@Component
public class ProductSupport {

	private static final Logger log = LogManager.getLogger(ProductSupport.class);

	@Autowired
	private ProductService service;

	@Autowired
	private ConversionService productConvert;

	public ProductDTO convertToFindByName(String name) {
		Optional<Product> product = service.findByName(name);
		ProductDTO founded = productConvert.convert(product.get(), ProductDTO.class);
		log.info("User: " + founded.toString());
		return founded;
	}

	public List<ProductDTO> list() {
		List<ProductDTO> products = new ArrayList<>();
		service.listAll().forEach(product -> {
			ProductDTO saved = productConvert.convert(product, ProductDTO.class);
			products.add(saved);
		});
		return products;
	}

	public ProductDTO convertToCreate(ProductDTO productDTO) {
		Product product = productConvert.convert(productDTO, Product.class);
		Product result = service.save(product);
		return productConvert.convert(result, ProductDTO.class);
	}

	public ProductDTO convertToChange(Long id, ProductDTO productDTO) {
		Product product = productConvert.convert(productDTO, Product.class);
		Product result = service.update(id, product);
		return productConvert.convert(result, ProductDTO.class);
	}

	public void remove(Long id) {
		service.remove(id);	
	}
}
