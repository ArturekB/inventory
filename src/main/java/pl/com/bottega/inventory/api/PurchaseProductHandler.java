package pl.com.bottega.inventory.api;

import org.springframework.stereotype.Component;
import pl.com.bottega.inventory.api.dto.FailReport;
import pl.com.bottega.inventory.api.dto.PurchaseReport;
import pl.com.bottega.inventory.api.dto.SuccessReport;
import pl.com.bottega.inventory.domain.Product;
import pl.com.bottega.inventory.domain.ProductRepository;
import pl.com.bottega.inventory.domain.commands.InvalidCommandException;
import pl.com.bottega.inventory.domain.commands.PurchaseProductsCommand;
import pl.com.bottega.inventory.domain.commands.Validatable;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PurchaseProductHandler {

    private ProductRepository repository;

    public PurchaseProductHandler(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public PurchaseReport handle(PurchaseProductsCommand cmd) {
        Set<String> skus = cmd.getProducts().keySet();
        Set<Product> availableProducts = repository.getAll(skus);

        validateSkuCodesOfProducts(skus, availableProducts);

        Map<String, Long> required = cmd.getProducts();
        Set<Product> availableForPurchase = new HashSet<>();
        Set<Product> notEnoughForPurchase = new HashSet<>();

        checkAvailabilityOfAllProducts(availableProducts, required, availableForPurchase, notEnoughForPurchase);

        if (availableForPurchase.size() == required.size()) {
            return performPurchase(availableForPurchase, required);
        } else
            return createResponse(notEnoughForPurchase, required);
    }

    private void validateSkuCodesOfProducts(Set<String> skus, Set<Product> availableProducts) {
        Validatable.ValidationErrors errors = new Validatable.ValidationErrors();
        Set<String> availableProductsSkus = availableProducts.stream().map(Product::getSkuCode).collect(Collectors.toSet());
        for (String s : skus) {
            if (!availableProductsSkus.contains(s)) {
                errors.add(s, "no such sku");
            }
        }
        if (!errors.isValid())
            throw new InvalidCommandException(errors);
    }

    private void checkAvailabilityOfAllProducts(Set<Product> availableProducts, Map<String, Long> required, Set<Product> availableForPurchase, Set<Product> notEnoughForPurchase) {
        availableProducts.forEach(p -> {
            if (p.isEnough(required.get(p.getSkuCode())))
                availableForPurchase.add(p);
            else
                notEnoughForPurchase.add(p);
        });
    }


    private PurchaseReport createResponse(Set<Product> products, Map<String, Long> required) {
        Map<String, Long> choosen = new HashMap<>();
        for (Product p : products) {
            if (!p.isEnough(required.get(p.getSkuCode()))) {
                choosen.put(p.getSkuCode(), required.get(p.getSkuCode()));
            }
        }
        return new FailReport(choosen);
    }

    private PurchaseReport performPurchase(Set<Product> products, Map<String, Long> required) {
        for (Product p : products) {
            p.updateAmount(required.get(p.getSkuCode()));
            repository.save(p);
        }
        return new SuccessReport(required);
    }


}
