package com.cv2.builderentity.onboarding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.cv2.builderentity.onboarding.entity.BuilderEntity;
import com.cv2.builderentity.onboarding.exception.BuilderNotFoundException;
import com.cv2.builderentity.onboarding.exception.EntityNotFoundException;
import com.cv2.builderentity.onboarding.exception.InvalidStateException;
import com.cv2.builderentity.onboarding.repository.BuilderRepository;
import com.cv2.builderentity.onboarding.util.StatusCode;

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
		return builderRepository.findById(id).orElseThrow(()-> new 
				BuilderNotFoundException("Buider Not found by Id :"+id , 
						String.valueOf(HttpStatus.NOT_FOUND)));
	}

	 @Override
	    public BuilderEntity getEntityByName(String name) {
	        return builderRepository.findByName(name).orElseThrow(()-> new 
	        		BuilderNotFoundException("Buider Not found by Name :"+name , 
	        				String.valueOf(HttpStatus.NOT_FOUND)));
	 }
	 
	   @Override
	    public BuilderEntity deboardEntity(String id) {
	        BuilderEntity entity = builderRepository.findById(id)
	                .orElseThrow(() -> new EntityNotFoundException(
	                        "Entity not found with ID: " + id,
	                        String.valueOf(HttpStatus.NOT_FOUND)
	                ));

	        if (entity.getBuilderStatus() != StatusCode.ACTIVE && entity.getBuilderStatus() != StatusCode.INACTIVE) 
	        {
	            throw new InvalidStateException(
	                    "Only entities with ACTIVE or INACTIVE status can be deboarded.",
	                    String.valueOf(HttpStatus.BAD_REQUEST)
	            );
	        }
	        entity.setBuilderStatus(StatusCode.DEBOARDED);
	        return builderRepository.save(entity);
}
}

