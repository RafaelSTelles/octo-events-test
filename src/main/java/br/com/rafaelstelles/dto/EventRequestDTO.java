package br.com.rafaelstelles.dto;

import org.postgresql.shaded.com.ongres.scram.common.util.Preconditions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EventRequestDTO {

	private final String action;
	private final String number;

	@JsonCreator
	public EventRequestDTO(@JsonProperty(value = "action") String action,
						   @JsonProperty(value = "number") String number) {
		Preconditions.checkNotEmpty(action, "action");
		Preconditions.checkNotEmpty(number, "number");
		this.action = action;
		this.number = number;
	}

	public String getAction() {
		return action;
	}

	public String getNumber() {
		return number;
	}

	@Override
	public String toString() {
		return "EventRequestDTO{" +
				"action='" + action + '\'' +
				", number='" + number + '\'' +
				'}';
	}
}
