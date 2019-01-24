package br.com.rafaelstelles.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.rafaelstelles.dto.EventResponseDTO;
import br.com.rafaelstelles.model.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {

	@Query(name = "Event.findAllEventDTOByNumber")
	Set<EventResponseDTO> findAllEventDTOByNumber(@Param("number") String number);
}
