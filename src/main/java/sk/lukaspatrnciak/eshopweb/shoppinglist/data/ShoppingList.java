package sk.lukaspatrnciak.eshopweb.shoppinglist.data;

import lombok.Data;
import sk.lukaspatrnciak.eshopweb.product.data.Product;

import javax.persistence.*;

@Data
@Entity
public class ShoppingList {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Product product;
    private Long amount;

    public ShoppingList() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}