package pl.com.bottega.inventory.api.dto;

import java.util.Map;

public class PurchaseReport {

    private boolean success;
    private Map<String, Long> purchasedProducts;
    private Map<String, Long> missingProducts;

    public PurchaseReport(boolean success, Map<String, Long> products) {
        this.success = success;
        if(success)
            purchasedProducts = products;
        else missingProducts = products;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Map<String, Long> getPurchasedProducts() {
        return purchasedProducts;
    }

    public void setPurchasedProducts(Map<String, Long> purchasedProducts) {
        this.purchasedProducts = purchasedProducts;
    }

    public Map<String, Long> getMissingProducts() {
        return missingProducts;
    }

    public void setMissingProducts(Map<String, Long> missingProducts) {
        this.missingProducts = missingProducts;
    }
}
