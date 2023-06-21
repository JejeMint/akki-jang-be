package jejemint.akkijang.repository;

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
}
