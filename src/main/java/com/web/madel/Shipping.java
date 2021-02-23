package com.web.madel;

import javax.persistence.*;

@Entity
@Table(name = "shipping")
public class Shipping {

    private Long id;
    private String city;

    public Shipping () {
    }

    public Shipping (Long id, String city) {
        this.id = id;
        this.city = city;
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    @Column(name = "city")
    public String getCity () {
        return city;
    }

    public void setCity (String city) {
        this.city = city;
    }
}
