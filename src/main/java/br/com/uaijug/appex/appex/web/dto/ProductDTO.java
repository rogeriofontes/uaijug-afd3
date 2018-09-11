package br.com.uaijug.appex.appex.web.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(callSuper = true, of = { "name" })
@Data
public class ProductDTO implements Serializable {
	private static final long serialVersionUID = -4105352897909511686L;

	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	private String name;

}
