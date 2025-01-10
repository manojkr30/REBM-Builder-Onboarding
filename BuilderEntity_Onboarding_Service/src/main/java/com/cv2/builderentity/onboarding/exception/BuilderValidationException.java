package com.cv2.builderentity.onboarding.exception;

public class BuilderValidationException extends RuntimeException {
	public String errormessage;
	public String errorStatusCode;

	public BuilderValidationException(String errormessage, String errorStatusCode) {
		this.errormessage = errormessage;
		this.errorStatusCode = errorStatusCode;
	}
}
