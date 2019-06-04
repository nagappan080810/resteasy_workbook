package com.nagappans.model;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Product {
    @XmlAttribute
    private Integer id;
    private String name;
    private Integer availableQty;
    private String brand;
    private String desc;
    @XmlTransient
    private Date modifiedDate;

    public Product() {
        this.modifiedDate = new Date();
    }

    public Product(Integer id, String name, Integer availableQty, String brand) {
        this.id = id;
        this.name = name;
        this.availableQty = availableQty;
        this.brand = brand;
        this.modifiedDate = new Date();
    }

    public Product(Integer id, Product product) {
        this.id = id;
        this.name = product.getName();
        this.availableQty = product.getAvailableQty();
        this.brand = product.getBrand();
        this.modifiedDate = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAvailableQty() {
        return availableQty;
    }

    public void setAvailableQty(Integer availableQty) {
        this.availableQty = availableQty;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
