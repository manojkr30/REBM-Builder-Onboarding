package com.cv2.builderentity.onboarding.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cv2.builderentity.onboarding.entity.BuilderEntity;
import com.cv2.builderentity.onboarding.entity.RequiredDocuments;

public interface BuilderService {

	BuilderEntity saveEntity(BuilderEntity builderEntity);

	List<BuilderEntity> fetchAllBuilders();

	BuilderEntity getEntityById(String id);

	BuilderEntity getEntityByName(String entityName);

	BuilderEntity saveDocuments(String id, List<MultipartFile> files);

}
