package org.lessons.wdpt6.pizzeria.la_mia_pizzeria.controller;

import org.lessons.wdpt6.pizzeria.la_mia_pizzeria.model.Pizza;
import org.lessons.wdpt6.pizzeria.la_mia_pizzeria.model.Offerta;
import org.lessons.wdpt6.pizzeria.la_mia_pizzeria.repository.PizzaRepository;
import org.lessons.wdpt6.pizzeria.la_mia_pizzeria.repository.OffertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/offerte")
public class OffertaController {

    @Autowired
    private OffertaRepository offertaRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    // CREATE
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("offerta") Offerta offerta,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            Pizza pizza = offerta.getPizza();
            model.addAttribute("pizza", pizza);
            return "offerte/create";
        }

        Integer pizzaId = offerta.getPizza().getId();

        Pizza pizza = pizzaRepository.findById(pizzaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza non trovata"));

        offerta.setPizza(pizza);
        offertaRepository.save(offerta);

        return "redirect:/pizzas/" + pizzaId;
    }

    // EDIT FORM
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Offerta offerta = offertaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Offerta non trovata"));

        model.addAttribute("offerta", offerta);
        model.addAttribute("pizza", offerta.getPizza());
        return "offerte/edit";
    }

    // UPDATE
    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id,
            @Valid @ModelAttribute("offerta") Offerta offerta,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("pizza", offerta.getPizza());
            return "offerte/edit";
        }

        offertaRepository.save(offerta);
        return "redirect:/pizzas/" + offerta.getPizza().getId();
    }

    // DELETE
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Offerta offerta = offertaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Offerta non trovata"));
        Pizza pizza = offerta.getPizza(); // salva prima
        offertaRepository.delete(offerta);
        return "redirect:/pizzas/" + pizza.getId();
    }
}
