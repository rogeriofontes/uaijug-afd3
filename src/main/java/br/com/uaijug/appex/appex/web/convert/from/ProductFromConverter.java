package br.com.uaijug.appex.appex.web.convert.from;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.uaijug.appex.appex.model.domain.Product;
import br.com.uaijug.appex.appex.web.dto.ProductDTO;

@Component
public class ProductFromConverter implements Converter<ProductDTO, Product> {

	@Override
	public Product convert(ProductDTO source) {
		Product target = new Product();

		target.setId(source.getId());
		target.setName(source.getName());

		return target;
	}

}
