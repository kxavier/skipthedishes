package com.skipthedishes.challenge.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kleber
 */
@XmlRootElement
@Entity @Table(name = "ORDER_TB")
public class Order implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date date;
    @ManyToOne @JoinColumn(name = "customerId")
    private Customer customer;
    private String deliveryAddress;
    private String contact;
    private Integer storeId;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) 
    private Collection<OrderItem> orderItems = new ArrayList<>();
    private double total;
    private String status;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date lastUpdate;
    
    public Order() {}

    public Order(Integer id, Date date, Customer customer, String deliveryAddress, String contact, Integer storeId, double total, String status, Date lastUpdate) {
        this.id = id;
        this.date = date;
        this.customer = customer;
        this.deliveryAddress = deliveryAddress;
        this.contact = contact;
        this.storeId = storeId;
        this.total = total;
        this.status = status;
        this.lastUpdate = lastUpdate;
    }
    
    public Order(JsonObject json) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        this.id = json.getInt("id", 0);
        
        if(json.containsKey("date")) {
            this.date = formatter.parse(json.getString("date"));
        } else {
            this.date = new Date();
        }
        
        this.customer = new Customer();
        
        this.deliveryAddress = json.getString("deliveryAddress", "");
        this.contact = json.getString("contact", "");
        this.storeId = json.getInt("storeId", 0);
        this.total = json.containsKey("total") ? json.getJsonNumber("total").doubleValue() : 0.0;
        this.status = json.getString("status", "");
        
        JsonArray items = json.getJsonArray("orderItems");
        items.forEach((item) -> this.orderItems.add(new OrderItem((JsonObject)item)));
        
        if(json.containsKey("date")) {
            this.lastUpdate = formatter.parse(json.getString("lastUpdate"));
        } else {
            this.lastUpdate = new Date();
        }
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Collection<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Collection<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final Order other = (Order) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", date=" + date + ", customer=" + customer + ", deliveryAddress=" + deliveryAddress + ", contact=" + contact + ", storeId=" + storeId + ", orderItems=" + orderItems + ", total=" + total + ", status=" + status + ", lastUpdate=" + lastUpdate + '}';
    }
    
    public JsonObject toJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder()
                .add("id", this.getId())
                .add("date", this.getDate().toString())
                .add("customerId", this.getCustomer().getId())
                .add("deliveryAddress", this.getDeliveryAddress())
                .add("contact", this.getContact())
                .add("storeId", this.getStoreId())
                .add("total", this.getTotal())
                .add("status", this.getStatus())
                .add("lastUpdate", this.getLastUpdate().toString());
        JsonArrayBuilder itemsBuilder = Json.createArrayBuilder();
        this.orderItems.forEach(item -> itemsBuilder.add(item.toJson()));
        builder.add("orderItems",itemsBuilder);
        return builder.build();
    }
    
}
