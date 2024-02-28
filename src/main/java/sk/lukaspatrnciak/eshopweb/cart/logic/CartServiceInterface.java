package sk.lukaspatrnciak.eshopweb.cart.logic;

import org.springframework.stereotype.Repository;
import sk.lukaspatrnciak.eshopweb.exeption.IllegalOperationException;
import sk.lukaspatrnciak.eshopweb.exeption.NotFoundException;
import sk.lukaspatrnciak.eshopweb.cart.data.Cart;
import sk.lukaspatrnciak.eshopweb.shoppinglist.web.ShoppingListRequest;

@Repository
public interface CartServiceInterface {
    Cart createCart();

    Cart getCartById(Long id) throws NotFoundException;

    void deleteCartById(Long id) throws NotFoundException;

    Cart addProductToCartById(Long id, ShoppingListRequest shoppingList) throws NotFoundException, IllegalOperationException;

    boolean isCartPayed(Cart cart);

    double payForCart(Long id) throws NotFoundException, IllegalOperationException;
}
