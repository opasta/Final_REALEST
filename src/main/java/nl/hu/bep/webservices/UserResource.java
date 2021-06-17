package nl.hu.bep.webservices;


import nl.hu.bep.model.User;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

@Path("/user")
public class UserResource {

    @GET
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        System.out.println("Get all users");
        return Response.ok(User.getAll()).build();
    }

    @GET
    @RolesAllowed({"user", "admin"})
    @Path("current")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCurrent(@Context HttpHeaders headers) {
        Base64.Decoder decoder = Base64.getDecoder();
        String payload = new String(decoder.decode(headers.getHeaderString("authorization").substring(7).split("\\.")[1]));

        String[] parts = payload.split("\"");
        System.out.println(parts[3]);

        return Response.ok(User.getUserByName(parts[3])).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(@FormParam("name") String name, @FormParam("password") String password,@FormParam("voornaam") String voornaam,@FormParam("achternaam") String achternaam) {

        User newCustomer = User.createUser(name, password, voornaam, achternaam);
        if (newCustomer == null) {
            System.out.println("Gebruiker bestond al");
            return Response.status(Response.Status.CONFLICT).entity(new AbstractMap.SimpleEntry<>("result", "Gebruiker bestond al")).build();
        }
        System.out.println("Gebruiker Aangemaakt");
        return Response.ok(newCustomer).build();
    }

    @DELETE
    @RolesAllowed("admin")
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCustomerName(@PathParam("id") int id){
        System.out.println("Delete user");
        if(User.removeCustomer(id))
            return Response.ok().build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
