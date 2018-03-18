package com.skipthedishes.challenge.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Kleber
 */
@Entity
public class OrderItem implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne @JoinColumn(name = "orderId")
    private Order order;
    @OneToOne @JoinColumn(name = "productId")
    private Product product;
    private double price;
    private int quantity;
    private double total;

    public OrderItem() {}
    
    public OrderItem(JsonObject json) {
        this.id = json.getInt("id", 0);
        //this.order;
        this.product = new Product(json.getJsonObject("product"));
        this.price = json.containsKey("price") ? json.getJsonNumber("price").doubleValue() : 0.0;
        this.quantity = json.getInt("quantity",0);
        this.total = json.containsKey("total") ? json.getJsonNumber("total").doubleValue() : 0.0;
        
        
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "OrderItem{" + "id=" + id + ", order=" + order + ", product=" + product + ", price=" + price + ", quantity=" + quantity + ", total=" + total + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final OrderItem other = (OrderItem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("id", this.getId())
                .add("orderId", this.getOrder().getId())
                .add("productId", this.getProduct().getId())
                .add("product", this.getProduct().toJson())
                .add("price",this.getPrice())
                .add("quantity",this.getQuantity())
                .add("total",this.getTotal())
                .build();
    }
}