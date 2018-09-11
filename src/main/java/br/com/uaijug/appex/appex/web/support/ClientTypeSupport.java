package br.com.uaijug.appex.appex.web.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import br.com.uaijug.appex.appex.model.domain.ClientType;
import br.com.uaijug.appex.appex.model.service.ClientTypeService;
import br.com.uaijug.appex.appex.web.dto.ClientTypeDTO;

@Component
public class ClientTypeSupport {

	private static final Logger log = LogManager.getLogger(ClientTypeSupport.class);

	@Autowired
	private ClientTypeService service;

	@Autowired
	private ConversionService clientTypeConvert;

	public ClientTypeDTO convertToFindByName(String name) {
		Optional<ClientType> clientType = service.findByName(name);
		ClientTypeDTO founded = clientTypeConvert.convert(clientType.get(), ClientTypeDTO.class);
		log.info("User: " + founded.toString());
		return founded;
	}

	public List<ClientTypeDTO> list() {
		List<ClientTypeDTO> clientTypes = new ArrayList<>();
		service.listAll().forEach(clientType -> {
			ClientTypeDTO saved = clientTypeConvert.convert(clientType, ClientTypeDTO.class);
			clientTypes.add(saved);
		});
		return clientTypes;
	}

	public ClientTypeDTO convertToCreate(ClientTypeDTO clientTypeDTO) {
		ClientType clientType = clientTypeConvert.convert(clientTypeDTO, ClientType.class);
		ClientType result = service.save(clientType);
		return clientTypeConvert.convert(result, ClientTypeDTO.class);
	}

	public ClientTypeDTO convertToChange(Long id, ClientTypeDTO clientTypeDTO) {
		ClientType clientType = clientTypeConvert.convert(clientTypeDTO, ClientType.class);
		ClientType result = service.update(id, clientType);
		return clientTypeConvert.convert(result, ClientTypeDTO.class);
	}

	public void remove(Long id) {
		service.remove(id);	
	}
}
