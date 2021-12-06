package com.matchesfashion.filter.model;

import java.math.BigDecimal;

public class Product {
    public String name;
    public String category;
    public BigDecimal price;
    public boolean onSale;

    public Product(String name, String category, BigDecimal price, boolean onSale) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.onSale = onSale;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean getOnSale() {
        return onSale;
    }

    public void setOnSale(boolean onSale) {
        this.onSale = onSale;
    }
}
