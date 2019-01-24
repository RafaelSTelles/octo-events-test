package br.com.rafaelstelles.util;

public final class Path {

	public static final String PLACEHOLDER_NUMBER = "number";

	private static final String PATH_ISSUES = "/issues";
	public static final String PATH_NUMBER = "/{" + PLACEHOLDER_NUMBER + "}";

	public static final String PATH_EVENTS = "/events";
	public static final String PATH_ISSUES_EVENT_ID = PATH_ISSUES + PATH_NUMBER + PATH_EVENTS;

	private Path() {
	}

}
