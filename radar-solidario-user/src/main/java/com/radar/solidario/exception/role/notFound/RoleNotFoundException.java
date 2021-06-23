package com.radar.solidario.exception.role.notFound;

import com.radar.solidario.constant.ErrorCode;

public class RoleNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1023283379749079161L;

	public RoleNotFoundException() {
		super(ErrorCode.ROLE_NOT_FOUND.getMessage());
	}
}
