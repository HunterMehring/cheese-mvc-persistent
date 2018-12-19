package org.launchcode.cheesemvc.controllers;

import org.launchcode.cheesemvc.models.Category;
import org.launchcode.cheesemvc.models.Cheese;
import org.launchcode.cheesemvc.models.data.CategoryDao;
import org.launchcode.cheesemvc.models.data.CheeseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by LaunchCode
 */

@Controller
@RequestMapping(value = "cheese")
public class CheeseController {

    @Autowired//I should be given an instance of this class by the framework
    private CheeseDao cheeseDao;

    @Autowired
    private CategoryDao categoryDao;


    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "My Cheeses");
        return "cheese/index";
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String displayDeleteForm( Model model) {
        model.addAttribute("cheeses", cheeseDao.findAll());
        model.addAttribute("title", "Delete Cheese");
        return "cheese/delete";
    }

    // Request path: cheese/delete
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String processDeletedCheese(@RequestParam int[] cheeseIds) {

      for (int cheeseId: cheeseIds) {

          cheeseDao.delete(cheeseId);
        }  // need to ignore foreign key check? or at least delete those relationships as well.
        return "redirect:";
    }

    // Request path: cheese/add
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddCheeseForm(Model model) {

        model.addAttribute("title", "Add Cheese");
        model.addAttribute(new Cheese());
        model.addAttribute("categories", categoryDao.findAll());
        return "cheese/add";
    }

    // Request path: cheese/add
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddCheeseForm(@ModelAttribute @Valid Cheese newCheese,
                                       Errors errors,
                                       Model model,
                                       @RequestParam int categoryId) {

        if (errors.hasErrors()) {
            model.addAttribute("categories", categoryDao.findAll());
            model.addAttribute("title", "Add Cheese");
            return "cheese/add";
        }
        /*
         * newCheese = newCheese(); we are calling the default constructor through a post request
         * newCheese.setName(Request.getParameter("name"));
         * newCheese.setDescription(Request.getParameter("description"));
         */
        Category cat = categoryDao.findOne(categoryId);
        newCheese.setCategory(cat);
        cheeseDao.save(newCheese);
        // Redirect to cheese/
        return "redirect:";
    }

    @RequestMapping(value = "category", method = RequestMethod.GET)
    public String displayCategories(Model model, @RequestParam int id){

        Category cat = categoryDao.findOne(id);//finds the category and stores it in cat
        List<Cheese> cheeses = cat.getCheeses();
        model.addAttribute("cheeses", cheeses);
        model.addAttribute("title", "Cheeses in Category: " + cat.getName());
        return "cheese/index";
    }



    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET) //need to get to cheese/edit/0
    public String displayEditForm( Model model, @PathVariable int id) {

        Cheese cheese = cheeseDao.findOne(id);// was Cheesetype.findById()// maybe Category cat = categoryDao.findOne(id)
        model.addAttribute(cheese);
        model.addAttribute("categories", categoryDao.findAll());
        model.addAttribute("title", "Edit Cheese " + cheese.getName());
        return "cheese/edit";
    }


    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditForm(@RequestParam int id,  @ModelAttribute @Valid Cheese newCheese, Errors errors, Model model) { // find the post version of @PathVariable

        Cheese aCheese = cheeseDao.findOne(id);

        if (errors.hasErrors()) {
            model.addAttribute(aCheese);
            model.addAttribute("categories", categoryDao.findAll());
            model.addAttribute("title", "Edit Cheese " + aCheese.getName());
            return "redirect:/cheese/edit/" + id;
        }

        aCheese.setName(newCheese.getName());
        aCheese.setDescription(newCheese.getDescription());
        aCheese.setCategory(newCheese.getCategory());
        aCheese.setRating(newCheese.getRating());
        cheeseDao.save(aCheese);
        return "redirect:";

    }

}