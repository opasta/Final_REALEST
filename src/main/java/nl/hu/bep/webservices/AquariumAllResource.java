package nl.hu.bep.webservices;

import nl.hu.bep.model.Aquarium;
import nl.hu.bep.servlets.LoggedInServlet;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.AbstractMap;
import java.util.List;


@Path("/aquariums")
public class AquariumAllResource {

    @GET
    @Produces("application/json")
    public List<Aquarium> getAllAquariums() {
        if (LoggedInServlet.Amin_or_not.equals("admin")){
            return Aquarium.getAllAquariums();
        }else{
            return null;
        }
    }

    @GET
    @PermitAll
    @Path("{userId}")
    @Produces("application/json")

    public List<Aquarium> getCustomer(@PathParam("userId") int id) {
        String idToString = "" + id;

        if (LoggedInServlet.usernumber.equals(idToString)) {
            return Aquarium.getAllAquariumsEqualToUser(id);
        }else{
            return null;
        }
    }

    @POST
    @Path("aquariumz")
    @Produces("application/json")
    public Response createUser(@FormParam("ownerID") int ownId, @FormParam("name") String nm, @FormParam("length") Double lnt, @FormParam("width") Double wdt, @FormParam("height") Double hgt, @FormParam("bottom")String bdsoort, @FormParam("water")String wtrsoort) {

        Aquarium newAquarium = Aquarium.createAquarium(ownId, nm, lnt, wdt, hgt, bdsoort, wtrsoort);
        if (newAquarium == null) {
            System.out.println("Aquarium bestond al");
            return Response.status(Response.Status.CONFLICT).entity(new AbstractMap.SimpleEntry<>("result", "Aquarium bestond al")).build();
        }
        System.out.println("Aquarium Aangemaakt");
        return Response.ok(newAquarium).build();
    }

    @DELETE
    @Path("{aquariumID}")
    @Produces("application/json")
    public Response deleteAquariums(@PathParam("aquariumID") int id){
        String idToString = "" + id;

        for (int i = 0; (i ) < Aquarium.allAquariums.size(); i++) {
            String string = Aquarium.allAquariums.get(i).toString();
            String[] parts = string.split(", ");

            Aquarium.listOwner.add(parts[0]);
        }

        if (LoggedInServlet.Amin_or_not.equals("admin")){

            System.out.println("Admin");
            if(!Aquarium.ownAquariums.toString().contains(idToString)){
                System.out.println("Deleting someone's else");
                if(Aquarium.removeAquarium(id))
                    return Response.ok().build();
            }
            System.out.println("Deleting my own");
            Aquarium.removeAquariumOthers(id);
            Aquarium.removeAquarium(id);
                return Response.ok().build();

        }else{
            System.out.println("NOT THE LEADER");
            if(Aquarium.removeAquariumOthers(id) && Aquarium.removeAquarium(id))
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}


