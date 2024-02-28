package sk.lukaspatrnciak.eshopweb.shoppinglist.web;

import sk.lukaspatrnciak.eshopweb.shoppinglist.data.ShoppingList;

public class ShoppingListRequest {
    private Long productId;
    private Long amount;

    public ShoppingListRequest(ShoppingList shoppingList) {
        this.productId = shoppingList.getProduct().getId();
        this.amount = shoppingList.getAmount();
    }

    public ShoppingListRequest() {
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
