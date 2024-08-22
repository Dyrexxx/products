package ru.pizza.products.rep;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pizza.products.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
