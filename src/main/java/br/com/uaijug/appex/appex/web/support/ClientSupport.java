package br.com.uaijug.appex.appex.web.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import br.com.uaijug.appex.appex.model.domain.Client;
import br.com.uaijug.appex.appex.model.service.ClientService;
import br.com.uaijug.appex.appex.web.dto.ClientDTO;

@Component
public class ClientSupport {

	private static final Logger log = LogManager.getLogger(ClientSupport.class);

	@Autowired
	private ClientService service;

	@Autowired
	private ConversionService clientConvertDTO;
	
	@Autowired
	private ConversionService clientConvert;

	public ClientDTO convertToFindByName(String name) {
		Optional<Client> client = service.findByName(name);
		ClientDTO founded = clientConvertDTO.convert(client.get(), ClientDTO.class);
		log.info("User: " + founded.toString());
		return founded;
	}

	public List<ClientDTO> list() {
		List<ClientDTO> clients = new ArrayList<>();
		service.listAll().forEach(client -> {
			ClientDTO saved = clientConvertDTO.convert(client, ClientDTO.class);
			clients.add(saved);
		});
		return clients;
	}

	public ClientDTO convertToCreate(ClientDTO clientDTO) {
		Client client = getConverterDTO(clientDTO);
		Client saved = service.save(client);
		ClientDTO result = getConverter(saved);
		return result;
	}

	private Client getConverterDTO(ClientDTO clientDTO) {
		return clientConvert.convert(clientDTO, Client.class);
	}

	private ClientDTO getConverter(Client client) {
		return clientConvertDTO.convert(client, ClientDTO.class);
	}

	public ClientDTO convertToChange(Long id, ClientDTO clientDTO) {
		Client client = getConverterDTO(clientDTO);
		Client result = service.update(id, client);
		return clientConvertDTO.convert(result, ClientDTO.class);
	}

	public void remove(Long id) {
		service.remove(id);
	}
}
