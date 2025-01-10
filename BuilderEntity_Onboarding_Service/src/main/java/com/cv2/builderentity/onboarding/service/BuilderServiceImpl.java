package com.cv2.builderentity.onboarding.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cv2.builderentity.onboarding.entity.BuilderEntity;
import com.cv2.builderentity.onboarding.exception.BuilderNotFoundException;
import com.cv2.builderentity.onboarding.exception.BuilderValidationException;
import com.cv2.builderentity.onboarding.repository.BuilderRepository;

@Service
public class BuilderServiceImpl implements BuilderService {

	private BuilderRepository builderRepository;
	private AwsS3Service awsS3Service;

	@Autowired
	public BuilderServiceImpl(BuilderRepository builderRepository, AwsS3Service awsS3Service) {
		this.builderRepository = builderRepository;
		this.awsS3Service = awsS3Service;
	}

	@Override
	public BuilderEntity builderOnboarding(BuilderEntity builderEntity) {
		Optional<BuilderEntity> existBuilder = builderRepository.findByIin(builderEntity.getTin());
		if (existBuilder.isPresent()) {
			BuilderEntity builderInDb = existBuilder.get();
			switch (builderInDb.getBuilderStatus()) {
			case BG_FAILED -> throw new BuilderValidationException(
					"Unable to onboard the user as back ground check is failed.", "ONBR_502");

			case ACTIVE -> throw new BuilderValidationException(
					"Unable to onboard the user as user already registered with system.", "ONBR_503");

			}
		}
		builderEntity.assignDocumentIds();
		return builderRepository.save(builderEntity);
	}

	@Override
	public List<BuilderEntity> fetchAllBuilders() {
		return builderRepository.findAll();
	}

	@Override
	public BuilderEntity getEntityById(String id) {
		return builderRepository.findById(id).orElseThrow(() -> new BuilderNotFoundException(
				"Unable to fetch the onboard user details , as user not exist in the system.", "ONBR_505"));
	}

	@Override
	public BuilderEntity getEntityByName(String name) {
		return builderRepository.findByName(name).orElseThrow(() -> new BuilderNotFoundException(
				"Unable to fetch the onboard user details , as user not exist in the system.", "ONBR_505"));
	}

	@Override
	public BuilderEntity saveDocuments(String id, List<MultipartFile> file) {
		BuilderEntity builderEntity = builderRepository.findById(id).orElseThrow(() -> new BuilderNotFoundException(
				"Unable to fetch the onboard user details , as user not exist in the system.", "ONBR_505"));
		builderEntity.setDocuments(awsS3Service.uploadFiles(file));
		builderEntity.assignDocumentIds();
		return builderRepository.save(builderEntity);
	}



}
