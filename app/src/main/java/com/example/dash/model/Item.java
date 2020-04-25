package com.example.dash.model;

public class Item {
    private String image;

    private String name;

    private String description;


   public Item(){};
   public Item(String image, String name, String description){
       this.image = image;
       this.name = name;
       this.description = description;
   }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
}
