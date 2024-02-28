package sk.lukaspatrnciak.eshopweb.itemcart.web;

import sk.lukaspatrnciak.eshopweb.itemcart.data.ItemCart;

public class ItemCartAddRequest {
    private Long productId;
    private Long amount;

    public ItemCartAddRequest() {
    }

    public ItemCartAddRequest(ItemCart itemCart) {
        this.productId = itemCart.getProduct().getId();
        this.amount = itemCart.getAmount();
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
