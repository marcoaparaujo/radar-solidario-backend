package com.radar.solidario.exception.role.alreadyExists;

import com.radar.solidario.constant.ErrorCode;

public class RoleAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -2284646792599136943L;

	public RoleAlreadyExistsException() {
		super(ErrorCode.ROLE_ALREADY_EXISTS.getMessage());
	}
}
