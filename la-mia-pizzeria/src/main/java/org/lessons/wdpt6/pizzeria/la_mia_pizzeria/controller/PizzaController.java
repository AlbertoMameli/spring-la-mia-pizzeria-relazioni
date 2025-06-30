package org.lessons.wdpt6.pizzeria.la_mia_pizzeria.controller;

import org.lessons.wdpt6.pizzeria.la_mia_pizzeria.model.Pizza;
import org.lessons.wdpt6.pizzeria.la_mia_pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {
    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("pizzas", pizzaRepository.findAll());// prendo la lista delle pizze chiamando la
                                                                // pizzarepository
        return "pizzas/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable(name = "id") Integer id, Model model) {
        Pizza pizza = pizzaRepository.findById(id).get();
        model.addAttribute("pizza", pizza);
        return "pizzas/show";
    }

    @GetMapping("/create") // sto chiamando la rotta per andare sul create
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "pizzas/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {

        // validazioni

        if (bindingResult.hasErrors()) {

            // salvare la pizza inserita nel form
            pizzaRepository.save(formPizza);
            return "/pizzas/create";
        }

        // ritorna alla pagina pizzas
        pizzaRepository.save(formPizza);
        return "redirect:/pizzas";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("pizza", pizzaRepository.findById(id).get());
        return "pizzas/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable("id") Integer id, @Valid @ModelAttribute("book") Pizza formPizza,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "/Pizzas/edit";
        }

        // Salva il libro nel DB
        pizzaRepository.save(formPizza);

        return "redirect:/pizzas";

    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {

        // * cancella la risorsa dal DB
        pizzaRepository.deleteById(id);

        return "redirect:/pizzas";
    }
}
