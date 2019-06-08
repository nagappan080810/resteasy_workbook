package com.nagappans.model;

import javax.xml.bind.annotation.*;
import java.util.Date;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Product {
    @XmlElement
    private Integer id;
    private String name;
    private Integer qty;
    private String brand;
    private String desc;
    @XmlTransient
    private Date modifiedDate;

    public Product() {
        this.modifiedDate = new Date();
    }

    public Product(Integer id, String name, Integer availableQty, String brand, String desc) {
        this.id = id;
        this.name = name;
        this.qty = availableQty;
        this.brand = brand;
        this.desc = desc;
        this.modifiedDate = new Date();
    }

    public Product(Integer id, Product product) {
        this.id = id;
        this.name = product.getName();
        this.qty = product.getQty();
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

    public int getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
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
