package com.example.tacos.controller;

import com.example.tacos.data.IngredientRepository;
import com.example.tacos.model.Design;
import com.example.tacos.model.Ingredient;
import com.example.tacos.model.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
This simple controller does the following:
- Handle HTTP GET requests where the request path is /design
- Build a list of ingredients
- Hand the request and the ingredient data off to a view template
  to be rendered as HTML and sent to the requesting web browser
 */

/*
@Slf4j is a Lombok-provided annotation that, at runtime,
will automatically generate an SLF4J Logger in the class.
 */
@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }
    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));
        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase());
        }
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors) {
        if (errors.hasErrors()) {
            return "design";
        }
        // TODO: Save the taco design
        log.info("Processing design: " + design);
        return "redirect:/orders/current";
    }

}
