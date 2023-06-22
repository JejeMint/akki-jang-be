package jejemint.akkijang.service;

import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageUploadService {

    @Value("${image.dir}")
    private String imageDir;
    @Value("${upload.dir}")
    private String uploadDir;

    public String uploadImage(MultipartFile image) {
        try {
            String imageName = image.getOriginalFilename();
            image.transferTo(new File(uploadDir + imageName));
            return imageDir + imageName;
        } catch (IOException e) {
            throw new IllegalArgumentException("파일 업로드에 실패했습니다.", e);
        }
    }
}
