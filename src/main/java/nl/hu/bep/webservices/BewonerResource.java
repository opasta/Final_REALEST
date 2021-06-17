package nl.hu.bep.webservices;

import nl.hu.bep.model.Aquarium;
import nl.hu.bep.model.Bewoner;
import nl.hu.bep.model.User;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.AbstractMap;
import java.util.Base64;

@Path("/bewoner")
public class BewonerResource {

    @GET
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        System.out.println("Get all bewoners");
        return Response.ok(Bewoner.getAll()).build();
    }

    @GET
    @RolesAllowed({"user", "admin"})
    @Path("currentUser")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOwn(@Context HttpHeaders headers) {
        Base64.Decoder decoder = Base64.getDecoder();
        String payload = new String(decoder.decode(headers.getHeaderString("authorization").substring(7).split("\\.")[1]));

        String[] parts = payload.split("\"");
        System.out.println(parts[3]);
        User user = User.getUserByName(parts[3]);
        return Response.ok(Bewoner.getAllEqualToUser(user.getId())).build();
    }

    @POST
    @RolesAllowed({"user", "admin"})
    @Path("new")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBewoner(@Context HttpHeaders headers, @FormParam("aquaID") int aquariumID, @FormParam("sname") String soortnaam, @FormParam("color") String kleurnaam,  @FormParam("amount") int aantal) {
        Base64.Decoder decoder = Base64.getDecoder();
        String payload = new String(decoder.decode(headers.getHeaderString("authorization").substring(7).split("\\.")[1]));

        String[] parts = payload.split("\"");
        if (Aquarium.getById(aquariumID).ownerId == User.getUserByName(parts[3]).getId()){
            Bewoner newbewoner = Bewoner.createBewoner(aquariumID, soortnaam, kleurnaam, aantal);
            if (newbewoner == null) {
                System.out.println("Bewoner bestond al");
                return Response.status(Response.Status.CONFLICT).entity(new AbstractMap.SimpleEntry<>("result", "Bewoner bestond al")).build();
            }
            System.out.println("Bewoner Aangemaakt");
            return Response.ok(newbewoner).build();

        }else
            return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @DELETE
    @RolesAllowed({"user", "admin"})
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteBewoner(@Context HttpHeaders headers, @PathParam("id") int id) {
        System.out.println("Delete bewoner");
        Base64.Decoder decoder = Base64.getDecoder();
        String payload = new String(decoder.decode(headers.getHeaderString("authorization").substring(7).split("\\.")[1]));

        String[] parts = payload.split("\"");
        System.out.println(parts[3]);
        User user = User.getUserByName(parts[3]);
        Bewoner bewoner = Bewoner.getById(id);
        Aquarium aquarium = Aquarium.getById(bewoner.aquariumID);
        if (aquarium.ownerId == user.getId() || user.getRole().equals("admin")) {
            if (Bewoner.remove(id))
                return Response.ok().build();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
