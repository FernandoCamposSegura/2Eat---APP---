package com.fcampos.toeatapp.domain;

public class Establishment {
    private long id;
    private String name;
    private String description;
    private String adress;
    private long longitude;
    private long latitude;

    public Establishment(String name, String description, String adress, long longitude, long latitude) {
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

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }
}
