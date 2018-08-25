package br.com.uaijug.appex.appex.model.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product")
public class Product extends AudityEntity {
	private static final long serialVersionUID = 7790474141975171471L;

	@NotNull
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void updade(Long id, Product product) {
		super.setId(id);
		this.name = product.getName();
	}
}
