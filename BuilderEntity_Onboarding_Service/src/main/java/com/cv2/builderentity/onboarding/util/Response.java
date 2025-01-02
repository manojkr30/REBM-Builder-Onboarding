package com.cv2.builderentity.onboarding.util;

import java.util.List;

import com.cv2.builderentity.onboarding.entity.BuilderEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)


public class Response {
	private Integer statusCode;
	private String statusMsg;
	private BuilderEntity data;
	private List<BuilderEntity> datas;
	private String errorCode;
	private String errorMsg;

}
