package nl.hu.bep.webservices;

import nl.hu.bep.model.Customer;
import nl.hu.bep.model.MyUser;
import nl.hu.bep.servlets.LoggedInServlet;

import javax.annotation.security.RolesAllowed;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.AbstractMap;
import java.util.List;

@Path("/users")
public class UserResource {

    @GET
    @Produces("application/json")
    public List<MyUser> getAllUsers() {
        if (LoggedInServlet.Salsaa != null && !LoggedInServlet.Salsaa.isEmpty()) {
            return MyUser.getAllUsers();
        } else {
            return null;
        }
    }


    @POST
    @Path("allUsers")
    @Produces("application/json")
    public Response createUser(@FormParam("name") String nm, @FormParam("password") String pwd,@FormParam("voornaam") String fnm,@FormParam("achternaam") String rnm) {


        MyUser newCustomer = MyUser.createUser(nm, pwd, fnm, rnm);
        if (newCustomer == null) {
            System.out.println("Gebruiker bestond al");
            return Response.status(Response.Status.CONFLICT).entity(new AbstractMap.SimpleEntry<>("result", "Gebruiker bestond al")).build();
        }
        System.out.println("Gebruiker Aangemaakt");
        return Response.ok(newCustomer).build();

    }

    @DELETE
    //@RolesAllowed("admin")
    @Path("{customerid}")
    @Produces("application/json")
    public Response deleteCustomerName(@PathParam("customerid") int id){
        if(MyUser.removeCustomer(id))
            return Response.ok().build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
