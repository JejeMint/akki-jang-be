package jejemint.akkijang.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import jejemint.akkijang.controller.dto.ProductCreateRequestDto;
import jejemint.akkijang.controller.dto.ProductSimpleSelectResponseDto;
import jejemint.akkijang.domain.Product;
import jejemint.akkijang.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
        return ResponseEntity.created(URI.create("/api/products/" + savedId)).build();
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductSimpleSelectResponseDto>> showProducts() {
        final List<Product> products = productService.getAllProducts();
        final List<ProductSimpleSelectResponseDto> responseDto = products.stream()
                .map(product -> ProductSimpleSelectResponseDto.from(product))
                .collect(Collectors.toUnmodifiableList());
        return ResponseEntity.ok(responseDto);
    }
}
