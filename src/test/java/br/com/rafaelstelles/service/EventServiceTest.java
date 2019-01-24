package br.com.rafaelstelles.service;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Iterables;

import br.com.rafaelstelles.AbstractIntegrationTest;
import br.com.rafaelstelles.dto.EventRequestDTO;
import br.com.rafaelstelles.dto.EventResponseDTO;
import br.com.rafaelstelles.model.Event;

public class EventServiceTest extends AbstractIntegrationTest {

	@Autowired
	private EventService eventService;

	@Test
	public void createEvent() {
		final Event open = eventService.create(new EventRequestDTO("open", "1000"));
		Assert.assertEquals(open.getAction(), "open");
		Assert.assertEquals(open.getNumber(), "1000");
	}

	@Test
	public void createEventAndListAll() {
		eventService.create(new EventRequestDTO("open", "1000"));
		eventService.create(new EventRequestDTO("edited", "1000"));
		eventService.create(new EventRequestDTO("edited", "1000"));
		eventService.create(new EventRequestDTO("closed", "1000"));

		eventService.create(new EventRequestDTO("open", "1001"));
		eventService.create(new EventRequestDTO("edited", "1001"));
		eventService.create(new EventRequestDTO("edited", "1002"));
		eventService.create(new EventRequestDTO("closed", "1003"));
		eventService.create(new EventRequestDTO("reopen", "1000"));

		final Set<EventResponseDTO> allEventsByNumber = eventService.findAllEventDTOByNumber("1000");
		Assert.assertEquals(allEventsByNumber.size(), 5);

		validateResponse(Iterables.get(allEventsByNumber, 0), "open");
		validateResponse(Iterables.get(allEventsByNumber, 1), "edited");
		validateResponse(Iterables.get(allEventsByNumber, 2), "edited");
		validateResponse(Iterables.get(allEventsByNumber, 3), "closed");
		validateResponse(Iterables.get(allEventsByNumber, 4), "reopen");
	}

	private void validateResponse(EventResponseDTO one, final String actual) {
		Assert.assertNotNull(one);

		Assert.assertEquals(one.getAction(), actual);
	}
}
