package ru.pizza.products.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "expenditure")
public class Expenditure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "weight")
    private int weight;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;


    public Expenditure() {

    }
}
