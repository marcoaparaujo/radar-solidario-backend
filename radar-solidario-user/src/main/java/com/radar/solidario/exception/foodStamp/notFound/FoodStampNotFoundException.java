package com.radar.solidario.exception.foodStamp.notFound;

import com.radar.solidario.constant.ErrorCode;

public class FoodStampNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5818315815113777368L;
	
	public FoodStampNotFoundException() {
		super(ErrorCode.FOODSTAMP_NOT_FOUND.getMessage());
	}

}
