package com.web.madel;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Books {

    private Long id;
    private String name;
    private Shipping shipping;

    public Books (Long id, String name, Shipping shipping) {
        this.id = id;
        this.name = name;
        this.shipping = shipping;
    }

    public Books () {
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

    @Column(name = "name")
    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "shipping_id")
    public Shipping getShipping () {
        return shipping;
    }

    public void setShipping (Shipping shipping) {
        this.shipping = shipping;
    }
}
