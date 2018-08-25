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

import br.com.uaijug.appex.appex.model.domain.ClientType;
import br.com.uaijug.appex.appex.model.service.ClientTypeService;

@RestController
@RequestMapping(path = "/client-types")
public class ClientTypeResources {
	private static final Logger log = LogManager.getLogger(ClientTypeResources.class);

	@Autowired
	private ClientTypeService service;

	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<List<ClientType>> getAll() {
		List<ClientType> clientTypes = service.listAll();
		return new ResponseEntity<>(clientTypes, HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@CacheEvict(value = "clientTypesInCache", allEntries = true)
	@Timed
	public ResponseEntity<ClientType> add(@Valid @RequestBody ClientType clientType) {
		ClientType result = service.save(clientType);
		return new ResponseEntity<ClientType>(result, HttpStatus.CREATED);
	}

	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@CacheEvict(value = "clientTypesInCache", allEntries = true)
	@Timed
	public ResponseEntity<ClientType> change(@PathVariable Long id, @RequestBody ClientType clientType) {
		ClientType result = service.update(id, clientType);
		return new ResponseEntity<ClientType>(result, HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@CacheEvict(value = "clientTypesInCache", allEntries = true)
	@Timed
	public ResponseEntity<?> remove(@PathVariable Long id) {
		service.remove(id);
		return new ResponseEntity<>("Dados Deletados!", HttpStatus.OK);
	}

	@GetMapping(path = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<ClientType> getUserByUsername(@PathVariable("name") String name) {
		Optional<ClientType> clientType = service.findByName(name);

		log.info("User: " + clientType.get().toString());
		return new ResponseEntity<ClientType>(clientType.get(), HttpStatus.OK);
	}
}
