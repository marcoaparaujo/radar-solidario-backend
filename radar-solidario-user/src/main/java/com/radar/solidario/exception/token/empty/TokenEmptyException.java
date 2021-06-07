package com.radar.solidario.exception.token.empty;

import com.radar.solidario.constant.ErrorCode;

public class TokenEmptyException extends RuntimeException {

	private static final long serialVersionUID = -2864456512817685368L;
	
	public TokenEmptyException() {
		super(ErrorCode.TOKEN_EMPTY.getMessage());
	}
}
