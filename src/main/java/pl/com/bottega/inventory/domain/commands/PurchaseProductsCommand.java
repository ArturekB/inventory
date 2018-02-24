package pl.com.bottega.inventory.domain.commands;

import pl.com.bottega.inventory.domain.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PurchaseProductsCommand implements Validatable {

    private Map<String, Long> products = new HashMap<>();

    public Map<String, Long> getProducts() {
        return products;
    }

    public void setProducts(Map<String, Long> products) {
        this.products = products;
    }


    @Override
    public void validate(ValidationErrors errors) {
        if (products.isEmpty())
            errors.add("skus", "are required");
        validateCounts(errors);
    }

    private void validateCounts(ValidationErrors errors) {
        for (String key : products.keySet()) {
            if (products.get(key) <= 0 || products.get(key) > 999)
                errors.add(key, "must be between 1 and 999");
        }
    }
}
