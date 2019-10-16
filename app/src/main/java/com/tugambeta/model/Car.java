package com.tugambeta.model;

public class Car implements java.io.Serializable {

    private int id;
    private String make;
    private String model;
    private Integer year;

    public Car() {
    }

    public Car(String make, String model, Integer year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }



    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

}
