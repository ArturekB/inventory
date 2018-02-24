package pl.com.bottega.inventory.api.dto;

import java.util.Map;

public class FailReport extends PurchaseReport {

    private Map<String, Long> missingProducts;

    public FailReport(Map<String, Long> map) {
        super(false);
        missingProducts = map;
    }


    public Map<String, Long> getMissingProducts() {
        return missingProducts;
    }

    public void setMissingProducts(Map<String, Long> missingProducts) {
        this.missingProducts = missingProducts;
    }
}
