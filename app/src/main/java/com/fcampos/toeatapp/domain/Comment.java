package com.fcampos.toeatapp.domain;

public class Comment {
    private long id;
    private int rating;
    private String message;
    private String datePost;
    private long user_id;
    private long establishment_id;

    public Comment(int rating, String message, String datePost, long userId, long establishmentId) {
        this.rating = rating;
        this.message = message;
        this.datePost = datePost;
        this.user_id = userId;
        this.establishment_id = establishmentId;
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
