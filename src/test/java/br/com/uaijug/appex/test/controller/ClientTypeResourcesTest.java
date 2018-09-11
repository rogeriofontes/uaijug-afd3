/*package br.com.uaijug.appex.test.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.uaijug.appex.appex.AppexApplication;
import br.com.uaijug.appex.appex.model.domain.ClientType;
import br.com.uaijug.appex.appex.model.service.ClientTypeService;
import br.com.uaijug.appex.appex.web.resources.ClientTypeResources;

@RunWith(SpringRunner.class)
@WebMvcTest(ClientTypeResources.class)
@ContextConfiguration(classes = { AppexApplication.class })
public class ClientTypeResourcesTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ClientTypeService service;

	private List<ClientType> mockClientTypes = null;

	private ClientType mockClientType = null;

	@Before
	public void init() {
		mockClientType = new ClientType();
		mockClientType.setName("Vip");
		mockClientTypes = new ArrayList<>();
		mockClientTypes.add(mockClientType);
	}

	//@Test
	public void clientsShouldReturnFromService() throws Exception {
		when(service.listAll()).thenReturn(mockClientTypes);
		this.mockMvc.perform(get("/client-types")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("{\"name\":\"Vip\"}")));
	}

	@Test
	public void clientShouldReturnFromService() throws Exception {
		when(service.findById(Mockito.anyLong())).thenReturn(Optional.of(mockClientType));
		this.mockMvc.perform(get("/client-types")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("{\"name\":\"Vip\"}")));
	}
	
	//@Test
	public void clientShouldCreateFromService() throws Exception {
		when(service.save(mockClientType)).thenReturn(mockClientType);
		this.mockMvc.perform(post("/client-types")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("{\"name\":\"Vip\"}")));
	}*/

	/*
	 * @Test public void list_all_clients_types() throws Exception {
	 * 
	 * Mockito.when(service.listAll()).thenReturn(mockClientTypes); RequestBuilder
	 * requestBuilder =
	 * MockMvcRequestBuilders.get("/client-types").accept(MediaType.APPLICATION_JSON
	 * );
	 * 
	 * MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	 * 
	 * System.out.println(result.getResponse()); String exampleClientJson =
	 * "{\"name\":\"Vip\"}";
	 * 
	 * JSONAssert.assertEquals(exampleClientJson,
	 * result.getResponse().getContentAsString(), false); }
	 */
//}
