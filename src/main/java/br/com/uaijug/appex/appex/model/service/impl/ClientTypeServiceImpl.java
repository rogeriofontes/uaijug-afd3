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

import br.com.uaijug.appex.appex.exception.ResourceFoundException;
import br.com.uaijug.appex.appex.exception.ResourceNotFoundException;
import br.com.uaijug.appex.appex.model.domain.ClientType;
import br.com.uaijug.appex.appex.model.repository.ClientTypeRepository;
import br.com.uaijug.appex.appex.model.service.ClientTypeService;

@Service
public class ClientTypeServiceImpl implements ClientTypeService {

	private static final Logger log = LogManager.getLogger(ClientTypeServiceImpl.class);

	@Autowired
	private ClientTypeRepository clientTypeeRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.uaijug.appex.appex.model.service.ClientTypeeSerivce#salvar(br.com.uaijug.
	 * appex.appex.model.domain.ClientTypee)
	 */
	@Override
	public ClientType save(ClientType clientType) {
		if (clientType != null) {
			Optional<ClientType> result = findByName(clientType.getName());
			if (!result.isPresent()) {
				return clientTypeeRepository.save(clientType);
			} else {
				throw new ResourceFoundException("ClientTypee já existe");
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see br.com.uaijug.appex.appex.model.service.ClientTypeeSerivce#listar()
	 */
	@Override
	@Cacheable("clientTypesInCache")
	public List<ClientType> listAll() {
		return clientTypeeRepository.findAll();
	}

	@Override
	@Cacheable("clientTypesInCache")
	public Page<ClientType> findAllPageable(Pageable pageable) {
		return clientTypeeRepository.findAll(pageable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.uaijug.appex.appex.model.service.ClientTypeeSerivce#alterar(java.lang.
	 * Long, br.com.uaijug.appex.appex.model.domain.ClientTypee)
	 */
	@Override
	public ClientType update(Long id, ClientType clientType) {
		if (id != null && clientType != null) {
			Optional<ClientType> result = findById(id);
			if (result.isPresent()) {
				result.get().updade(id, clientType);
				log.info("Objeto Gravado!");
				return clientTypeeRepository.save(result.get());
			} else {
				throw new ResourceNotFoundException("ClientTypee não existe");
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.uaijug.appex.appex.model.service.ClientTypeeSerivce#buscarPorId(java.lang.
	 * Long)
	 */
	@Override
	public Optional<ClientType> findById(Long id) {
		return clientTypeeRepository.findById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.uaijug.appex.appex.model.service.ClientTypeeSerivce#buscarPorId(java.lang.
	 * Long)
	 */
	@Override
	public Optional<ClientType> findByName(String name) {
		return clientTypeeRepository.findByName(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.com.uaijug.appex.appex.model.service.ClientTypeeSerivce#delete(java.lang.Long)
	 */
	@Override
	public void remove(Long id) {
		clientTypeeRepository.deleteById(id);
	}

}
