package br.com.uaijug.appex.appex.web.resources;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.uaijug.appex.appex.web.dto.ClientDTO;
import br.com.uaijug.appex.appex.web.support.ClientSupport;

@RestController
@RequestMapping(path = "/clients")
public class ClientResources {
	private static final Logger log = LogManager.getLogger(ClientResources.class);

	@Autowired
	private ClientSupport clientSupport;

	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<List<ClientDTO>> getAll() {
		List<ClientDTO> clients = clientSupport.list();
		log.info("Total de clientes Buscados" + clients.size());
		return new ResponseEntity<>(clients, HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@CacheEvict(value = "clientsInCache", allEntries = true)
	@Timed
	public ResponseEntity<ClientDTO> add(@Valid @RequestBody ClientDTO clientDTO) {
		ClientDTO saved = clientSupport.convertToCreate(clientDTO);
		return new ResponseEntity<ClientDTO>(saved, HttpStatus.CREATED);
	}

	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@CacheEvict(value = "clientsInCache", allEntries = true)
	@Timed
	public ResponseEntity<ClientDTO> change(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
		ClientDTO saved = clientSupport.convertToChange(id, clientDTO);
		return new ResponseEntity<ClientDTO>(saved, HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@CacheEvict(value = "clientsInCache", allEntries = true)
	@Timed
	public ResponseEntity<?> remove(@PathVariable Long id) {
		clientSupport.remove(id);
		return new ResponseEntity<>("Dados Deletados!", HttpStatus.OK);
	}

	@GetMapping(path = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<ClientDTO> findByName(@PathVariable("name") String name) {
		ClientDTO founded = clientSupport.convertToFindByName(name);
		return new ResponseEntity<ClientDTO>(founded, HttpStatus.OK);
	}
}
