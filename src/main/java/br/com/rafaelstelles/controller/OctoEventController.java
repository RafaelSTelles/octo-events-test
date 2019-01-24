package br.com.rafaelstelles.controller;

import static br.com.rafaelstelles.util.Path.PATH_EVENTS;
import static br.com.rafaelstelles.util.Path.PATH_ISSUES_EVENT_ID;
import static br.com.rafaelstelles.util.Path.PLACEHOLDER_NUMBER;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.rafaelstelles.dto.EventRequestDTO;
import br.com.rafaelstelles.dto.EventResponseDTO;
import br.com.rafaelstelles.service.EventService;

@RestController
public class OctoEventController {

	private static final Logger logger = LoggerFactory.getLogger(OctoEventController.class);

	@Autowired
	public EventService eventService;

	@PostMapping(value = PATH_EVENTS)
	public void createEvent(@RequestBody EventRequestDTO eventRequestDTO) {
		logger.info("{}", eventRequestDTO);
		eventService.create(eventRequestDTO);
	}

	@GetMapping(PATH_ISSUES_EVENT_ID)
	public Set<EventResponseDTO> findByBumber(@PathVariable(PLACEHOLDER_NUMBER) String number) {
		logger.info("id={}", number);
		return eventService.findAllEventDTOByNumber(number);
	}

}
