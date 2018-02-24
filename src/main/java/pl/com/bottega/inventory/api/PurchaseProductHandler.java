package pl.com.bottega.inventory.api;

import org.springframework.stereotype.Component;
import pl.com.bottega.inventory.api.dto.PurchaseReport;
import pl.com.bottega.inventory.api.dto.ReportBuilder;
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

@Component
public class PurchaseProductHandler {

    private ProductRepository repository;

    public PurchaseProductHandler(ProductRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public PurchaseReport handle(PurchaseProductsCommand cmd) {
        Set<String> skus = cmd.getProducts().keySet();
        repository.checkProductKinds(skus);
        Set<Product> availableProducts = repository.getAll(skus);
        Map<String, Long> required = cmd.getProducts();
        Set<Product> availableForPurchase = new HashSet<>();
        Set<Product> notEnoughForPurchase = new HashSet<>();
        availableProducts.forEach(p -> {
            if (p.isEnough(required))
                availableForPurchase.add(p);
            else
                notEnoughForPurchase.add(p);
        });
        if (availableForPurchase.size() == required.size()) {
            return performPurchase(availableForPurchase, required);
        } else
            return createResponse(notEnoughForPurchase, required);
    }




    private PurchaseReport createResponse(Set<Product> products, Map<String, Long> required) {
        Map<String, Long> choosen = new HashMap<>();
        for (Product p : products) {
            if (!p.isEnough(required)) {
                choosen.put(p.getSkuCode(), required.get(p.getSkuCode()));
            }
        }
        return ReportBuilder.createReport(false, choosen);
    }

    private PurchaseReport performPurchase(Set<Product> products, Map<String, Long> required) {
        for (Product p : products) {
            p.updateAmount(required);
            repository.update(p);
        }
        return ReportBuilder.createReport(true, required);
    }


}
