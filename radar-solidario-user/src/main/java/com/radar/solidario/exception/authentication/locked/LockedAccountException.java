package com.radar.solidario.exception.authentication.locked;

import com.radar.solidario.constant.ErrorCode;

public class LockedAccountException extends RuntimeException {

	private static final long serialVersionUID = 7976777825709332795L;

	public LockedAccountException() {
		super(ErrorCode.LOCKED_ACCOUNT.getMessage());
	}
}
