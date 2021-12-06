package com.matchesfashion.filter;

import com.matchesfashion.filter.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FilterAPITest {

    //Task 1
    @Test
    void should_filter_products_by_name() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Yellow Hat", "hat", new BigDecimal("3.99"), false));
        products.add(new Product("Red Shirt", "shirt", new BigDecimal("14.99"), true));
        products.add(new Product("Green Trousers", "trouser", new BigDecimal("18.50"), true));

        List<Product> actual = FilterAPI.filterProductsByName(products, "Yellow Hat");

        assertEquals(1, actual.size());
        assertEquals("Yellow Hat", actual.get(0).getName());
    }

    //Task 2
    @Test
    //  Task 2 - Return all products where attribute == name
    void should_filter_products_by_attribute() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Yellow Hat", "hat", new BigDecimal("3.99"), false));
        products.add(new Product("Red Hat", "hat", new BigDecimal("5.99"), false));
        products.add(new Product("Red Shirt", "shirt", new BigDecimal("14.99"), true));
        products.add(new Product("Green Trousers", "trouser", new BigDecimal("18.50"), true));

        List<Product> actual = FilterAPI.filterProducts(products, "category", "hat");
        assertEquals(2, actual.size());
        assertEquals("Yellow Hat", actual.get(0).getName());
        assertEquals("Red Hat", actual.get(1).getName());

        actual = FilterAPI.filterProducts(products, "price", "14.99");
        assertEquals(1, actual.size());
        assertEquals("Red Shirt", actual.get(0).getName());

        actual = FilterAPI.filterProducts(products, "onSale", "true");
        assertEquals(2, actual.size());
        assertEquals("Red Shirt", actual.get(0).getName());
        assertEquals("Green Trousers", actual.get(1).getName());

    }

    //Task 3
    @Test
    void should_filter_products_by_attribute_operator() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Yellow Hat", "hat", new BigDecimal("3.99"), false));
        products.add(new Product("Red Hat", "hat", new BigDecimal("5.99"), false));
        products.add(new Product("Pink Hat", "hat", new BigDecimal("5.99"), true));
        products.add(new Product("Red Shirt", "shirt", new BigDecimal("14.99"), true));
        products.add(new Product("Green Trousers", "trouser", new BigDecimal("18.50"), true));
        products.add(new Product("Blue Trousers", "trouser", new BigDecimal("18.50"), false));

        // OR Tests
        List<Product> actual = FilterAPI.filterProductsWithOperator(
                products,
                "category",
                "hat",
                "OR",
                "category",
                "trouser");
        assertEquals(5, actual.size());
        assertEquals("Yellow Hat", actual.get(0).getName());
        assertEquals("Red Hat", actual.get(1).getName());
        assertEquals("Pink Hat", actual.get(2).getName());
        assertEquals("Green Trousers", actual.get(3).getName());
        assertEquals("Blue Trousers", actual.get(4).getName());

        actual = FilterAPI.filterProductsWithOperator(
                products,
                "category",
                "hat",
                "OR",
                "category",
                "shoes");
        assertEquals(3, actual.size());
        assertEquals("Yellow Hat", actual.get(0).getName());
        assertEquals("Red Hat", actual.get(1).getName());
        assertEquals("Pink Hat", actual.get(2).getName());

        actual = FilterAPI.filterProductsWithOperator(
                products,
                "price",
                "3.99",
                "OR",
                "onSale",
                "false");
        assertEquals(3, actual.size());
        assertEquals("Yellow Hat", actual.get(0).getName());
        assertEquals("Red Hat", actual.get(1).getName());
        assertEquals("Blue Trousers", actual.get(2).getName());

        // AND tests
        actual = FilterAPI.filterProductsWithOperator(
                products,
                "price",
                "18.50",
                "AND",
                "onSale",
                "false");
        assertEquals(1, actual.size());
        assertEquals("Blue Trousers", actual.get(0).getName());

        actual = FilterAPI.filterProductsWithOperator(
                products,
                "category",
                "trouser",
                "AND",
                "onSale",
                "true");
        assertEquals(1, actual.size());
        assertEquals("Green Trousers", actual.get(0).getName());

        actual = FilterAPI.filterProductsWithOperator(
                products,
                "category",
                "hat",
                "AND",
                "onSale",
                "false");
        assertEquals(2, actual.size());
        assertEquals("Yellow Hat", actual.get(0).getName());
        assertEquals("Red Hat", actual.get(1).getName());
    }
}


