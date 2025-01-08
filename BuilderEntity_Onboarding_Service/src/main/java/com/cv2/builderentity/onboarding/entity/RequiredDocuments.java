package com.cv2.builderentity.onboarding.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RequiredDocuments {
	private String id;
	private String name;
	private String path;

}
