package pl.com.bottega.inventory.infrastructure;

import javafx.beans.property.SetProperty;
import org.hibernate.sql.Select;
import org.springframework.stereotype.Component;
import pl.com.bottega.inventory.domain.Product;
import pl.com.bottega.inventory.domain.ProductRepository;
import pl.com.bottega.inventory.domain.commands.InvalidCommandException;
import pl.com.bottega.inventory.domain.commands.Validatable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class JpaProductRepository implements ProductRepository{

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
    public void update(Product product) {
        entityManager.merge(product);
    }

    @Override
    public Optional<Product> getOptional(String id) {
        Product product =  entityManager.find(Product.class, id);
        return product == null ? Optional.empty(): Optional.of(product);
    }

    //TODO - zbyt dużo zapytań, można zrobić w jednym !!!
    @Override
    public void checkProductKinds(Set<String> skus) {
        Validatable.ValidationErrors errors = new Validatable.ValidationErrors();
        for (String s: skus) {
            if (!getOptional(s).isPresent()){
                errors.add(s,"no such sku");
            }
        }
        if (!errors.isValid())
            throw new InvalidCommandException(errors);
    }

    //TODO za dużo zapytań, to samo co wyżej
    @Override
    public Set<Product> getAll(Set<String> skus) {
        Set<Product>  result = new HashSet<>();
        for (String s: skus) {
            result.add(getOptional(s).get());
        }
        return result;
    }

}
