package org.lessons.wdpt6.pizzeria.la_mia_pizzeria.controller;

import org.lessons.wdpt6.pizzeria.la_mia_pizzeria.model.Sconto;
import org.lessons.wdpt6.pizzeria.la_mia_pizzeria.repository.OfferteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/offerte")
public class OfferteController {

    @Autowired
    private OfferteRepository offerteRepository;

    // Salvataggio nuova offerta
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("sconto") Sconto formSconto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "offerte/create";
        }

        offerteRepository.save(formSconto);

        return "redirect:/pizzas/" + formSconto.getPizza().getId();
    }

    // Modifica offerta - form
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Sconto sconto = offerteRepository.findById(id).get();

        model.addAttribute("sconto", sconto);
        return "offerte/edit";
    }

    // Salvataggio offerta modificata
    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") Integer id, @Valid @ModelAttribute("sconto") Sconto formSconto,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "offerte/edit";
        }

        offerteRepository.save(formSconto);

        return "redirect:/pizzas/" + formSconto.getPizza().getId();
    }
}
