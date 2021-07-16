package com.radar.solidario.exception.charity.notFound;

import com.radar.solidario.constant.ErrorCode;

public class CharityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5818315815113777368L;
	
	public CharityNotFoundException() {
		super(ErrorCode.CHARITY_NOT_FOUND.getMessage());
	}

}
