package com.gmail.trair8.entity;

import java.util.Objects;

public class Review extends Entity{

    private int userId;
    private int productId;
    private String text;

    public Review() {
    }

    public Review(int id, int userId, int productId, String text) {
        super(id);
        this.userId = userId;
        this.productId = productId;
        this.text = text;
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
        if (!super.equals(o)) return false;
        Review review = (Review) o;
        return userId == review.userId &&
                productId == review.productId &&
                Objects.equals(text, review.text);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), userId, productId, text);
    }

    @Override
    public String toString() {
        return "Review{" +
                "userId=" + userId +
                ", productId=" + productId +
                ", text='" + text + '\'' +
                ", id=" + id +
                '}';
    }
}
