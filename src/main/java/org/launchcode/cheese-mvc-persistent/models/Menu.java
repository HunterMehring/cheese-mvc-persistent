package org.launchcode.cheesemvc.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Menu {


    @NotNull
    @Size(min = 3, max=15)
    private String name;

    @Id//says this should be a primary key in the database
    @GeneratedValue//says that a value should be generated for us.
    private int id;

    //this list of cheeses will be populated based on the relationships we set up with our controllers
    //there will be many cheeses associated with many menus
    //This is going to be populated with a list of cheeses that are present for each Menu
    //Hibernate figures out the relationship between cheeses and
    // menus
    @ManyToMany
    private List<Cheese> cheeses = new ArrayList<>();


    public Menu() {}

    public Menu(String name) {
        this.name = name;
    }

    //simply adds a given item to the list
    public void addItem(Cheese item) {
    cheeses.add(item);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Cheese> getCheeses() {
        return cheeses;
    }
    }

