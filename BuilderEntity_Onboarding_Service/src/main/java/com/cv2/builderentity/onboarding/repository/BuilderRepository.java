package com.cv2.builderentity.onboarding.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cv2.builderentity.onboarding.entity.BuilderEntity;

@Repository
public interface BuilderRepository extends MongoRepository<BuilderEntity, String> {

}
