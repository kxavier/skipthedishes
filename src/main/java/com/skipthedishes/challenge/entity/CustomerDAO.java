package com.skipthedishes.challenge.entity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Kleber
 */
@Stateless
public class CustomerDAO {

    @PersistenceContext
    EntityManager em;
    
    public boolean authenticate(String email, String password) {
        Query query = em.createQuery("SELECT c FROM Customer c WHERE c.email = :email and c.password = :password");
        query.setParameter("email", email);
        query.setParameter("password",password);
        try {
            Customer c = (Customer) query.getSingleResult();
        } catch(NoResultException ex) {
            return false;
        }
        return true;
    }
    
}
