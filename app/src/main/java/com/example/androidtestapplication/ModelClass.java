package com.example.androidtestapplication;

public class ModelClass {
    public String image, IdKey, color, modelname, comapnyname, price, Details;


    public ModelClass(){


    }
    public String getIdKey() {
        return IdKey;
    }

    public void setIdKey(String idKey) {
        IdKey = idKey;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        this.Details = details;
    }

    public ModelClass(String IdKey, String image, String color, String modelname, String comapnyname, String price, String Details) {
        this.image = image;
        this.IdKey = IdKey;
        this.color = color;
        this.modelname = modelname;
        this.comapnyname = comapnyname;
        this.price = price;
        this.Details = Details;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getModelname() {
        return modelname;
    }

    public void setModelname(String modelname) {
        this.modelname = modelname;
    }

    public String getComapnyname() {
        return comapnyname;
    }

    public void setComapnyname(String comapnyname) {
        this.comapnyname = comapnyname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
