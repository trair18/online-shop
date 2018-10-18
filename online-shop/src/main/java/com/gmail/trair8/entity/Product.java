package com.gmail.trair8.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Product extends Entity {

    private String name;
    private BigDecimal price;
    private double rating;
    private boolean inStock;
    private String img;
    private String category;

    public Product() {
    }

    public Product(int id, String name, BigDecimal price, double rating, boolean inStock, String img, String category) {
        super(id);
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.inStock = inStock;
        this.img = img;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Product product = (Product) o;
        return Double.compare(product.rating, rating) == 0 &&
                inStock == product.inStock &&
                Objects.equals(name, product.name) &&
                Objects.equals(price, product.price) &&
                Objects.equals(img, product.img) &&
                Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), name, price, rating, inStock, img, category);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", inStock=" + inStock +
                ", img='" + img + '\'' +
                ", category='" + category + '\'' +
                ", id=" + id +
                '}';
    }
}
