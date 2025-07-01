package org.lessons.wdpt6.pizzeria.la_mia_pizzeria.controller;

import org.lessons.wdpt6.pizzeria.la_mia_pizzeria.model.Pizza;
import org.lessons.wdpt6.pizzeria.la_mia_pizzeria.model.Sconto;
import org.lessons.wdpt6.pizzeria.la_mia_pizzeria.repository.OffertaRepository;
import org.lessons.wdpt6.pizzeria.la_mia_pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/offerte")
public class OffertaController {

    @Autowired
    private OffertaRepository offertaRepository;

    @Autowired
    private PizzaRepository pizzaRepository;

    // CREAZIONE nuova offerta
    @GetMapping("/create/{pizzaId}")
    public String create(@PathVariable("pizzaId") Integer pizzaId, Model model) {
        Pizza pizza = pizzaRepository.findById(pizzaId).orElse(null);
        if (pizza == null) {
            return "redirect:/pizzas";
        }

        Sconto sconto = new Sconto();
        sconto.setPizza(pizza);

        model.addAttribute("sconto", sconto);
        model.addAttribute("pizza", pizza);
        return "offerte/create";
    }

    // SALVATAGGIO nuova offerta
    @PostMapping("/save")
    public String store(@Valid @ModelAttribute("sconto") Sconto formSconto,
            BindingResult bindingResult,
            Model model) {

        Integer pizzaId = formSconto.getPizza().getId();
        Pizza pizza = pizzaRepository.findById(pizzaId).orElse(null);

        if (pizza == null) {
            bindingResult.rejectValue("pizza", "notfound", "Pizza non trovata");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("pizza", pizza);
            return "offerte/create";
        }

        formSconto.setPizza(pizza);
        offertaRepository.save(formSconto);
        return "redirect:/pizzas/" + pizzaId;
    }

    // FORM MODIFICA offerta
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Sconto sconto = offertaRepository.findById(id).orElse(null);
        if (sconto == null) {
            return "redirect:/pizzas";
        }

        model.addAttribute("sconto", sconto);
        model.addAttribute("pizza", sconto.getPizza());
        return "offerte/edit";
    }

    // AGGIORNA offerta
    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") Integer id,
            @Valid @ModelAttribute("sconto") Sconto formSconto,
            BindingResult bindingResult,
            Model model) {

        Integer pizzaId = formSconto.getPizza().getId();
        Pizza pizza = pizzaRepository.findById(pizzaId).orElse(null);

        if (pizza == null) {
            bindingResult.rejectValue("pizza", "notfound", "Pizza non trovata");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("pizza", pizza);
            return "offerte/edit";
        }

        formSconto.setPizza(pizza);
        offertaRepository.save(formSconto);
        return "redirect:/pizzas/" + pizzaId;
    }

    // CANCELLA offerta
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        Sconto sconto = offertaRepository.findById(id).orElse(null);

        if (sconto != null) {
            Integer pizzaId = sconto.getPizza().getId();
            offertaRepository.deleteById(id);
            return "redirect:/pizzas/" + pizzaId;
        }

        return "redirect:/pizzas";
    }
}
