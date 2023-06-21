package jejemint.akkijang.controller;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import jejemint.akkijang.controller.dto.ProductCreateRequestDto;
import jejemint.akkijang.domain.Category;
import jejemint.akkijang.domain.Product;
import jejemint.akkijang.domain.Region;
import jejemint.akkijang.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private static final String uploadDir = "/Users/jaemin/Desktop/workspace/project/akki-jang-be/akkijang/src/main/resources/image/";

    private final ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<Void> createProduct(@ModelAttribute ProductCreateRequestDto request) throws IOException {
        System.out.println("request = " + request);
        final MultipartFile image = request.getImage();
        String imageName = image.getOriginalFilename();
        String imageUrl = uploadDir + imageName;
        image.transferTo(new File(imageUrl));

        final Product product = Product.create(request.getTitle(),
                request.getContent(),
                request.getPrice(),
                imageUrl,
                Category.fromCode(request.getCategoryCode()),
                Region.fromCode(request.getRegionCode()));
        final Long savedId = productService.createProduct(product);

        return ResponseEntity.created(URI.create("/products/" + savedId)).build();
    }
}
