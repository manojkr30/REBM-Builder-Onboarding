package com.cv2.builderentity.onboarding.service;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cv2.builderentity.onboarding.config.AwsS3Config;
import com.cv2.builderentity.onboarding.entity.RequiredDocuments;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

@Service
public class AwsS3Service {

	private S3Client s3Client;
	private AwsS3Config awsS3Config;
	private S3Presigner s3Presigner;

	@Autowired
	public AwsS3Service(S3Client s3Client, AwsS3Config awsS3Config, S3Presigner s3Presigner) {
		this.s3Client = s3Client;
		this.awsS3Config = awsS3Config;
		this.s3Presigner = s3Presigner;
	}
    
	//Method To Upload Documents
	public List<RequiredDocuments> uploadFiles(List<MultipartFile> files) {
		List<RequiredDocuments> uploadResults = new ArrayList<>();

		for (MultipartFile file : files) {
			String key = "uploads/" + file.getOriginalFilename();
			try {
				PutObjectRequest request = PutObjectRequest.builder().bucket(awsS3Config.getBucketName()).key(key)
						.contentType(file.getContentType() != null ? file.getContentType() : "application/octet-stream")
						.build();

				s3Client.putObject(request, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
				String fileUrl = generatePresignedUrl(awsS3Config.getBucketName(), key);
				uploadResults.add(RequiredDocuments.builder().name(file.getOriginalFilename()).path(fileUrl).build());
			} catch (IOException e) {
				System.out.println("Failed to upload " + file.getOriginalFilename() + ": " + e.getMessage());
			} catch (S3Exception e) {
				System.out.println(
						"Failed to upload " + file.getOriginalFilename() + ": " + e.awsErrorDetails().errorMessage());
			}
		}

		return uploadResults;
	}
     
	//Method To Get Document Saved Path 
	//S-Key =jyOe/pk2BGT/P3mh5ny1ofHfFZANLEKZhS6pmCJD
	//A-key =AKIAU6GDV3KWZ4FY77S4
	public String generatePresignedUrl(String bucketName, String fileKey) {
		GetObjectRequest getObjectRequest = GetObjectRequest.builder().bucket(bucketName).key(fileKey).build();

		GetObjectPresignRequest presignRequest = GetObjectPresignRequest.builder().getObjectRequest(getObjectRequest)
				.signatureDuration(Duration.ofMinutes(15)).build();

		URL presignedUrl = s3Presigner.presignGetObject(presignRequest).url();
		return presignedUrl.toString();
	}
}
