package ru.pizza.products.entities;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    @Column(name = "title")
    protected String name;

    @Column(name = "description")
    private String description;

    @Column(name = "url_image")
    private String urlImage;

    @Column(name = "price")
    private int price;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Product.Type type;

    @JsonManagedReference
    @OneToMany(mappedBy = "product")
    private List<Expenditure> ingredientList;

    @Transient
    private ImageProduct imageProduct;

    public enum Type {
        PIZZA, COFFEE;
    }
}