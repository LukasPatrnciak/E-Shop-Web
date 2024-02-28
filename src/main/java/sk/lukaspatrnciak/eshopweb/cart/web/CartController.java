package sk.lukaspatrnciak.eshopweb.cart.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.lukaspatrnciak.eshopweb.cart.logic.CartServiceInterface;
import sk.lukaspatrnciak.eshopweb.exeption.*;
import sk.lukaspatrnciak.eshopweb.shoppinglist.web.ShoppingListRequest;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartServiceInterface cartService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CartResponse> createCart() {
        return new ResponseEntity<>(new CartResponse(cartService.createCart()), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CartResponse getCart(@PathVariable("id") Long id) throws NotFoundException {
        return new CartResponse(cartService.getCartById(id));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCart(@PathVariable("id") Long id) throws NotFoundException {
        cartService.deleteCartById(id);
    }

    @PostMapping(value = "/{id}/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CartResponse addToCart(@PathVariable("id") Long id, @RequestBody ShoppingListRequest itemCartAddRequest) throws NotFoundException, IllegalOperationException {
        return new CartResponse(cartService.addProductToCartById(id,itemCartAddRequest));
    }

    @GetMapping(value = "/{id}/pay", produces = MediaType.TEXT_PLAIN_VALUE)
    public String payForCart(@PathVariable("id") Long id) throws NotFoundException, IllegalOperationException {
        return "" + cartService.payForCart(id);
    }
}
