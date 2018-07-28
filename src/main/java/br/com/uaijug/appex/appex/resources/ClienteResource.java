package br.com.uaijug.appex.appex.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.uaijug.appex.appex.dao.ClienteDAO;
import br.com.uaijug.appex.appex.model.domain.Cliente;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	private ClienteDAO dao;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<Cliente> getAll() {
		return dao.listar();
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public void add(@RequestBody Cliente cliente) {
		dao.salvar(cliente);
	}
}
