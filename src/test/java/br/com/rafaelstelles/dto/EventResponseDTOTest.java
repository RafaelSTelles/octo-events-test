package br.com.rafaelstelles.dto;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

public class EventResponseDTOTest {

	@Test
	public void createSuccessful() {
		final LocalDateTime dataOne = LocalDateTime.now();
		final EventResponseDTO actionOne = new EventResponseDTO("actionOne", dataOne);
		Assert.assertEquals(actionOne.getAction(), "actionOne");
		Assert.assertEquals(actionOne.getCreatedAt(), dataOne);
	}
}
