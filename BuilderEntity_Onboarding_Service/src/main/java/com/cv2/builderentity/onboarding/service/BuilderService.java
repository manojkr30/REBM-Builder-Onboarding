package com.cv2.builderentity.onboarding.service;

import java.util.List;

import com.cv2.builderentity.onboarding.entity.BuilderEntity;

public interface BuilderService {

	public BuilderEntity saveEntity(BuilderEntity builderEntity);

	public List<BuilderEntity> fetchAllBuilders();

}
