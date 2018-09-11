package br.com.uaijug.appex.appex.model.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "product_order")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = { "name", "create", "products" })
@Data
public class Order extends AudityEntity {
	private static final long serialVersionUID = 6794082089100611290L;

	@NotNull
	@Getter
	@Setter
	private String name;
	
	@NotNull
	@Column(name = "create_date")
	@Getter
	@Setter
	private Date create;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	@Getter
	@Setter
	private Client client;

	@ManyToMany
	@JoinTable(name = "item_order", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"))
	@Getter
	@Setter
	public Set<Product> products;
	
	@NotNull
	@Getter
	@Setter
	private Integer total;
	
	public void updade(Long id, Order order) {
		super.setId(id);
		this.name = order.getName();
		this.create = order.getCreate();
		this.products = order.getProducts();
		this.total = order.getTotal();
	}

}
