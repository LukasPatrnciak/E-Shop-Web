package sk.lukaspatrnciak.eshopweb.cart.data;

import lombok.Data;
import sk.lukaspatrnciak.eshopweb.shoppinglist.data.ShoppingList;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(orphanRemoval = true)
    private List<ShoppingList> shoppingList;

    private boolean paid;

    public Cart() {
        shoppingList = new ArrayList<>();
        paid = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ShoppingList> getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(List<ShoppingList> shoppingList) {
        this.shoppingList = shoppingList;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
