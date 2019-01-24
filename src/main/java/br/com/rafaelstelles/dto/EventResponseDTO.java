package br.com.rafaelstelles.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EventResponseDTO {

	private final String action;
	@JsonProperty("created_at")
	private final LocalDateTime createdAt;

	public EventResponseDTO(String action, LocalDateTime createdAt) {
		this.action = action;
		this.createdAt = createdAt;
	}

	public String getAction() {
		return action;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
}
