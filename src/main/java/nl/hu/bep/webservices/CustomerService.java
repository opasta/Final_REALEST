package nl.hu.bep.webservices;

import nl.hu.bep.model.Customer;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

@Path("/customer")
public class CustomerService {


    @GET
    @Produces("application/json")
    public String getCustomer() {
        System.out.println("testertttfirst");
        return Json.createObjectBuilder().add("error", "dit ging eem nie goej").build().toString();
    }

    @GET
    @PermitAll
    @Path("{customerid}")
    @Produces("application/json")

    public Response getCustomer(@PathParam("customerid") int id) {
        System.out.println("testertttfirst");
        Customer customer = Customer.getCustomerById(id);
        if (customer == null) {
            Map<String, String> messages = new HashMap<>();
            System.out.println("testerttt");
            messages.put("error", "Customer does not exist");
            messages.put("requestedId", Integer.toString(id));
            return Response.status(409).entity(messages).build();
        }

        return Response.ok(customer).build();
    }

    @PUT
    @RolesAllowed("user")
    @Path("{customerid}")
    @Produces("application/json")
    public Response updateCustomer(@PathParam("customerid") int id, @FormParam("name") String name) {
        Customer replaced = Customer.updateCustomer(new Customer(id, name));
        if (replaced == null)
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(new AbstractMap.SimpleEntry<>("error", "kon klant " + id + " niet updaten")).build();
        return Response.ok(Customer.getCustomer(id)).build();
    }


    @PATCH
    @RolesAllowed("user")
    @Path("{customerid}")
    @Produces("application/json")
    public Response updateCustomerName(@PathParam("customerid") int id, @FormParam("name") String name) {
        Customer replaced = Customer.updateCustomerName(id, name);
        if (replaced == null)
            return Response.status(Response.Status.EXPECTATION_FAILED).entity(new AbstractMap.SimpleEntry<>("error", "kon klant " + id + " niet updaten")).build();
        return Response.ok(Customer.getCustomer(id)).build();
    }



    @DELETE
    @RolesAllowed("admin")
    @Path("{customerid}")
    @Produces("application/json")
    public Response deleteCustomerName(@PathParam("customerid") int id){
        if(Customer.removeCustomer(id))
            return Response.ok().build();
        return Response.status(Response.Status.NOT_FOUND).build();

    }

}
