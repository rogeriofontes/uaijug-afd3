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

import br.com.uaijug.appex.appex.web.dto.ClientTypeDTO;
import br.com.uaijug.appex.appex.web.support.ClientTypeSupport;

@RestController
@RequestMapping(path = "/client-types")
public class ClientTypeResources {
	private static final Logger log = LogManager.getLogger(ClientTypeResources.class);

	@Autowired
	private ClientTypeSupport clientTypeSupport;

	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<List<ClientTypeDTO>> getAll() {
		List<ClientTypeDTO> clientTypes = clientTypeSupport.list();
		log.info("Total Retornado: " + clientTypes.size());
		return new ResponseEntity<>(clientTypes, HttpStatus.OK);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@CacheEvict(value = "clientTypesInCache", allEntries = true)
	@Timed
	public ResponseEntity<ClientTypeDTO> add(@Valid @RequestBody ClientTypeDTO clientTypeDTO) {
		ClientTypeDTO result = clientTypeSupport.convertToCreate(clientTypeDTO);
		return new ResponseEntity<ClientTypeDTO>(result, HttpStatus.CREATED);
	}

	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@CacheEvict(value = "clientTypesInCache", allEntries = true)
	@Timed
	public ResponseEntity<ClientTypeDTO> change(@PathVariable Long id, @RequestBody ClientTypeDTO clientTypeDTO) {
		ClientTypeDTO result = clientTypeSupport.convertToChange(id, clientTypeDTO);
		return new ResponseEntity<ClientTypeDTO>(result, HttpStatus.OK);
	}

	@DeleteMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@CacheEvict(value = "clientTypesInCache", allEntries = true)
	@Timed
	public ResponseEntity<?> remove(@PathVariable Long id) {
		clientTypeSupport.remove(id);
		return new ResponseEntity<>("Dados Deletados!", HttpStatus.OK);
	}

	@GetMapping(path = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@Timed
	public ResponseEntity<ClientTypeDTO> findByName(@PathVariable("name") String name) {
		ClientTypeDTO clientTypeDTO = clientTypeSupport.convertToFindByName(name);
		return new ResponseEntity<ClientTypeDTO>(clientTypeDTO, HttpStatus.OK);
	}
}
