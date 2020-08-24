package nl.hu.bep.webservices;

import nl.hu.bep.model.User;
import nl.hu.bep.servlets.LoggedInServlet;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.AbstractMap;
import java.util.List;

@Path("/users")
public class UserResource {

    @GET
    @Produces("application/json")
    public List<User> getAllUsers() {
        if (LoggedInServlet.Amin_or_not.equals("admin")){
            return User.getAllUsers();
        }else{
            return null;
        }
    }

    @POST
    @Path("allUsers")
    @Produces("application/json")
    public Response createUser(@FormParam("name") String nm, @FormParam("password") String pwd,@FormParam("voornaam") String fnm,@FormParam("achternaam") String rnm) {

        User newCustomer = User.createUser(nm, pwd, fnm, rnm);
        if (newCustomer == null) {
            System.out.println("Gebruiker bestond al");
            return Response.status(Response.Status.CONFLICT).entity(new AbstractMap.SimpleEntry<>("result", "Gebruiker bestond al")).build();
        }
        System.out.println("Gebruiker Aangemaakt");
        return Response.ok(newCustomer).build();
    }

    @DELETE
    @Path("{customerid}")
    @Produces("application/json")
    public Response deleteCustomerName(@PathParam("customerid") int id){
        if(User.removeCustomer(id))
            return Response.ok().build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
