package pl.com.bottega.inventory.domain;

import pl.com.bottega.inventory.domain.commands.AddProductCommand;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Map;

@Entity
public class Product {

    @Id
    private String skuCode;

    private Long amount;


    public Product() {
    }

    public Product(String skuCode, Long amount) {
        this.skuCode = skuCode;
        this.amount = amount;
    }

    public Product(AddProductCommand cmd) {
        skuCode = cmd.getSkuCode();
        amount = cmd.getAmount();
    }

    public void addToCount(Long amount) {
        this.amount += amount;
    }


    public boolean isEnough(Long requestedAmount) {
        return requestedAmount <= amount;
    }

    public void updateAmount(Long count) {
        amount -= count;
    }


    public String getSkuCode() {
        return skuCode;
    }
}


