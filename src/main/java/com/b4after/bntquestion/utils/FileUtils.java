package com.b4after.bntquestion.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileUtils {

    public static String extractExtension(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        validateFileNull(originalFilename);
        int index = originalFilename.lastIndexOf(".");
        return originalFilename.substring(index + 1);
    }

    private static void validateFileNull(String originalFilename) {
        if (originalFilename == null) {
            throw new IllegalArgumentException("사용할 수 없는 파일 입니다.");
        }
    }
}
