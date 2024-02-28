package sk.lukaspatrnciak.eshopweb.product.logic;

import org.springframework.beans.factory.annotation.Autowired;
import sk.lukaspatrnciak.eshopweb.shoppinglist.data.ShoppingList;
import sk.lukaspatrnciak.eshopweb.product.data.ProductRepositoryInterface;
import sk.lukaspatrnciak.eshopweb.exeption.NotFoundException;
import sk.lukaspatrnciak.eshopweb.exeption.*;
import org.springframework.stereotype.Service;
import sk.lukaspatrnciak.eshopweb.product.data.Product;
import sk.lukaspatrnciak.eshopweb.product.web.ProductRequest;
import sk.lukaspatrnciak.eshopweb.product.web.ProductUpdate;

import java.util.List;

@Service
public class ProductService implements ProductServiceInterface {

    @Autowired
    private ProductRepositoryInterface repository;

    public ProductService(ProductRepositoryInterface repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> getAll(){
        return this.repository.findAll();
    }

    @Override
    public Product createProduct(ProductRequest body){
        Product a = new Product(
                body.getName(),
                body.getDescription(),
                body.getAmount(),
                body.getUnit(),
                body.getPrice());
        return this.repository.save(a);
    }

    @Override
    public Product getById(Long id) throws NotFoundException {
        Product b = this.repository.findProductById(id);
        if (b == null) {
            throw new NotFoundException();
        }
        return b;

    }
    @Override
    public Product update(Long id, ProductUpdate request) throws NotFoundException {
        Product b = this.getById(id);
        if (request.getName() != null) {
            b.setName(request.getName());
        }
        if (request.getDescription() != null) {
            b.setDescription(request.getDescription());
        }
        if (request.getUnit() != null) {
            b.setUnit(request.getUnit());
        }
        if (request.getPrice() != 0) {
            b.setPrice(request.getPrice());
        }
        return this.repository.save(b);
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        Product b = this.getById(id);

        this.repository.delete(b);
    }

    @Override
    public Long getAmount(Long id) throws NotFoundException {
        return this.getById(id).getAmount();
    }

    @Override
    public Long addAmount(Long id, Long increment) throws NotFoundException {
        Product b = this.getById(id);
        b.setAmount(b.getAmount() + increment);
        this.repository.save(b);

        return b.getAmount();
    }

    @Override
    public void removeAmount(Long id, Long decrement) throws NotFoundException, IllegalOperationException {
        Product p = this.getById(id);

        if (p.getAmount() < decrement) {
            throw new IllegalOperationException();
        }

        p.setAmount(p.getAmount() - decrement);

        this.repository.save(p);
    }

    @Override
    public boolean isSufficientAmount(Long id, Long decrementAmount) throws NotFoundException {
        Product product = returnExistingPrduct(id);

        if (product.getAmount() - decrementAmount < 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public ShoppingList isProductInList(Long id, List<ShoppingList> shoppingList) throws NotFoundException {
        Product product = getById(id);
        ShoppingList itemCart = null;

        for (ShoppingList shoppingListIterator : shoppingList) {
            assert false;

            if (product.equals(itemCart.getProduct())) {
                itemCart = shoppingListIterator;
            }
        }
        return itemCart;
    }

    @Override
    public Product returnExistingPrduct(Long id) throws NotFoundException {
        Product product = getById(id);

        if (product != null) {
            return product;
        } else {
            throw new NotFoundException();
        }
    }
}