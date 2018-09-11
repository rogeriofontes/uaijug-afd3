package br.com.uaijug.appex.appex.web.convert.to;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.uaijug.appex.appex.model.domain.Product;
import br.com.uaijug.appex.appex.web.dto.ProductDTO;

@Component
public class ProductToConverter implements Converter<Product, ProductDTO> {

	@Override
	public ProductDTO convert(Product source) {
		ProductDTO target = new ProductDTO();

		target.setId(source.getId());
		target.setName(source.getName());

		return target;

	}


}
