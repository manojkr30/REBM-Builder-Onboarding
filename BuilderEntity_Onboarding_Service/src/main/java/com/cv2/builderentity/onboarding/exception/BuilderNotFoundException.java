package com.cv2.builderentity.onboarding.exception;

public class BuilderNotFoundException extends RuntimeException {
	public String errormessage;
	public String errorStatusCode;

	public BuilderNotFoundException(String errormessage, String errorStatusCode) {
		this.errormessage = errormessage;
		this.errorStatusCode = errorStatusCode;
	}

}
