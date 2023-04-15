package com.fcampos.toeatapp.domain;

import java.time.LocalDate;

public class Comment {
    private long id;
    private int rating;
    private String message;
    private String datePost;
    private User user;
    private Establishment establishment;

    public Comment(int rating, String message, String datePost, User user, Establishment establishment) {
        this.rating = rating;
        this.message = message;
        this.datePost = datePost;
        this.user = user;
        this.establishment = establishment;
    }

    public Comment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDatePost() {
        return datePost;
    }

    public void setDatePost(String datePost) {
        this.datePost = datePost;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
    }
}
