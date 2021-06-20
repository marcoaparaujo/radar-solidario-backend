package com.radar.solidario.constant;

public enum AuthenticationMessage {

	NONEXISTENT("nonexistent"), INVALIDEMAIL("invalid.email"), WITHOUTTOKEN("without.token"),
	INVALIDTOKEN("invalid.token"), INVALIDPASSWORD("invalid.password"), ALREADYEXISTSEMAIL("alreadyexists.email");

	private final String text;

	AuthenticationMessage(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}
