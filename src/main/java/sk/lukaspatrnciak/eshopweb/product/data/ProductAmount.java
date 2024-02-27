package sk.lukaspatrnciak.eshopweb.product.data;

public class ProductAmount {
    private Long amount;

    public ProductAmount() {
    }

    public ProductAmount(Long amount) {
        this.amount = amount;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}