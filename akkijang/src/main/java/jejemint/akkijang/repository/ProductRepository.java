package jejemint.akkijang.repository;

import java.util.List;
import javax.persistence.EntityManager;
import jejemint.akkijang.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ProductRepository {

    private final EntityManager em;

    public Long save(Product product) {
        em.persist(product);
        return product.getId();
    }

    public List<Product> findAll() {
        return em.createQuery("select p from Product p", Product.class)
                .getResultList();
    }

    public Product findById(final Long productId) {
        return em.find(Product.class, productId);
    }
}
