package com.radar.solidario.exception.authentication.notChanged;

import com.radar.solidario.constant.ErrorCode;

public class AuthenticationNotChangedException extends RuntimeException {

	private static final long serialVersionUID = -320075757688047046L;

	public AuthenticationNotChangedException() {
		super(ErrorCode.AUTHENTICATION_NOT_CHANGED.getMessage());
	}
}
