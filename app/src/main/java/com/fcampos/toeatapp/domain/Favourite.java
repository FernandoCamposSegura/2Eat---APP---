package com.fcampos.toeatapp.domain;

public class Favourite {
    private long id;
    private long user_id;
    private long establishment_id;

    public Favourite() {
    }

    public Favourite(long user_id, long establishment_id) {
        this.user_id = user_id;
        this.establishment_id = establishment_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getEstablishment_id() {
        return establishment_id;
    }

    public void setEstablishment_id(long establishment_id) {
        this.establishment_id = establishment_id;
    }
}
