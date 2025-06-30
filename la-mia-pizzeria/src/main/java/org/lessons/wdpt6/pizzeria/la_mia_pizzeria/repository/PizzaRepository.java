package org.lessons.wdpt6.pizzeria.la_mia_pizzeria.repository;

import org.lessons.wdpt6.pizzeria.la_mia_pizzeria.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza, Integer>{
    
}
