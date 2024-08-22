package ru.pizza.products.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pizza.products.entities.Expenditure;
import ru.pizza.products.entities.Product;
import ru.pizza.products.rep.ExpenditureRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ExpenditureService {
    private final ExpenditureRepository expenditureRepository;

    @Autowired
    public ExpenditureService(ExpenditureRepository expenditureRepository) {
        this.expenditureRepository = expenditureRepository;
    }

    @Transactional
    public void save(List<Expenditure> expenditureList, Product product) {
        for (Expenditure i : expenditureList) {
            i.setProduct(product);
        }
        expenditureRepository.saveAll(expenditureList);
    }

}