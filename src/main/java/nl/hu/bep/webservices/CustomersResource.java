package nl.hu.bep.webservices;

import nl.hu.bep.model.Customer;
import nl.hu.bep.servlets.LoggedInServlet;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.AbstractMap;
import java.util.List;

@Path("/customers")
public class CustomersResource {

    @GET
    @Produces("application/json")
    public List<Customer> getCustomers() {
        if(LoggedInServlet.Salsaa != null && !LoggedInServlet.Salsaa.isEmpty()) {
            return Customer.getAllCustomers();
        }else{
            return null;
        }
    }

    @POST
    @Path("nojackson")
    @Produces("application/json")
    public String createCustomerOld(@FormParam("name") String nm){
        Customer newCustomer = Customer.createCustomer(nm);
        JsonObjectBuilder job = Json.createObjectBuilder();
        if(newCustomer != null){
            job.add("id", newCustomer.getId());
            job.add("name", newCustomer.getName());
        } else{
            job.add("error", "klant is niet gemaakt");
        }
        return job.build().toString();
    }

    @POST
    @Path("jackson")
    @Produces("application/json")
    public Response createCustomer(@FormParam("name") String nm) {
        Customer newCustomer = Customer.createCustomer(nm);
        if (newCustomer == null) {
            System.out.println("Klant bestond al");
            return Response.status(Response.Status.CONFLICT).entity(new AbstractMap.SimpleEntry<>("result", "klantbestond al")).build();
        }
        System.out.println("Klant Aangemaakt");
        return Response.ok(newCustomer).build();
    }

}
