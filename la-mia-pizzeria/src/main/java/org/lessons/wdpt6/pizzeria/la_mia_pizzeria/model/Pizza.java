package org.lessons.wdpt6.pizzeria.la_mia_pizzeria.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

//creazione tabella
@Entity
@Table(name = "pizzas")
public class Pizza implements Serializable {

    // creazione colonne
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @NotBlank(message = "Name must not be null, blank or empty")
    private String name;
    @Lob
    @NotBlank(message = "Description must not be null, blank or empty")
    private String description;
    @Lob
    @NotBlank(message = "Image URL must not be null, blank or empty")
    private String imageUrl;

    
    @Min(value = 0, message = "The price must be greater than zero")
    private float price;

    public Pizza() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return this.imageUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imageUrl = imgUrl;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}
