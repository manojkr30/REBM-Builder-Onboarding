package com.cv2.builderentity.onboarding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cv2.builderentity.onboarding.entity.BuilderEntity;
import com.cv2.builderentity.onboarding.service.BuilderService;

@RestController
@RequestMapping("api/builder/v1")
public class BuilderController {

	@Autowired
	private BuilderService builderService;

	@PostMapping
	public ResponseEntity<BuilderEntity> saveBuilder(@RequestBody BuilderEntity builder) {
		return new ResponseEntity<>(builderService.saveEntity(builder), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<BuilderEntity>> fetchAllBuilders() {
		return new ResponseEntity<>(builderService.fetchAllBuilders(), HttpStatus.FOUND);
	}

}
