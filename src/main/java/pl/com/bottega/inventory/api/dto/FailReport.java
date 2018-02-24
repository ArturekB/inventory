package pl.com.bottega.inventory.api.dto;

import java.util.Map;

public class FailReport implements PurchaseReport {

    private boolean success;
    private Map<String, Long> missingProducts;

    FailReport(Map<String, Long> map) {
        success = false;
        missingProducts = map;
    }

    public FailReport() {
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Map<String, Long> getMissingProducts() {
        return missingProducts;
    }

    public void setMissingProducts(Map<String, Long> missingProducts) {
        this.missingProducts = missingProducts;
    }
}
