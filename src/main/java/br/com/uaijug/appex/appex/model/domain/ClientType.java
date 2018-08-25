package br.com.uaijug.appex.appex.model.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "client_type")
public class ClientType extends AudityEntity {
	private static final long serialVersionUID = -2647716282886497886L;

	@NotNull
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void updade(Long id, ClientType clientType) {
		super.setId(id);
		this.name = clientType.getName();
	}

}
