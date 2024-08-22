package ru.pizza.products.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.pizza.products.entities.*;
import ru.pizza.products.rep.ProductRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;
    private final ExpenditureService expenditureService;
    private final RestTemplate restTemplate;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductService(ProductRepository productRepository, ExpenditureService expenditureService, RestTemplate restTemplate, JdbcTemplate jdbcTemplate) {
        this.productRepository = productRepository;
        this.expenditureService = expenditureService;
        this.restTemplate = restTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> index() {
        return productRepository.findAll();
    }

    @Transactional
    public void save(Product product) {
        productRepository.save(product);
        expenditureService.save(product.getIngredientList(), product);
    }

    public List<ProductAvailability> createViewProductList() {
        List<ProductAvailability> productAvailability = new ArrayList<>();
        List<Product> productList = index();
        List<Product> warehouseList = getIsWarehouse();
        for (Product product : productList) {
            ProductAvailability emptyProductAvailability = new ProductAvailability(product);
            for (Product warehouse : warehouseList) {
                int countCoincidence = 0;
                for (Expenditure productExpenditure : product.getIngredientList()) {
                    for (Expenditure expenditureWarehouse : warehouse.getIngredientList()) {
                        if (expenditureWarehouse.getTitle().equals(productExpenditure.getTitle())) {
                            if (expenditureWarehouse.getWeight() >= productExpenditure.getWeight()) {
                                countCoincidence++;
                                break;
                            }
                        }
                    }
                }
                boolean isPresent = false;
                if (countCoincidence == product.getIngredientList().size()) {
                    isPresent = true;
                }
                emptyProductAvailability.getBuildingList().add(Building.builder()
                        .id(warehouse.getId())
                        .name(warehouse.getName())
                        .isPresent(isPresent)
                        .build());
            }
            productAvailability.add(emptyProductAvailability);
        }
        return productAvailability;
    }

    private List<Product> getIsWarehouse() {
        return List.of(restTemplate.getForEntity("http://localhost:8085/dodo/buildings", Product[].class).getBody());
    }

    public void editOrder(Basket basket) {
        for (Basket.BasketItem basketItem : basket.getBasketViewItemList()) {
            List<Expenditure> list = jdbcTemplate.query("select e.title, e.weight from product p join expenditure e on p.id = e.product_id where p.title = ?",
                    this::mapRowToExpenditure, basketItem.getProductName());
            basketItem.setIngredients(list);
        }
    }

    private Expenditure mapRowToExpenditure(ResultSet row, int rowInt) throws SQLException {
        return new Expenditure() {{
            setTitle(row.getString("title"));
            setWeight(row.getInt("weight"));
        }};
    }
}