package com.nagappans.rest;

import com.nagappans.model.Product;
import org.jboss.resteasy.logging.Logger;

import javax.ws.rs.*;
import javax.xml.ws.RequestWrapper;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Path("products")
public class ProductService {
    private static Map<Integer, Product> inventory = new HashMap<>();
    static {
        inventory.put(1, new Product(1, "Inspiron Laptop", 5, "Dell"));
        inventory.put(2, new Product(2, "Power X", 3, "LG"));
        inventory.put(3, new Product(3, "Thumbdrive", 3, "Kingston"));
    }
    private static int maxProductId = 4;

    Logger logger = Logger.getLogger(ProductService.class);

    @POST
    @Path("/")
    @Consumes("application/json")
    @Produces("application/text")
    public String createProduct(Product productDetails) {
        Product newproduct = new Product(maxProductId++, productDetails);
        inventory.put(newproduct.getId(), newproduct);
        return "product created successfully";
    }


    @GET
    @Path("/")
    @Produces("application/json")
    public Collection<Product> getProducts() {
        return inventory.values();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Product getProduct(@PathParam("id") Integer id) {
        return inventory.get(id);
    }

    @DELETE
    @Path("/{id}")
    @Produces("application/text")
    public String deleteProduct(@PathParam("id") Integer id) {
        inventory.remove(id);
        return "product deleted successfully";
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/text")
    public String updateProduct(@PathParam("id") Integer id, Product productDetails) {
        inventory.remove(id);
        Product updatedproduct = new Product(maxProductId++, productDetails);
        inventory.put(updatedproduct.getId(), updatedproduct);
        return "product updated successfully and new id -" + updatedproduct.getId();
    }


}
