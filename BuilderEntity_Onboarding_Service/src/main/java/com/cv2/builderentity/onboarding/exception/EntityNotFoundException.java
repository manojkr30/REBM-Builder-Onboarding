package com.cv2.builderentity.onboarding.exception;

public class EntityNotFoundException extends RuntimeException{
	public String errormessage;
	public String errorStatusCode;
	public EntityNotFoundException(String errormessage, String errorStatusCode) {
		this.errormessage = errormessage;
		this.errorStatusCode = errorStatusCode;
	}
	@Override
	public String toString() {
		return "EntityNotFoundException [errormessage=" + errormessage + ", errorStatusCode=" + errorStatusCode + "]";
	}
	

}
