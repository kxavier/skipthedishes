package com.skipthedishes.challenge.entity;

import java.util.Collection;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Kleber
 */
@Stateless
public class ProductDAO {

    @PersistenceContext
    private EntityManager em;

    public Collection<Product> getAll() {
        Query query = em.createQuery("SELECT p FROM Product p");
        return query.getResultList();
    }

    public Optional<Product> getById(Integer id) {
        Product product = em.find(Product.class, id);
        return Optional.ofNullable(product);
    }

    public Collection<Product> getByName(String name) {
        Query query = em.createQuery("SELECT p FROM Product p WHERE p.name LIKE :name");
        query.setParameter("name", "%"+name+"%");
        return query.getResultList();
    }
    
    
//     private final Map<Integer, Product> mockDB = new HashMap<>();
//    
//    @PostConstruct
//    public void init() {
//        mockDB.put(1, new Product(1,0,"globalcode", "productDescription1", 0.0));
//        mockDB.put(2, new Product(2,0,"globaleducation", "productDescription2", 0.0));
//        mockDB.put(3, new Product(3,0,"other", "productDescription3", 0.0));
//    }
//    
//    public Collection<Product> getAll() {
//        return mockDB.values();
//    }
//    
//    public Optional<Product> getById(Integer id) {
//        return Optional.ofNullable(mockDB.get(id));
//    }
//    
//    public Collection<Product> getByName(String name) {
//        Collection<Product> products = mockDB.values();
//        return products.stream().filter(p -> p.getName().contains(name)).collect(Collectors.toList());
//    }
}
