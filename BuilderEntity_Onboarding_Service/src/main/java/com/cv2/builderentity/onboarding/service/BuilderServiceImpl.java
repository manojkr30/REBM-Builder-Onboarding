package com.cv2.builderentity.onboarding.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Override
	public BuilderEntity getEntityById(String id) {
		return builderRepository.findById(id).orElse(null);
	}

	 @Override
	    public BuilderEntity getEntityByName(String name) {
	        return builderRepository.findByEntityName(name).orElse(null);
	 }

}


