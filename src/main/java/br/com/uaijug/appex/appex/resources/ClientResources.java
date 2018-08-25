package br.com.uaijug.appex.appex.resources;

import java.util.List;
import java.util.Optional;

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

import br.com.uaijug.appex.appex.model.domain.Client;
import br.com.uaijug.appex.appex.model.service.ClientService;

@RestController
@RequestMapping(path = "/clients")
public class ClientResources {
	private static final Logger log = LogManager.getLogger(ClientResources.class);

	@Autowired
	private ClientService service;

	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<List<Client>> getAll() {
		List<Client> clients = service.listAll();
		return new ResponseEntity<>(clients, HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@CacheEvict(value = "clientsInCache", allEntries = true)
	@Timed
	public ResponseEntity<Client> add(@Valid @RequestBody Client client) {
		Client result = service.save(client);
		return new ResponseEntity<Client>(result, HttpStatus.CREATED);
	}

	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@CacheEvict(value = "clientsInCache", allEntries = true)
	@Timed
	public ResponseEntity<Client> change(@PathVariable Long id, @RequestBody Client client) {
		Client result = service.update(id, client);
		return new ResponseEntity<Client>(result, HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@CacheEvict(value = "clientsInCache", allEntries = true)
	@Timed
	public ResponseEntity<?> remove(@PathVariable Long id) {
		service.remove(id);
		return new ResponseEntity<>("Dados Deletados!", HttpStatus.OK);
	}

	@GetMapping(path = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<Client> getUserByUsername(@PathVariable("name") String name) {
		Optional<Client> client = service.findByName(name);

		log.info("User: " + client.get().toString());
		return new ResponseEntity<Client>(client.get(), HttpStatus.OK);
	}
}
