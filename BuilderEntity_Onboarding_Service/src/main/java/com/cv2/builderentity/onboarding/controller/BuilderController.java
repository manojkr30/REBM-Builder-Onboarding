package com.cv2.builderentity.onboarding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cv2.builderentity.onboarding.entity.BuilderEntity;
import com.cv2.builderentity.onboarding.service.BuilderService;


import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/builder/v1")
public class BuilderController {

	private BuilderService builderService;

	@Autowired
	public BuilderController(BuilderService builderService) {
		this.builderService = builderService;
	}

	@PostMapping
	public ResponseEntity<BuilderEntity> saveBuilder(@RequestBody BuilderEntity builder) {
		return new ResponseEntity<>(builderService.saveEntity(builder), HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<BuilderEntity>> fetchAllBuilders() {
		return new ResponseEntity<>(builderService.fetchAllBuilders(), HttpStatus.FOUND);
	}
	
	  @GetMapping("{id}")
	  public ResponseEntity<BuilderEntity> getEntityById(@PathVariable("id") String id) { 
		  return new ResponseEntity<>(builderService.getEntityById(id), HttpStatus.FOUND);
	  }
	  
	
	@GetMapping("name/{name}")
	public ResponseEntity<BuilderEntity> getEntityByName(@PathVariable("name") String name) {
	   return new ResponseEntity<BuilderEntity>(builderService.getEntityByName(name),HttpStatus.FOUND);

	}

    }


