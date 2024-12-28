package com.cv2.builderentity.onboarding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cv2.builderentity.onboarding.entity.BuilderEntity;
import com.cv2.builderentity.onboarding.repository.BuilderRepository;

@Service
public class BuilderServiceImpl implements BuilderService {

	@Autowired
	private BuilderRepository builderRepository;

	@Override
	public BuilderEntity saveEntity(BuilderEntity builderEntity) {
		builderEntity.assignDocumentIds();
		return builderRepository.save(builderEntity);
	}

	@Override
	public List<BuilderEntity> fetchAllBuilders() {
		return builderRepository.findAll();
	}

}
