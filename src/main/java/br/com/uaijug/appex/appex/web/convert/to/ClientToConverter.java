package br.com.uaijug.appex.appex.web.convert.to;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.uaijug.appex.appex.model.domain.Client;
import br.com.uaijug.appex.appex.web.dto.ClientDTO;
import br.com.uaijug.appex.appex.web.dto.ClientTypeDTO;

@Component
public class ClientToConverter implements Converter<Client, ClientDTO> {

	@Override
	public ClientDTO convert(Client source) {
		ClientDTO target = new ClientDTO();

		target.setId(source.getId());
		target.setName(source.getName());
		target.setEmail(source.getEmail());
		target.setAddress(source.getAddress());

		if (source.getClientType() != null) {
			ClientTypeDTO dto = new ClientTypeDTO();
			dto.setId(source.getClientType().getId());
			dto.setName(source.getClientType().getName());
			target.setClientType(dto);
		}

		target.setBirthDate(source.getBirthDate());
		target.setPersonType(source.getPersonType());
		target.setPhone(source.getPhone());
		target.setMobile(source.getMobile());
		target.setDocumentRegion(source.getDocumentRegion());
		target.setDocumentId(source.getDocumentId());
		target.setSocialId(source.getSocialId());
		target.setSex(source.getSex());
		target.setNationality(source.getNationality());

		return target;
	}

}
