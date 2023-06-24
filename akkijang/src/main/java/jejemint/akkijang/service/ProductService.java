package jejemint.akkijang.service;

import java.util.List;
import jejemint.akkijang.controller.dto.ProductCreateRequestDto;
import jejemint.akkijang.domain.Category;
import jejemint.akkijang.domain.Product;
import jejemint.akkijang.domain.Region;
import jejemint.akkijang.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final ImageUploadService imageUploadService;

    public Long createProduct(ProductCreateRequestDto request) {
        final Category category = Category.fromCode(request.getCategoryCode());
        final Region region = Region.fromCode(request.getRegionCode());

        final String imageUrl = imageUploadService.uploadImage(request.getImage());

        final Product product = Product.create(request.getTitle(),
                request.getContent(),
                request.getPrice(),
                imageUrl,
                category,
                region);
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(final Long productId) {
        return productRepository.findById(productId);
    }
}
