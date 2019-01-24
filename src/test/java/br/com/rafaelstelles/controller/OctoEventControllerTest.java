package br.com.rafaelstelles.controller;

import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import br.com.rafaelstelles.AbstractIntegrationTest;
import br.com.rafaelstelles.util.Path;

public class OctoEventControllerTest extends AbstractIntegrationTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void createEventsExampleReadme() throws Exception {
		mvc.perform(post(Path.PATH_EVENTS)
				.contentType(APPLICATION_JSON_VALUE)
				.content(generateJsonPostWithActionAndNumber("open", "1000")))
				.andExpect(status().isOk())
				.andReturn();

		mvc.perform(post(Path.PATH_EVENTS)
				.contentType(APPLICATION_JSON_VALUE)
				.content(generateJsonPostWithActionAndNumber("open", "1001")))
				.andExpect(status().isOk())
				.andReturn();

		mvc.perform(post(Path.PATH_EVENTS)
				.contentType(APPLICATION_JSON_VALUE)
				.content(generateJsonPostWithActionAndNumber("closed", "1000")))
				.andExpect(status().isOk())
				.andReturn();

		mvc.perform(post(Path.PATH_EVENTS)
				.contentType(APPLICATION_JSON_VALUE)
				.content(generateJsonPostWithActionAndNumber("edited", "1001")))
				.andExpect(status().isOk())
				.andReturn();

		final String pathRequest = Path.PATH_ISSUES_EVENT_ID.replace(Path.PATH_NUMBER, "/1000");
		mvc.perform(get(pathRequest)
				.contentType(APPLICATION_JSON_VALUE)
				.content(generateJsonPostWithActionAndNumber("edited", "1001")))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].action", is("open")))
				.andExpect(jsonPath("$[0].created_at", anything()))
				.andExpect(jsonPath("$[1].action", is("closed")))
				.andExpect(jsonPath("$[1].created_at", anything()))
				.andReturn();
	}

	@Test
	public void validateNumberIsNotNull() throws Exception {
		mvc.perform(post(Path.PATH_EVENTS)
				.contentType(APPLICATION_JSON_VALUE)
				.content("{action: \"open\"}"))
				.andExpect(status().is4xxClientError());
	}

	@Test
	public void validateNumberIsNotEmpty() throws Exception {
		mvc.perform(post(Path.PATH_EVENTS)
				.contentType(APPLICATION_JSON_VALUE)
				.content("{action: \"open\", number: \"\"}"))
				.andExpect(status().is4xxClientError());
	}

	@Test
	public void validateActionIsNotNull() throws Exception {
		mvc.perform(post(Path.PATH_EVENTS)
				.contentType(APPLICATION_JSON_VALUE)
				.content("{number: \"1000\"}"))
				.andExpect(status().is4xxClientError());
	}

	@Test
	public void validateActionIsNotEmpty() throws Exception {
		mvc.perform(post(Path.PATH_EVENTS)
				.contentType(APPLICATION_JSON_VALUE)
				.content("{action: \"\", number: \"1000\"}"))
				.andExpect(status().is4xxClientError());
	}

	private String generateJsonPostWithActionAndNumber(final String action, final String number) {
		return "{\n" +
				" \"action\":\"" + action + "\",\n" +
				" \"number\":\"" + number + "\"\n" +
				"}";
	}

}
