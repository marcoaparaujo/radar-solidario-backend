package com.radar.solidario.exception.family.alreadyExists;

import com.radar.solidario.constant.ErrorCode;

public class FamilyAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 6177850157860516266L;

	public FamilyAlreadyExistsException() {
		super(ErrorCode.FAMILY_ALREADY_EXISTS.getMessage());
	}
}
