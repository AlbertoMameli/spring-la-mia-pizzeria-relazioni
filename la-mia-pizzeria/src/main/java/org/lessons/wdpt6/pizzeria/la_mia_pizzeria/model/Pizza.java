package org.lessons.wdpt6.pizzeria.la_mia_pizzeria.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pizzas")
public class Pizza implements Serializable {

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

    // Relazione 1 a molti con Sconto (un pizza può avere più sconti)
    //cascade = CascadeType.ALL  elimina o salva una pizza, salva/elimina anche gli sconti collegati.
    //orphanRemoval = true rimuove dallo sconti quelli non più collegati alla Pizza
    @OneToMany(mappedBy = "pizza", cascade = CascadeType.ALL, orphanRemoval = true)
private List<Offerta> sconti;

    public Pizza() {
    }

    // Getters e Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getImgUrl() {
        return imageUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imageUrl = imgUrl;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<Offerta> getSconti() {
        return sconti;
    }

    public void setSconti(List<Offerta> sconti) {
        this.sconti = sconti;
    }
}
