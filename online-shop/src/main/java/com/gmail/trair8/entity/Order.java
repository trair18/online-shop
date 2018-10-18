package com.gmail.trair8.entity;

import java.util.Date;
import java.util.Objects;

public class Order extends Entity{

    private int userId;
    private int productId;
    private boolean actual;
    private String address;
    private String payment;
    private Date time;

    public Order() {
    }

    public Order(int id, int userId, int productId, boolean actual, String address, String payment, Date time) {
        super(id);
        this.userId = userId;
        this.productId = productId;
        this.actual = actual;
        this.address = address;
        this.payment = payment;
        this.time = time;
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

    public boolean isActual() {
        return actual;
    }

    public void setActual(boolean actual) {
        this.actual = actual;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Order order = (Order) o;
        return userId == order.userId &&
                productId == order.productId &&
                actual == order.actual &&
                Objects.equals(address, order.address) &&
                Objects.equals(payment, order.payment) &&
                Objects.equals(time, order.time);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), userId, productId, actual, address, payment, time);
    }

    @Override
    public String toString() {
        return "Order{" +
                "userId=" + userId +
                ", productId=" + productId +
                ", actual=" + actual +
                ", address='" + address + '\'' +
                ", payment='" + payment + '\'' +
                ", time=" + time +
                ", id=" + id +
                '}';
    }
}
