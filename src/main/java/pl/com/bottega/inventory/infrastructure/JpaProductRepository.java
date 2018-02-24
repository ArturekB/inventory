package pl.com.bottega.inventory.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.bottega.inventory.domain.Product;
import pl.com.bottega.inventory.domain.ProductRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.*;
import java.util.stream.Collectors;

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
        List<Product> result = entityManager.createQuery("FROM Product p WHERE (p.skuCode IN :values)", Product.class)
                .setParameter("values", skus).getResultList();
        return new HashSet<>(result);
    }

}
