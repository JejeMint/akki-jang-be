package jejemint.akkijang.controller;

import java.net.URI;
import jejemint.akkijang.controller.dto.ProductCreateRequestDto;
import jejemint.akkijang.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<Void> createProduct(@ModelAttribute ProductCreateRequestDto request) {
        final Long savedId = productService.createProduct(request);

        return ResponseEntity.created(URI.create("/products/" + savedId)).build();
    }
}
