package com.radar.solidario.exception.donate.notFound;

import com.radar.solidario.constant.ErrorCode;

public class DonateNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5818315815113777368L;
	
	public DonateNotFoundException() {
		super(ErrorCode.DONATE_NOT_FOUND.getMessage());
	}

}
