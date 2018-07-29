package br.com.uaijug.appex.appex.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.uaijug.appex.appex.model.domain.Cliente;
import br.com.uaijug.appex.appex.model.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;

	// @RequestMapping(method = RequestMethod.GET, produces =
	// MediaType.APPLICATION_JSON_UTF8_VALUE)
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	// @ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<List<Cliente>> getAll() {
		List<Cliente> clientes = service.listar();
		return new ResponseEntity<>(clientes, HttpStatus.OK);
	}

	// @RequestMapping(method = RequestMethod.POST, consumes =
	// MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	// @ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> add(@RequestBody Cliente cliente) {
		service.salvar(cliente);
		return new ResponseEntity<>("Dados Criados!", HttpStatus.CREATED);
	}

	// @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes =
	// MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	// @ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> change(@PathVariable int id, @RequestBody Cliente cliente) {
		service.alterar(id, cliente);
		return new ResponseEntity<>("Dados Alterados!", HttpStatus.OK);
	}

	// @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes =
	// MediaType.APPLICATION_JSON_UTF8_VALUE)
	@DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	//@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> remove(@PathVariable int id) {
		service.delete(id);
		return new ResponseEntity<>("Dados Deletados!", HttpStatus.OK);
	}
}
