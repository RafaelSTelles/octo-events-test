package br.com.rafaelstelles.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.rafaelstelles.dto.EventRequestDTO;

@Entity
@NamedQueries({
		@NamedQuery(name = "Event.findAllEventDTOByNumber",
				query = "select new br.com.rafaelstelles.dto.EventResponseDTO(iv.action, iv.createdAt) from Event iv where iv.number = :number group by iv.id order by iv.createdAt, iv.id"),
})
public class Event {

	@Id
	@GeneratedValue(generator = "event_id_id_seq")
	@SequenceGenerator(name = "event_id_id_seq", sequenceName = "event_id_id_seq", allocationSize = 1)
	private Long id;

	@NotNull
	@NotEmpty
	private String number;

	@NotNull
	@NotEmpty
	private String action;

	@NotNull
	private LocalDateTime createdAt;

	protected Event() {
	}

	private Event(String number, String action) {
		this.number = number;
		this.action = action;
		this.createdAt = LocalDateTime.now();
	}

	public static Event of(EventRequestDTO eventRequestDTO) {
		return new Event(eventRequestDTO.getNumber(), eventRequestDTO.getAction());
	}

	public String getNumber() {
		return number;
	}

	public String getAction() {
		return action;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Event)) {
			return false;
		}
		final Event that = (Event) o;
		return Objects.equals(number, that.number) &&
				Objects.equals(action, that.action) &&
				Objects.equals(createdAt, that.createdAt);
	}

	@Override
	public int hashCode() {
		return Objects.hash(number, action, createdAt);
	}

	@Override
	public String toString() {
		return "Event{" +
				"number='" + number + '\'' +
				", action='" + action + '\'' +
				", createdAt=" + createdAt +
				'}';
	}
}
