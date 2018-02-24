package pl.com.bottega.inventory.domain.commands;

public class AddProductCommand implements Validatable {

    private String skuCode;
    private Long amount;

    @Override
    public void validate(ValidationErrors errors) {
        validatePresenceOf(skuCode, "skuCode", errors);
        validatePresenceOf(amount, "amount", errors);
        if (amount != null)
            validateRangeOfAmount(amount, errors);
    }

    private void validateRangeOfAmount(Long amount, ValidationErrors errors) {
        if (amount<1||amount>999)
            errors.add("amount","must be between 1 and 999");
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
