package com.younnescode.customer;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Customer {
    @Id
    @SequenceGenerator(
            name = "customer_id_sequence",
            sequenceName = "customer_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_id_sequence"
    )
    private Integer id;

    @Column(
            nullable = false
    )
    private String firsname;

    @Column(
            nullable = false
    )
    private String lastname;

    @Column(
            nullable = false,
            unique = true
    )
    private String email;

    public Customer() {
    }

    public Customer(String firsname, String lastname, String email) {
        this.firsname = firsname;
        this.lastname = lastname;
        this.email = email;
    }

    public Customer(Integer id, String firsname, String lastname, String email) {
        this.id = id;
        this.firsname = firsname;
        this.lastname = lastname;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public String getFirsname() {
        return firsname;
    }

    public void setFirsname(String firsname) {
        this.firsname = firsname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(firsname, customer.firsname) && Objects.equals(lastname, customer.lastname) && Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firsname, lastname, email);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firsname='" + firsname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
