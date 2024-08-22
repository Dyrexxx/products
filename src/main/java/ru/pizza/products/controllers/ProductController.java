package ru.pizza.products.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.pizza.products.entities.Basket;
import ru.pizza.products.entities.Product;
import ru.pizza.products.entities.ProductAvailability;
import ru.pizza.products.services.ProductService;
import ru.pizza.products.utils.ImageUtil;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    private final ProductService productService;
    private final RestTemplate restTemplate;

    @Autowired
    public ProductController(ProductService productService, RestTemplate restTemplate) {
        this.productService = productService;
        this.restTemplate = restTemplate;
    }

    @PostMapping(consumes = "application/json")
    public void newProduct(@RequestBody Product product) {
        System.out.println(product);
        String urlImage = ImageUtil.createFile(product.getImageProduct());
        product.setUrlImage(urlImage);
        productService.save(product);
    }

    @GetMapping("/view")
    public ResponseEntity<List<ProductAvailability>> index() {
        return new ResponseEntity<>(productService.createViewProductList(), HttpStatus.OK);
    }

    @PostMapping("/order")
    public ResponseEntity basketPay(@RequestBody Basket basket) {
        productService.editOrder(basket);
        restTemplate.exchange("http://localhost:8085/dodo/newOrder", HttpMethod.POST, new HttpEntity<>(basket), Basket.class);
        return ResponseEntity.ok(null);
    }

}