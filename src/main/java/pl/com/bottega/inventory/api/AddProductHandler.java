package pl.com.bottega.inventory.api;

import org.springframework.stereotype.Component;
import pl.com.bottega.inventory.domain.Product;
import pl.com.bottega.inventory.domain.ProductRepository;
import pl.com.bottega.inventory.domain.commands.AddProductCommand;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.function.Supplier;

@Component
public class AddProductHandler {

    private ProductRepository repository;


    public AddProductHandler(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void handle(AddProductCommand cmd) {
        Optional<Product> optionalProduct = repository.getOptional(cmd.getSkuCode());
        Product product = optionalProduct.orElse(new Product(cmd));
        if (optionalProduct.isPresent())
            product.addToCount(cmd.getAmount());
        repository.save(product);
    }
}
