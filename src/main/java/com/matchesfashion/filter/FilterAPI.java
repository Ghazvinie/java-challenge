package com.matchesfashion.filter;

import com.matchesfashion.filter.model.Product;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;


public class FilterAPI {

    /*  Task 1
        TODO: The test for this is failing :( Check FilterAPITest and fix it plz
     */

    /**
     * @param products List of products
     * @param name     Product name to search for
     * @return List of products that == the name
     */
    public static List<Product> filterProductsByName(List<Product> products, String name) {
        List<Product> filteredProducts = new ArrayList<>();

        for (Product product : products) {
            if (product.getName().equals(name)) {
                filteredProducts.add(product);
            }
        }

        return filteredProducts; // Return filtered products ?
    }

    // Task 2 - Return all products where attribute == name

    /**
     * @param products  List of products
     * @param attribute The attribute to search for (key?)
     * @param value     The value to match that attribute
     * @return List of products that attribute == name
     */
    public static List<Product> filterProducts(List<Product> products, String attribute, String value) {
        List<Product> filteredProducts = new ArrayList<>();

        for (Product product : products) {
            try {
                BigDecimal x = attribute.equals("price") ? new BigDecimal(value) : null;
                boolean y = value.equals("true");
                Field productField = product.getClass().getDeclaredField(attribute);
                Object productValue = productField.get(product);
                boolean canAdd = productValue.equals(value) || Objects.equals(productValue, x) || Objects.equals(productValue, y);
                if (canAdd) {
                    filteredProducts.add(product);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return filteredProducts;
    }

    /**
     * @param products   List of products
     * @param attribute1 The first attribute to search for
     * @param value1     The first value to search for
     * @param attribute2 The second attribute to search for
     * @param value2     The second  value to match that attribute
     * @return List of products that attribute == name
     */

    // Task 3 - Return all products where attribute == name and can use operators (AND OR)
    public static List<Product> filterProductsWithOperator(
            List<Product> products,
            String attribute1,
            String value1,
            String operator,
            String attribute2,
            String value2) {

        List<Product> filteredProducts = new ArrayList<>();

        for (Product product : products) {
            try {
                BigDecimal bigDecPrice1 = attribute1.equals("price") ? new BigDecimal(value1) : null;
                BigDecimal bigDecPrice2 = attribute2.equals("price") ? new BigDecimal(value2) : null;
                boolean onSale1 = value1.equals("true");
                boolean onSale2 = value2.equals("true");

                Field productField1 = product.getClass().getDeclaredField(attribute1);
                Field productField2 = product.getClass().getDeclaredField(attribute2);

                Object productValue1 = productField1.get(product);
                Object productValue2 = productField2.get(product);

                boolean test1 = productValue1.equals(value1) || Objects.equals(productValue1, bigDecPrice1) || Objects.equals(productValue1, onSale1);
                boolean test2 = productValue2.equals(value2) || Objects.equals(productValue2, bigDecPrice2) || Objects.equals(productValue2, onSale2);

                boolean canAdd = (operator.equals("OR") && (test1 || test2)) || (operator.equals("AND") && (test1 && test2));

                if (canAdd) {
                    filteredProducts.add(product);
                }

            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return filteredProducts;
    }
}
