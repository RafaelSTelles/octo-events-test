package br.com.rafaelstelles.dto;

import org.junit.Assert;
import org.junit.Test;

public class EventRequestDTOTest {

	@Test
	public void createSuccessful() {
		final EventRequestDTO requestOne = new EventRequestDTO("actionOne", "numberOne");
		Assert.assertEquals(requestOne.getAction(), "actionOne");
		Assert.assertEquals(requestOne.getNumber(), "numberOne");
	}

	@Test(expected = IllegalArgumentException.class)
	public void createFailWithActionBlank() {
		new EventRequestDTO("", "number");
	}

	@Test(expected = IllegalArgumentException.class)
	public void createFailWithActionNull() {
		new EventRequestDTO(null, "number");
	}

	@Test(expected = IllegalArgumentException.class)
	public void createFailWithNumberBlank() {
		new EventRequestDTO("action", "");
	}

	@Test(expected = IllegalArgumentException.class)
	public void createFailWithNumberNull() {
		new EventRequestDTO("action", null);
	}
}
