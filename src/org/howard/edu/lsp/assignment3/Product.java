package org.howard.edu.lsp.assignment3;

public class Product {
    private final int id;
    private final String name;
    private final double price;
    private final String category;
    private final PriceRange priceRange;

    public Product(int id, String name, double price, String category, PriceRange priceRange) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.priceRange = priceRange;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public PriceRange getPriceRange() {
        return priceRange;
    }

    public Product withName(String updatedName) {
        return new Product(id, updatedName, price, category, priceRange);
    }

    public Product withPrice(double updatedPrice) {
        return new Product(id, name, updatedPrice, category, priceRange);
    }

    public Product withCategory(String updatedCategory) {
        return new Product(id, name, price, updatedCategory, priceRange);
    }

    public Product withPriceRange(PriceRange updatedPriceRange) {
        return new Product(id, name, price, category, updatedPriceRange);
    }
}
