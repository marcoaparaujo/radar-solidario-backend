package com.radar.solidario.exception.family.notFound;

import com.radar.solidario.constant.ErrorCode;

public class FamilyNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6002342690048300285L;

	public FamilyNotFoundException() {
		super(ErrorCode.AUTHENTICATION_NOT_FOUND.getMessage());
	}
}
