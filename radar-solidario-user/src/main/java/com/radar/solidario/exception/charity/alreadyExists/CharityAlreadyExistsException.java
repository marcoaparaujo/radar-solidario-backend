package com.radar.solidario.exception.charity.alreadyExists;

import com.radar.solidario.constant.ErrorCode;

public class CharityAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 6177850157860516266L;

	public CharityAlreadyExistsException() {
		super(ErrorCode.CHARITY_ALREADY_EXISTS.getMessage());
	}
}
