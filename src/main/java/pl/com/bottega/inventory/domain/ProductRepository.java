package pl.com.bottega.inventory.domain;


import java.util.Optional;
import java.util.Set;

public interface ProductRepository {

    void save(Product product);

    Optional<Product> getOptional(String id);


    Set<Product> getAll(Set<String> skus);
}
