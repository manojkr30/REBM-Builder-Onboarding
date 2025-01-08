package com.cv2.builderentity.onboarding.exception;

public class InvalidStateException extends RuntimeException {
	public String errormessage;
	public String errorStatusCode;
	public InvalidStateException(String errormessage, String errorStatusCode) {
		this.errormessage = errormessage;
		this.errorStatusCode = errorStatusCode;
	}
	@Override
	public String toString() {
		return "InvalidStateException [errormessage=" + errormessage + ", errorStatusCode=" + errorStatusCode + "]";
	}
	
	
	

}
