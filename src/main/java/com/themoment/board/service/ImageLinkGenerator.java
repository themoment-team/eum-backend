package com.themoment.board.service;

import com.themoment.board.dto.ImageLink.ImageLinkResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;

import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageLinkGenerator {

    private final S3Presigner presigner;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public ImageLinkResponseDTO imageLinkGenerate(String fileType, String type) {
        String extension = getExtensionFromMimeType(fileType);
        String fileName = type + "/" + UUID.randomUUID() + extension;

        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .contentType(fileType)
                .build();

        PresignedPutObjectRequest presignedRequest = presigner.presignPutObject(
                b -> b.putObjectRequest(objectRequest)
                        .signatureDuration(Duration.ofMinutes(10))
        );

        String fileUrl = "https://" + bucketName + ".s3.amazonaws.com/" + fileName;
        return new ImageLinkResponseDTO(presignedRequest.url().toString(), fileUrl);
    }

    private String getExtensionFromMimeType(String mimeType) {
        return switch (mimeType) {
            case "image/png" -> ".png";
            case "image/jpeg" -> ".jpg";
            case "image/webp" -> ".webp";
            default -> throw new IllegalArgumentException("허용되지 않은 파일 형식입니다.");
        };
    }
}
