package com.example.androidtestapplication;

public class Exampleclass {
    String Id;
    String Name;
    String  Store;
    String Price;
    String Color;
    String Details;
    String Image;

    public  Exampleclass(){

    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getStore() {
        return Store;
    }

    public String getPrice() {
        return Price;
    }

    public String getColor() {
        return Color;
    }

    public String getDetails() {
        return Details;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setStore(String store) {
        Store = store;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public void setColor(String color) {
        Color = color;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getImage() {
        return Image;
    }

    public Exampleclass(String Id, String Name, String Store, String Price, String Color, String Details, String Image) {
        this.Id = Id;
        this.Name = Name;
        this.Store = Store;
        this.Price = Price;
        this.Color = Color;
        this.Details = Details;
        this.Image = Image;
    }
}
