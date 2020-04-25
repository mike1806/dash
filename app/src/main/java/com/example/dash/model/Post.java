package com.example.dash.model;


import com.google.gson.annotations.SerializedName;

public class Post {

    private String _id;

    private String title;

    private String slug;

    @SerializedName("desc")
    private String text;

    private int price;

    private String category;

    private String image;

    public Post(String title, String slug, String text, int price, String category, String image) {
        this.title = title;
        this.slug = slug;
        this.text = text;
        this.price = price;
        this.category = category;
        this.image = image;
    }

    public String get_id() {
        return _id;
    }

    public String getTitle() {
        return title;
    }

    public String getSlug() {
        return slug;
    }

    public String getText() {
        return text;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }
}