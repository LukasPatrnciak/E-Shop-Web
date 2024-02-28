package sk.lukaspatrnciak.eshopweb.cart.web;

import sk.lukaspatrnciak.eshopweb.cart.data.Cart;
import sk.lukaspatrnciak.eshopweb.shoppinglist.web.ShoppingListRequest;

import java.util.List;
import java.util.stream.Collectors;

public class CartResponse {
    private long id;

    private final List<ShoppingListRequest> shoppingList;

    private boolean payed;

    public CartResponse(Cart cart) {
        this.id = cart.getId();
        this.shoppingList = cart.getShoppingList().stream().map(ShoppingListRequest::new).collect(Collectors.toList());
        this.payed = cart.isPaid();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ShoppingListRequest> getShoppingList() {
        return shoppingList;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }
}
