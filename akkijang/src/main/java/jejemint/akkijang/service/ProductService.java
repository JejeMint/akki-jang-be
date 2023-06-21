package jejemint.akkijang.service;

import jejemint.akkijang.domain.Product;
import jejemint.akkijang.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public Long createProduct(Product product) {
        return productRepository.save(product);
    }
}
