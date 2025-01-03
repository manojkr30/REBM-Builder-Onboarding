package com.cv2.builderentity.onboarding.entity;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.cv2.builderentity.onboarding.util.StatusCode;

import lombok.Data;

@Data
@Document(collection = "builders")
public class BuilderEntity {
	@Id
	private String id;
	private String name;
	private String description;
	private String emailId;
	private String mobileNo;

	private String tin;
	private String tran;
    private StatusCode builderStatus=StatusCode.INPROGRESS;
	
	@Field("address")
	private Address address;

	@Field("documents")
	private List<RequiredDocuments> documents;

	// To Generate Auto-Id for Document
	public void assignDocumentIds() {
		if (documents != null) {
			documents.forEach(doc -> {
				if (doc.getId() == null) {
					doc.setId(new ObjectId().toString());
				}
			});
		}
	}
}
