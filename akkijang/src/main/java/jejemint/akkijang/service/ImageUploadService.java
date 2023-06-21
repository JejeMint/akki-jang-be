package jejemint.akkijang.service;

import java.io.File;
import java.io.IOException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageUploadService {

    private static final String uploadDir = "/Users/jaemin/Desktop/workspace/project/akki-jang-be/akkijang/src/main/resources/image/";

    public String uploadImage(MultipartFile image) {
        try {
            String imageName = image.getOriginalFilename();
            String imageUrl = uploadDir + imageName;
            image.transferTo(new File(imageUrl));
            return imageUrl;
        } catch (IOException e) {
            throw new IllegalArgumentException("파일 업로드에 실패했습니다.", e);
        }
    }
}
