package pl.com.bottega.inventory.api.dto;

import java.util.Map;

public class SuccessReport implements PurchaseReport {

    private boolean success;
    private Map<String, Long> purchasedProducts;

    public SuccessReport() {
    }

    SuccessReport(Map<String, Long> map) {
        success = true;
        purchasedProducts = map;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean status) {
        this.success = status;
    }

    public Map<String, Long> getPurchasedProducts() {
        return purchasedProducts;
    }

    public void setPurchasedProducts(Map<String, Long> purchasedProducts) {
        this.purchasedProducts = purchasedProducts;
    }
}
