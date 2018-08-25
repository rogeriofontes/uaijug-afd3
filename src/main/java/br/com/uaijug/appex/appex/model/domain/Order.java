package br.com.uaijug.appex.appex.model.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product_order")
public class Order extends AudityEntity {
	private static final long serialVersionUID = 6794082089100611290L;

	@NotNull
	private String name;
	@NotNull
	@Column(name = "create_date")
	private Date create;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "item_order", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"))
	public Set<Product> products;

	public String getName() {
		return name;
	}

	public void setNome(String name) {
		this.name = name;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Date getCreate() {
		return create;
	}

	public void setCreate(Date create) {
		this.create = create;
	}

	public void updade(Long id, Order order) {
		super.setId(id);
		this.name = order.getName();
		this.create = order.getCreate();
		this.products = order.getProducts();
	}

}
