package br.com.uaijug.appex.appex.model.domain;

import javax.persistence.Entity;
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
@Table(name = "client_type")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, of = { "name" })
@Data
public class ClientType extends AudityEntity {
	private static final long serialVersionUID = -2647716282886497886L;

	@NotNull
	@Getter
	@Setter
	private String name;

	public void updade(Long id, ClientType clientType) {
		super.setId(id);
		this.name = clientType.getName();
	}

}
