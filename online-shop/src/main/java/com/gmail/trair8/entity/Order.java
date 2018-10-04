package com.gmail.trair8.entity;

import com.gmail.trair8.controller.ControllerA;
import org.reflections.Reflections;

import java.util.Objects;
import java.util.Set;

public class Order implements Entity{
    private int id;
    private int userId;
    private int productId;
    private boolean isActual;
    private String address;
    private String payment;
    private long time;

    public Order() {
    }

    public Order(int userId, int productId, boolean isActual, String address, String payment, long time) {
        this.userId = userId;
        this.productId = productId;
        this.isActual = isActual;
        this.address = address;
        this.payment = payment;
        this.time = time;
    }

    public Order(int id, int userId, int productId, boolean isActual, String address, String payment, long time) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.isActual = isActual;
        this.address = address;
        this.payment = payment;
        this.time = time;
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

    public boolean isActual() {
        return isActual;
    }

    public void setActual(boolean actual) {
        isActual = actual;
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                userId == order.userId &&
                productId == order.productId &&
                isActual == order.isActual &&
                time == order.time &&
                Objects.equals(address, order.address) &&
                Objects.equals(payment, order.payment);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, productId, isActual, address, payment, time);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", isActual=" + isActual +
                ", address='" + address + '\'' +
                ", payment='" + payment + '\'' +
                ", time=" + time +
                '}';
    }

    public static void main(String[] args) {
        Reflections reflections = new Reflections("com.gmail.trair8.controller");
        Set<Class<? extends Object>> allClasses = reflections.getTypesAnnotatedWith(ControllerA.class);
        System.out.println(allClasses);
    }
}
