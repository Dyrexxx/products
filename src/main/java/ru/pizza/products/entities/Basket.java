package ru.pizza.products.entities;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Basket {
    private String fio;
    private String address;
    private List<BasketItem> basketViewItemList = new ArrayList<>();

    @Data
    public static class BasketItem {
        private int buildingId;
        private String productName;
        private int price;
        private int count;
        private List<Expenditure> ingredients;
    }
}
