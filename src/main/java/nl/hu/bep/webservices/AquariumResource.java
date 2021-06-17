package nl.hu.bep.webservices;

import nl.hu.bep.model.Aquarium;
import nl.hu.bep.model.User;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.AbstractMap;
import java.util.Base64;


@Path("/aquarium")
public class AquariumResource {

    @GET
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        System.out.println("Get all users");
            return Response.ok(Aquarium.getAll()).build();
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
        return Response.ok(Aquarium.getAllEqualToUser(user.getId())).build();
    }

    @POST
    @RolesAllowed({"user", "admin"})
    @Path("new")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAquarium(@Context HttpHeaders headers, @FormParam("nameAqua") String name, @FormParam("length") Double lengte, @FormParam("width") Double breedte, @FormParam("height") Double hoogte, @FormParam("bottom") String bodemsoort, @FormParam("water") String watersoort) {
        Base64.Decoder decoder = Base64.getDecoder();
        String payload = new String(decoder.decode(headers.getHeaderString("authorization").substring(7).split("\\.")[1]));

        String[] parts = payload.split("\"");
        System.out.println(parts[3]);
        User user = User.getUserByName(parts[3]);
        Aquarium newAquarium = Aquarium.createAquarium(user.getId(), name, lengte, breedte, hoogte, bodemsoort, watersoort);
        if (newAquarium == null) {
            System.out.println("Aquarium bestond al");
            return Response.status(Response.Status.CONFLICT).entity(new AbstractMap.SimpleEntry<>("result", "Aquarium bestond al")).build();
        }
        System.out.println("Aquarium Aangemaakt");
        return Response.ok(newAquarium).build();
    }

    @DELETE
    @RolesAllowed({"user", "admin"})
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAquarium(@Context HttpHeaders headers, @PathParam("id") int id) {
        System.out.println("Delete aquarium");
        Base64.Decoder decoder = Base64.getDecoder();
        String payload = new String(decoder.decode(headers.getHeaderString("authorization").substring(7).split("\\.")[1]));

        String[] parts = payload.split("\"");
        System.out.println(parts[3]);
        User user = User.getUserByName(parts[3]);
        Aquarium aquarium = Aquarium.getById(id);

        if (aquarium.ownerId == user.getId() || user.getRole().equals("admin")) {
            if (Aquarium.remove(id))
                return Response.ok().build();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}


