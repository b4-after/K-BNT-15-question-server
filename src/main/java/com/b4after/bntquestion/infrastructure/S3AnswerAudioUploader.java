package com.b4after.bntquestion.infrastructure;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.b4after.bntquestion.domain.AnswerAudio;
import com.b4after.bntquestion.service.AnswerAudioUploader;
import com.b4after.bntquestion.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class S3AnswerAudioUploader implements AnswerAudioUploader {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public AnswerAudio upload(MultipartFile audioFile) {
        ObjectMetadata objectMetadata = setUpMetadata(audioFile);
        return upload(audioFile, objectMetadata);
    }

    private ObjectMetadata setUpMetadata(MultipartFile audioFile) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(audioFile.getContentType());
        objectMetadata.setContentLength(audioFile.getSize());
        return objectMetadata;
    }

    private AnswerAudio upload(MultipartFile audioFile, ObjectMetadata objectMetadata) {
        String audioFileObjectKey = createFileObjectKey(audioFile);
        try (InputStream inputStream = audioFile.getInputStream()) {
            PutObjectRequest request = new PutObjectRequest(bucket, audioFileObjectKey, inputStream, objectMetadata);
            amazonS3Client.putObject(request);
        } catch (IOException e) {
            throw new IllegalArgumentException("오디오 파일에서 문제가 발생했습니다.");
        }
        return new AnswerAudio(audioFileObjectKey);
    }

    private String createFileObjectKey(MultipartFile audioFile) {
        return "audio/" + UUID.randomUUID() + "." + FileUtils.extractExtension(audioFile);
    }
}
