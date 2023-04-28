package com.fcampos.toeatapp.domain;

public class Establishment {
    private long id;
    private String name;
    private String description;
    private String adress;
    private double longitude;
    private double latitude;

    public Establishment(String name, String description, String adress, double longitude, double latitude) {
        this.name = name;
        this.description = description;
        this.adress = adress;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Establishment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
