package org.lessons.wdpt6.pizzeria.la_mia_pizzeria.repository;

import org.lessons.wdpt6.pizzeria.la_mia_pizzeria.model.Sconto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferteRepository extends JpaRepository<Sconto, Integer> {

}
