package com.skipthedishes.challenge.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Kleber
 */
@Stateless
public class OrderDAO {

    @PersistenceContext
    EntityManager em;
    
    private Map<Integer,Order> mockDB = new HashMap<Integer,Order>();
    
    @PostConstruct()
    public void init() {
        mockDB.put(1, new Order(1,new Date(),new Customer(), "deliveryAddress1", "contact1", 1, 0.0, "Ok", new Date()));
        mockDB.put(2, new Order(1,new Date(),new Customer(), "deliveryAddress2", "contact2", 1, 0.0, "Ok", new Date()));
        mockDB.put(3, new Order(1,new Date(),new Customer(), "deliveryAddress2", "contact3", 1, 0.0, "Ok", new Date()));
    }

    
    public Order save(Order order) {
        order = em.merge(order);
        return order;
    }
    
    public Optional<Order> getById(Integer id) {
        Order order = em.find(Order.class, id);
        return Optional.ofNullable(order);
    }
}
