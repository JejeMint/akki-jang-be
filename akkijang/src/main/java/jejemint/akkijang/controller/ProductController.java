package jejemint.akkijang.controller;

import java.net.URI;
import jejemint.akkijang.controller.dto.ProductCreateRequestDto;
import jejemint.akkijang.domain.Category;
import jejemint.akkijang.domain.Product;
import jejemint.akkijang.domain.Region;
import jejemint.akkijang.service.ImageUploadService;
import jejemint.akkijang.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;
    private final ImageUploadService imageUploadService;

    @PostMapping("/products")
    public ResponseEntity<Void> createProduct(@ModelAttribute ProductCreateRequestDto request) {
        final String imageUrl = imageUploadService.uploadImage(request.getImage());

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
