package ru.pizza.products.dto.response;

import lombok.Data;
import ru.pizza.products.entities.Expenditure;

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
        private String title;
        private int price;
        private int count;
        private List<Expenditure> ingredients;
    }
}
