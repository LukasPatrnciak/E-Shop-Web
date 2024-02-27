package sk.lukaspatrnciak.eshopweb.product.web;

import sk.lukaspatrnciak.eshopweb.product.data.Product;

public class ProductResponse {
    private final Long id;
    private final String name;
    private final String description;
    private final Long amount;
    private final String unit;
    private final double price;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.amount = product.getAmount();
        this.unit = product.getUnit();
        this.price = product.getPrice();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }

    public double getPrice() {
        return price;
    }
}
