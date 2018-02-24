package pl.com.bottega.inventory.api.dto;

import java.util.Map;

public class SuccessReport extends PurchaseReport {

    private Map<String, Long> purchasedProducts;


    public SuccessReport(Map<String, Long> map) {
        super(true);
        purchasedProducts = map;
    }

    public Map<String, Long> getPurchasedProducts() {
        return purchasedProducts;
    }

    public void setPurchasedProducts(Map<String, Long> purchasedProducts) {
        this.purchasedProducts = purchasedProducts;
    }
}
