package com.gmail.trair8.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class User extends Entity {

    private String email;
    private String password;
    private String surname;
    private String firstName;
    private BigDecimal account;
    private int loyaltyPoints;
    private boolean blocked;
    private boolean admin;

    public User() {
        this.blocked = false;
    }

    public User(int id, String email, String password, String surname, String firstName, BigDecimal account, int loyaltyPoints, boolean blocked, boolean admin) {
        super(id);
        this.email = email;
        this.password = password;
        this.surname = surname;
        this.firstName = firstName;
        this.account = account;
        this.loyaltyPoints = loyaltyPoints;
        this.blocked = blocked;
        this.admin = admin;
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

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return loyaltyPoints == user.loyaltyPoints &&
                blocked == user.blocked &&
                admin == user.admin &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(account, user.account);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), email, password, surname, firstName, account, loyaltyPoints, blocked, admin);
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", surname='" + surname + '\'' +
                ", firstName='" + firstName + '\'' +
                ", account=" + account +
                ", loyaltyPoints=" + loyaltyPoints +
                ", blocked=" + blocked +
                ", admin=" + admin +
                ", id=" + id +
                '}';
    }
}
