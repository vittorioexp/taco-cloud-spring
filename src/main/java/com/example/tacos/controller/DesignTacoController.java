package com.example.tacos.controller;

import com.example.tacos.model.Design;
import com.example.tacos.model.Ingredient;
import com.example.tacos.model.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping
    public String showDesignForm(Model model) {

        // TODO: remove hard coded list of ingredients
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient(1, "Flour Tortilla", Ingredient.Type.WRAP),
                new Ingredient(2, "Corn Tortilla", Ingredient.Type.WRAP),
                new Ingredient(3, "Ground Beef", Ingredient.Type.PROTEIN),
                new Ingredient(4, "Carnitas", Ingredient.Type.PROTEIN),
                new Ingredient(5, "Diced Tomatoes", Ingredient.Type.VEGGIES),
                new Ingredient(6, "Lettuce", Ingredient.Type.VEGGIES),
                new Ingredient(7, "Cheddar", Ingredient.Type.CHEESE),
                new Ingredient(8, "Monterrey Jack", Ingredient.Type.CHEESE),
                new Ingredient(9, "Salsa", Ingredient.Type.SAUCE),
                new Ingredient(10, "Sour Cream", Ingredient.Type.SAUCE)
        );

        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase());
        }
        model.addAttribute("design", new Taco());
        return "design";

    }

    @PostMapping
    public String processDesign(Design design) {
        // TODO: Save the taco design
        log.info("Processing design: " + design);
        return "redirect:/orders/current";
    }

}
