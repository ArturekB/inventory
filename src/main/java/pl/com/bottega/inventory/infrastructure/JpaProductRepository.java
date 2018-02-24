package pl.com.bottega.inventory.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.bottega.inventory.domain.Product;
import pl.com.bottega.inventory.domain.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class JpaProductRepository implements ProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public JpaProductRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Product product) {
        entityManager.persist(product);
    }

    @Override
    public Optional<Product> getOptional(String id) {
        Product product = entityManager.find(Product.class, id);
        return product == null ? Optional.empty() : Optional.of(product);
    }

    @Override
    public Set<Product> getAll(Set<String> skus) {
        Set<Product> result = new HashSet<>();
        for (String s : skus) {
            if (getOptional(s).isPresent())
                result.add(getOptional(s).get());
        }
        return result;
    }

}
