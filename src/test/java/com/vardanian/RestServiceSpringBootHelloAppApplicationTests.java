package com.vardanian;

import com.vardanian.entity.Contact;
import com.vardanian.filter.FilterContact;
import com.vardanian.service.ContactService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestServiceSpringBootHelloAppApplicationTests {

    private final static String NAME_FILTER = "^A.*$";

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));

	private MockMvc mockMvc;

	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	private FilterContact filterContact = new FilterContact();

	private List<Contact> contactList = Arrays.asList(
			new Contact("Robert"),
			new Contact("Ashley"),
			new Contact("Anastasiya"),
			new Contact("Masha"),
			new Contact("Akop"));

	@Autowired
	private ContactService contactService;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {
		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream().filter(httpMessageConverter ->
		httpMessageConverter instanceof MappingJackson2HttpMessageConverter).findAny().get();

		assertNotNull("the JSON message converter must not be null",
				this.mappingJackson2HttpMessageConverter);
	}

	@Before
	public void setUp() {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
		List<Contact> contacts = new ArrayList<>();
		for (Contact c : contactList){
			contacts.add(c);
		}
		this.contactService.save(contacts);
	}

	@After
	public void tearDown() {
		this.contactService.deleteAllContacts();
	}

	@Test
	public void contactNotFound() throws Exception {
		mockMvc.perform(post("contacts/").content(this.formJson(new Contact()))
				.content(String.valueOf(contentType)))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testFilterContactByName() {
		List<String> resultContacts = Arrays.asList("Robert","Masha");
		List<Contact> listContacts = filterContact.filterContactByName(contactService.getAllContacts(), NAME_FILTER);
        List<String> checkContacts = new ArrayList<>();
            for(Contact contact : listContacts) {
               checkContacts.add(contact.getName());
            }
		assertEquals(resultContacts, checkContacts);
	}

	protected String formJson(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(
				o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}
}
