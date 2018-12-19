package org.launchcode.cheesemvc.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity//stores this in a database. all fields unless specified.
public class Cheese {

    @Id//says this should be a primary key in the database
    @GeneratedValue//says that a value should be generated for us.
    private int id;

    @NotNull
    @Size(min = 3, max=15)
    private String name;

    @NotNull
    @Size(min = 1, message = "Description must not be empty")
    private String description;

    @ManyToOne//many cheeses to each category
    private Category category;

    private int rating;

    //represents the list of Menu objects that a given cheese is contained in
    //ex: menuObject1 and menuObject2 have cheddar in them, but not menuObject 3;
    //
    @ManyToMany(mappedBy = "cheeses")//this is the cheeses field in the menu class
    private List<Menu> menus;

    public Cheese(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Cheese(){ }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
