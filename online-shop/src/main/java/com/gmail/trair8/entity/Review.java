package com.gmail.trair8.entity;

import java.util.Objects;

public class Review implements Entity{
    private int id;
    private int userId;
    private int productId;
    private String text;

    public Review() {
    }

    public Review(int userId, int productId, String text) {
        this.userId = userId;
        this.productId = productId;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id == review.id &&
                userId == review.userId &&
                productId == review.productId &&
                Objects.equals(text, review.text);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, productId, text);
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", text='" + text + '\'' +
                '}';
    }
}
