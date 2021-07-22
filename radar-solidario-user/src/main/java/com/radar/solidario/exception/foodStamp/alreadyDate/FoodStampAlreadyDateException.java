package com.radar.solidario.exception.foodStamp.alreadyDate;

import com.radar.solidario.constant.ErrorCode;

public class FoodStampAlreadyDateException extends RuntimeException {

	private static final long serialVersionUID = 6177850157860516266L;

	public FoodStampAlreadyDateException() {
		super(ErrorCode.INVALID_DATE.getMessage());
	}
}
