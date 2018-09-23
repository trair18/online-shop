package com.gmail.trair8.entity;

import java.util.Objects;

public class User implements Entity {
    private int id;
    private String email;
    private String password;
    private String surname;
    private String firstName;
    private String account;
    private int loyaltyPoints;
    private boolean isBlocked;
    private boolean isAdmin;

    public User() {
        this.isBlocked = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                loyaltyPoints == user.loyaltyPoints &&
                isBlocked == user.isBlocked &&
                isAdmin == user.isAdmin &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(account, user.account);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, email, password, surname, firstName, account, loyaltyPoints, isBlocked, isAdmin);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", surname='" + surname + '\'' +
                ", firstName='" + firstName + '\'' +
                ", account='" + account + '\'' +
                ", loyaltyPoints=" + loyaltyPoints +
                ", isBlocked=" + isBlocked +
                ", isAdmin=" + isAdmin +
                '}';
    }
}