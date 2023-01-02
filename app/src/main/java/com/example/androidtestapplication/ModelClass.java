package com.example.androidtestapplication;

public class ModelClass {
    public String image;
    public String modelname, comapnyname, price;


    public ModelClass(){

    }
    public ModelClass(String image, String modelname, String comapnyname, String price) {
        this.image = image;

        this.modelname = modelname;
        this.comapnyname = comapnyname;
        this.price = price;

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
