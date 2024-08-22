package ru.pizza.products.rep;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.pizza.products.entities.Expenditure;

@Repository
public interface ExpenditureRepository extends JpaRepository<Expenditure, Integer> {
}
