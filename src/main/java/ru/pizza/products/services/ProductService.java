package ru.pizza.products.services;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pizza.products.dto.response.Basket;
import ru.pizza.products.entities.*;
import ru.pizza.products.rep.ProductRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;
    private final ExpenditureService expenditureService;


    public List<Product> index() {
        return productRepository.findAll();
    }

    @Transactional
    public void save(Product product) {
        productRepository.save(product);
        expenditureService.save(product.getExpenditureList(), product);
    }
}