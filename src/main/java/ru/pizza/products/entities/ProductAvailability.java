package ru.pizza.products.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductAvailability {
    private int id;
    private String name;
    private String description;
    private String urlImage;
    private int price;
    private Product.Type type;
    private List<Building> buildingList = new ArrayList<>();

    public ProductAvailability(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.urlImage = product.getUrlImage();
        this.price = product.getPrice();
        this.type = product.getType();
    }
}
