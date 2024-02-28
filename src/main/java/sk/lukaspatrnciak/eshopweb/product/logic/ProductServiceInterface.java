package sk.lukaspatrnciak.eshopweb.product.logic;

import sk.lukaspatrnciak.eshopweb.exeption.IllegalOperationException;
import sk.lukaspatrnciak.eshopweb.exeption.NotFoundException;
import sk.lukaspatrnciak.eshopweb.shoppinglist.data.ShoppingList;
import sk.lukaspatrnciak.eshopweb.product.data.Product;
import sk.lukaspatrnciak.eshopweb.product.web.ProductRequest;
import sk.lukaspatrnciak.eshopweb.product.web.ProductUpdate;

import java.util.List;

public interface ProductServiceInterface {
    List<Product> getAll();

    Product createProduct(ProductRequest body) throws NotFoundException;

    Product getById(Long id) throws NotFoundException;

    Product update(Long id, ProductUpdate request) throws NotFoundException;

    void delete(Long id) throws NotFoundException;

    Long getAmount(Long id) throws NotFoundException;

    Long addAmount(Long id, Long increment) throws NotFoundException;

    void removeAmount(Long id, Long decrement) throws NotFoundException, IllegalOperationException;

    boolean isSufficientAmount(Long id, Long decrementAmount) throws NotFoundException;

    ShoppingList isProductInList(Long id, List<ShoppingList> shoppingList) throws NotFoundException;

    Product returnExistingPrduct(Long id)throws NotFoundException;
}
