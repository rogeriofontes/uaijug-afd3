package br.com.uaijug.appex.appex.model.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.uaijug.appex.appex.exception.CPFValidationException;
import br.com.uaijug.appex.appex.exception.ResourceFoundException;
import br.com.uaijug.appex.appex.exception.ResourceNotFoundException;
import br.com.uaijug.appex.appex.model.domain.Client;
import br.com.uaijug.appex.appex.model.repository.ClientRepository;
import br.com.uaijug.appex.appex.model.service.ClientService;
import br.com.uaijug.appex.appex.util.CPFUtil;

@Service
public class ClientServiceImpl implements ClientService {

	private static final Logger log = LogManager.getLogger(ClientServiceImpl.class);

	@Autowired
	private ClientRepository clienteRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.uaijug.appex.appex.model.service.ClienteSerivce#salvar(br.com.uaijug.
	 * appex.appex.model.domain.Cliente)
	 */
	@Override
	public Client save(Client client) {
		if (client != null) {
			Optional<Client> result = findByName(client.getName());
			if (!result.isPresent()) {
				if (!CPFUtil.valida(client.getDocumentId())) {
					throw new CPFValidationException("Erro na validação do cpf!");
				} else {
					return clienteRepository.save(client);
				}
			} else {
				throw new ResourceFoundException("Cliente já existe");
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.uaijug.appex.appex.model.service.ClienteSerivce#listar()
	 */
	@Override
	@Cacheable("clientsInCache")
	public List<Client> listAll() {
		return clienteRepository.findAll();
	}

	@Override
	@Cacheable("clientsInCache")
	public Page<Client> findAllPageable(Pageable pageable) {
		return clienteRepository.findAll(pageable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.uaijug.appex.appex.model.service.ClienteSerivce#alterar(java.lang.
	 * Long, br.com.uaijug.appex.appex.model.domain.Cliente)
	 */
	@Override
	public Client update(Long id, Client client) {
		if (id != null && client != null) {
			Optional<Client> result = findById(id);
			if (result.isPresent()) {
				if (!CPFUtil.valida(client.getDocumentId())) {
					throw new CPFValidationException("Erro na validação do cpf!");
				} else {
					result.get().updade(id, client);
					log.info("Objeto Gravado!");
					return clienteRepository.save(result.get());
				}

			} else {
				throw new ResourceNotFoundException("Cliente não existe");
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.uaijug.appex.appex.model.service.ClienteSerivce#buscarPorId(java.lang.
	 * Long)
	 */
	@Override
	public Optional<Client> findById(Long id) {
		return clienteRepository.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.uaijug.appex.appex.model.service.ClienteSerivce#buscarPorId(java.lang.
	 * Long)
	 */
	@Override
	public Optional<Client> findByName(String name) {
		return clienteRepository.findByName(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.uaijug.appex.appex.model.service.ClienteSerivce#delete(java.lang.Long)
	 */
	@Override
	public void remove(Long id) {
		clienteRepository.deleteById(id);
	}

}
