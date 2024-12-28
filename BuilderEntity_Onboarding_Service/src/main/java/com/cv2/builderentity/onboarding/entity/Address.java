package com.cv2.builderentity.onboarding.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Address {

	private String address1;
	private String address2;
	private String address3;
	private String city;
	private String state;
	private String pinCode;
	private String country;
}
