package br.com.uaijug.appex.appex.web.convert.from;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.uaijug.appex.appex.model.domain.ClientType;
import br.com.uaijug.appex.appex.web.dto.ClientTypeDTO;

@Component
public class ClientTypeFromConverter implements Converter<ClientTypeDTO, ClientType> {

	@Override
	public ClientType convert(ClientTypeDTO source) {
		ClientType target = new ClientType();

		target.setId(source.getId());
		target.setName(source.getName());

		return target;

	}

}
