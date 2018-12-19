package org.launchcode.cheesemvc.models;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity//makes sure that this class will be mapped to a relational database table(persistent)
public class Category {

    @Id//These are JPA annotations! they do the heavy lifting with setting up the Id.
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min = 3, max = 15)
    private String name;

    @OneToMany//each category can have many cheeses
    @JoinColumn(name = "category_id")//used to determine which cheese belongs to a given category.
    private List<Cheese> cheeses = new ArrayList<>();

    public Category() {}

    public Category(String name) { this.name = name;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  List<Cheese> getCheeses() {return cheeses;}
}
