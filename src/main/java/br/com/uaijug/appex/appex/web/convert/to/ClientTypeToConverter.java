package br.com.uaijug.appex.appex.web.convert.to;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.uaijug.appex.appex.model.domain.ClientType;
import br.com.uaijug.appex.appex.web.dto.ClientTypeDTO;

@Component
public class ClientTypeToConverter implements Converter<ClientType, ClientTypeDTO> {

	@Override
	public ClientTypeDTO convert(ClientType source) {
		ClientTypeDTO target = new ClientTypeDTO();

		target.setId(source.getId());
		target.setName(source.getName());

		return target;
	}

}
