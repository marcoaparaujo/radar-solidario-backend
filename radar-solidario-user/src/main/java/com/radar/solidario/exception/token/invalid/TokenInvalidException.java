package com.radar.solidario.exception.token.invalid;

import com.radar.solidario.constant.ErrorCode;

public class TokenInvalidException extends RuntimeException {

	private static final long serialVersionUID = -2864456512817685368L;

	public TokenInvalidException() {
		super(ErrorCode.TOKEN_INVALID.getMessage());
	}
}
