package br.com.uaijug.appex.appex.web.convert.from;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.uaijug.appex.appex.model.domain.Client;
import br.com.uaijug.appex.appex.model.domain.ClientType;
import br.com.uaijug.appex.appex.web.dto.ClientDTO;

@Component
public class ClientFromConverter implements Converter<ClientDTO, Client> {

	@Autowired
	private ConversionService service;

	@Override
	public Client convert(ClientDTO source) {
		Client target = new Client();

		target.setId(source.getId());
		target.setName(source.getName());
		target.setEmail(source.getEmail());
		target.setAddress(source.getAddress());
		ClientType clienteType = service.convert(source.getClientType(), ClientType.class);
		target.setClientType(clienteType);
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
