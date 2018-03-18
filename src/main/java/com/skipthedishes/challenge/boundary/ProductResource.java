package com.skipthedishes.challenge.boundary;

import com.skipthedishes.challenge.ProductNotFoundException;
import com.skipthedishes.challenge.entity.Product;
import com.skipthedishes.challenge.entity.ProductDAO;
import java.util.Collection;
import java.util.Optional;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Kleber
 */
@Path("Product")
public class ProductResource {
    
    @EJB
    ProductDAO productDao;

    @GET
    @Produces("application/json")
    public Product[] getAll() {
        Collection<Product> products = productDao.getAll();
        return products.toArray(new Product[0]);
    }

    @GET
    @Path("search/{searchText}")
    @Produces("application/json")
    public Product[] search(@PathParam("searchText") String searchText) {
        Collection<Product> products = productDao.getByName(searchText);
        return products.toArray(new Product[0]);
    }

    @GET
    @Path("{productId}")
    @Produces("application/json")
    public Product getProductById(@PathParam("productId") Integer productId) {
        Optional<Product> product = productDao.getById(productId);
        return product.orElseThrow(() -> new ProductNotFoundException("No product with id " + productId + " was found"));
    }
}
