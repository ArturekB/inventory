package pl.com.bottega.inventory.api.dto;

public abstract class PurchaseReport {

    private boolean success;

    PurchaseReport(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
