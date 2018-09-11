/*package br.com.uaijug.appex.test.controller;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.uaijug.appex.appex.model.domain.Client;
import br.com.uaijug.appex.appex.model.domain.ClientType;
import br.com.uaijug.appex.appex.model.service.ClientService;
import br.com.uaijug.appex.appex.web.resources.ClientResources;

@RunWith(SpringRunner.class)
@WebMvcTest(ClientResources.class)
public class ClientResourcesTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ClientService service;
	
	List<Client> mockClients = null;

	@Before
	public void init() {
		Client mockClient = new Client();
		ClientType clientType = new ClientType();
		clientType.setName("Nte");
		mockClient.setClientType(clientType);
		clientType.setName("Joao");
		mockClient.setDocumentId(null);
		mockClient.setEmail("root@localhost");
		
				//Arrays.asList("Learn Maven", "Import Project", "First Example", "Second Example"));

		String exampleCourseJson = "{\"name\":\"Spring\",\"description\":\"10 Steps\",\"steps\":[\"Learn Maven\",\"Import Project\",\"First Example\",\"Second Example\"]}";
		System.out.println("-->" + exampleCourseJson);
	}

	@Test
	public void retrieveDetailsForCourse() throws Exception {

		//Mockito.when(service.s.retrieveCourse(Mockito.anyString(), Mockito.anyString())).thenReturn(mockClient);
		Mockito.when(service.listAll()).thenReturn(mockClients);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/students/Student1/courses/Course1")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "{id:Course1,name:Spring,description:10 Steps}";

		// {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K
		// Students","steps":["Learn Maven","Import Project","First Example","Second
		// Example"]}

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
}*/
