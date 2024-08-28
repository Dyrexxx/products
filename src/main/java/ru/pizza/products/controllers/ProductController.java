package ru.pizza.products.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.pizza.products.dto.response.Basket;
import ru.pizza.products.entities.Product;
import ru.pizza.products.services.ProductService;
import ru.pizza.products.utils.ImageUtil;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/products")
public class ProductController {
    private final ProductService productService;


    @PostMapping(consumes = "application/json")
    public void newProduct(@RequestBody Product product) {
        String urlImage = ImageUtil.createFile(product.getImageProduct());
        product.setUrlImage(urlImage);
        productService.save(product);
    }

    @GetMapping("/view")
    public ResponseEntity<List<Product>> index() {
        return new ResponseEntity<>(productService.index(), HttpStatus.OK);
    }
}