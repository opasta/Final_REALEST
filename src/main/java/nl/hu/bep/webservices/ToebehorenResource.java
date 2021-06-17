package nl.hu.bep.webservices;

import nl.hu.bep.model.Aquarium;
import nl.hu.bep.model.Toebehoren;
import nl.hu.bep.model.User;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.AbstractMap;
import java.util.Base64;



@Path("/toebehoren")
public class ToebehorenResource {

    @GET
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        System.out.println("Get all toebehoren");
        return Response.ok(Toebehoren.getAll()).build();
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
        return Response.ok(Toebehoren.getAllEqualToUser(user.getId())).build();
    }

    @POST
    @RolesAllowed({"user", "admin"})
    @Path("new")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createToebehoren(@Context HttpHeaders headers, @FormParam("aquaID") int aquariumID, @FormParam("model") String model, @FormParam("serienummer") String serienummer) {
        Base64.Decoder decoder = Base64.getDecoder();
        String payload = new String(decoder.decode(headers.getHeaderString("authorization").substring(7).split("\\.")[1]));

        String[] parts = payload.split("\"");
        if (Aquarium.getById(aquariumID).ownerId == User.getUserByName(parts[3]).getId()){
            Toebehoren newToebehoren = Toebehoren.createToebehoren(aquariumID, model, serienummer);
            if (newToebehoren == null) {
                System.out.println("Toebehoren bestond al");
                return Response.status(Response.Status.CONFLICT).entity(new AbstractMap.SimpleEntry<>("result", "Toebehoren bestond al")).build();
            }
            System.out.println("Toebehoren Aangemaakt");
            return Response.ok(newToebehoren).build();

        }else
            return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @DELETE
    @RolesAllowed({"user", "admin"})
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteToebehoren(@Context HttpHeaders headers, @PathParam("id") int id) {
        System.out.println("Delete toebehoren");
        Base64.Decoder decoder = Base64.getDecoder();
        String payload = new String(decoder.decode(headers.getHeaderString("authorization").substring(7).split("\\.")[1]));

        String[] parts = payload.split("\"");
        System.out.println(parts[3]);
        User user = User.getUserByName(parts[3]);
        Toebehoren toebehoren = Toebehoren.getById(id);
        Aquarium aquarium = Aquarium.getById(toebehoren.aquariumID);
        if (aquarium.ownerId == user.getId() || user.getRole().equals("admin")) {
            if (Toebehoren.remove(id))
                return Response.ok().build();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
