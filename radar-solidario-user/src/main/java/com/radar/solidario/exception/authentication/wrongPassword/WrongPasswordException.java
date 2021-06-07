package com.radar.solidario.exception.authentication.wrongPassword;

import com.radar.solidario.constant.ErrorCode;

public class WrongPasswordException extends RuntimeException {

	private static final long serialVersionUID = -3904671552829840705L;

	public WrongPasswordException() {
		super(ErrorCode.WRONG_PASSWORD.getMessage());
	}
}
