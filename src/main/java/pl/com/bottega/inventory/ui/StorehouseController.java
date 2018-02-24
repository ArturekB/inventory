package pl.com.bottega.inventory.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.com.bottega.inventory.api.AddProductHandler;
import pl.com.bottega.inventory.api.PurchaseProductHandler;
import pl.com.bottega.inventory.api.dto.PurchaseReport;
import pl.com.bottega.inventory.domain.commands.AddProductCommand;
import pl.com.bottega.inventory.domain.commands.PurchaseProductsCommand;

import java.util.Map;

@RestController
public class StorehouseController {

    private AddProductHandler addProductHandler;

    private PurchaseProductHandler purchaseProductHandler;

    public StorehouseController(AddProductHandler addProductHandler, PurchaseProductHandler purchaseProductHandler, ObjectMapper objectMapper) {
        this.addProductHandler = addProductHandler;
        this.purchaseProductHandler = purchaseProductHandler;
    }

    @PostMapping("/inventory")
    public void addProduct(@RequestBody AddProductCommand cmd){
        addProductHandler.handle(cmd);
    }

    @PostMapping("/purchase")
    public PurchaseReport purchaseProducts(@RequestBody Map<String,Long> map){
        PurchaseProductsCommand cmd = new PurchaseProductsCommand();
        cmd.setProducts(map);
        return purchaseProductHandler.handle(cmd);
    }

}
