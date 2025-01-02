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
import com.cv2.builderentity.onboarding.util.Response;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("api/builder/v1")
public class BuilderController {

	@Autowired
	private BuilderService builderService;

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
	
	
	  @GetMapping("/{id}")
	  public ResponseEntity<Response> getEntityById(@PathVariable("id") String id) { 
		  log.info("Started execution of GetById..."); 
		  return new ResponseEntity<>(Response.builder() .data(builderService.getEntityById(id))
				  .statusCode(HttpStatus.FOUND.value())
				  .statusMsg(" Successfully Fetched from database by id " + id) .build(),
				  HttpStatus.FOUND);
	  }
	  
	
	@GetMapping(params = "name")
	public ResponseEntity<BuilderEntity> getEntityByName(@PathVariable String name) {
	    BuilderEntity entity = builderService.getEntityByName(name);
	    if (entity != null) {
	        return new ResponseEntity<>(entity, HttpStatus.FOUND);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

    }


