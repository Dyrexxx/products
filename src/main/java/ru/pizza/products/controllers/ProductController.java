package ru.pizza.products.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
        System.out.println(product);
        String urlImage = ImageUtil.createFile(product.getImageModel());
        product.setUrlImage(urlImage);
        productService.save(product);
    }

    @GetMapping("/view")
    public ResponseEntity<List<Product>> index() {
        return new ResponseEntity<>(productService.index(), HttpStatus.OK);
    }
}