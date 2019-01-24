package br.com.rafaelstelles.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rafaelstelles.dto.EventRequestDTO;
import br.com.rafaelstelles.dto.EventResponseDTO;
import br.com.rafaelstelles.model.Event;
import br.com.rafaelstelles.repository.EventRepository;

@Service
public class EventService {

	@Autowired
	public EventRepository eventRepository;

	@Transactional
	public Event create(final EventRequestDTO eventRequestDTO) {
		return eventRepository.save(Event.of(eventRequestDTO));
	}

	public Set<EventResponseDTO> findAllEventDTOByNumber(final String number) {
		return eventRepository.findAllEventDTOByNumber(number);
	}
}
