package com.skipthedishes.challenge.entity;

import java.math.BigDecimal;
import java.util.Objects;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kleber
 */
@XmlRootElement
@Entity
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer storeId;
    private String name;
    private String description;
    private double price;

    public Product() {}

    public Product(Integer id, Integer storeId, String name, String description, double price) {
        this.id = id;
        this.storeId = storeId;
        this.name = name;
        this.description = description;
        this.price = price;
    }
    
    public Product(JsonObject json) {
        this.id = json.getInt("id",0);
        this.storeId = json.getInt("storeId",0);
        this.name = json.getString("name","");
        this.description = json.getString("description","");
        this.price = json.containsKey("price") ? json.getJsonNumber("total").doubleValue() : 0.0;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", storeId=" + storeId + ", name=" + name + ", description=" + description + ", price=" + price + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.storeId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.storeId, other.storeId)) {
            return false;
        }
        return true;
    }
    
    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("id", this.getId())
                .add("storeId", this.getStoreId())
                .add("name",this.getName())
                .add("description", this.getDescription())
                .add("price",this.getPrice())
                .build();
    }
}
