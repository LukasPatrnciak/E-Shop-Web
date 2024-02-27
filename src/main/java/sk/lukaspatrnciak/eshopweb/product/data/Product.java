package sk.lukaspatrnciak.eshopweb.product.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private Long amount;
    private String unit;
    private double price;

    public Product() {
    }

    public Product(String name, String description, Long amount, String unit, double price) {
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.unit = unit;
        this.price = price;
    }
}