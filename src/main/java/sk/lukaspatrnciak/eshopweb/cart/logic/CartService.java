package sk.lukaspatrnciak.eshopweb.cart.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.lukaspatrnciak.eshopweb.exeption.IllegalOperationException;
import sk.lukaspatrnciak.eshopweb.exeption.NotFoundException;
import sk.lukaspatrnciak.eshopweb.cart.data.CartRepositoryInterface;
import sk.lukaspatrnciak.eshopweb.cart.data.Cart;
import sk.lukaspatrnciak.eshopweb.shoppinglist.data.ShoppingList;
import sk.lukaspatrnciak.eshopweb.shoppinglist.logic.ShoppingListService;
import sk.lukaspatrnciak.eshopweb.product.data.Product;
import sk.lukaspatrnciak.eshopweb.product.logic.ProductServiceInterface;
import sk.lukaspatrnciak.eshopweb.shoppinglist.web.ShoppingListRequest;

@Service
public class CartService implements CartServiceInterface {

    @Autowired
    private CartRepositoryInterface cartRepository;

    @Autowired
    private ShoppingListService shoppingListService;

    @Autowired
    private ProductServiceInterface productService;

    @Override
    public Cart createCart() {
        return cartRepository.save(new Cart());
    }

    @Override
    public Cart getCartById(Long id) throws NotFoundException {
        Cart cart = cartRepository.findCartById(id);

        if (cart != null){
            return cart;

        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public void deleteCartById(Long id) throws NotFoundException {
        if (id != null){
            this.cartRepository.delete(this.getCartById(id));
        }
    }

    @Override
    public Cart addProductToCartById(Long id, ShoppingListRequest shoppingList) throws NotFoundException, IllegalOperationException {
        Cart cart = cartRepository.findCartById(id);
        Long idOfProductInCart = shoppingList.getProductId();
        Product product = productService.getById(idOfProductInCart);

        if (cart != null){
            if (isCartPayed(cart)){
                throw new IllegalOperationException();

            } else {
                if (productService.isSufficientAmount(idOfProductInCart,shoppingList.getAmount())){
                    ShoppingList shoppingListInCurrentCart = productService.isProductInList(idOfProductInCart,cart.getShoppingList());
                    if (shoppingListInCurrentCart != null){
                        Long productAmountBefore = product.getAmount();
                        Long productAmountInCartBefore = shoppingListInCurrentCart.getAmount();
                        product.setAmount(productAmountBefore- shoppingListInCurrentCart.getAmount());
                        shoppingListInCurrentCart.setAmount(productAmountInCartBefore+productAmountBefore);

                    } else {
                        Long productAmountBefore = product.getAmount();
                        product.setAmount(productAmountBefore- shoppingList.getAmount());
                        ShoppingList newShoppingListToAdd = shoppingListService.createShoppingList();
                        newShoppingListToAdd.setProduct(product);
                        newShoppingListToAdd.setAmount(shoppingList.getAmount());
                        cart.getShoppingList().add(newShoppingListToAdd);
                    }

                } else {
                    throw new IllegalOperationException();
                }

                return cartRepository.save(cart);
            }

        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public boolean isCartPayed(Cart cart) {
        if (cart.isPaid()){
            return true;
        } else return false;

    }


    @Override
    public double payForCart(Long id) throws NotFoundException, IllegalOperationException {
        Cart cart = getCartById(id);
        double payment = 0;

        if (isCartPayed(cart)) {
            throw new IllegalOperationException();

        } else {
            for (ShoppingList shoppingList: cart.getShoppingList()){
                payment += shoppingList.getAmount() * shoppingList.getProduct().getPrice();
            }

            cart.setPaid(true);
            cartRepository.save(cart);
            return payment;
        }
    }
}
