package nl.hu.bep.webservices;

import nl.hu.bep.model.Aquarium;
import nl.hu.bep.model.Bewoner;
import nl.hu.bep.servlets.LoggedInServlet;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.AbstractMap;
import java.util.List;


@Path("/bewoners")
public class BewonerResource {

    @GET
    @Produces("application/json")
    public List<Bewoner> getAllBewoners() {
        if (LoggedInServlet.Amin_or_not.equals("admin")){
            return Bewoner.getAllBewoners();
        }else{
            return null;
        }
    }

    @GET
    @PermitAll
    @Path("{userId}")
    @Produces("application/json")
    public List<Bewoner> getbewoner(@PathParam("userId") int id) {
        String idToString = "" + id;

        if (LoggedInServlet.usernumber.equals(idToString)) {
            return Bewoner.gettAllbewonersEqualToAquarium(id);
        }else{
            return null;
        }
    }



    @POST
    @Path("bewoner_add")
    @Produces("application/json")
    public Response createUser(@FormParam("ownerID") int ownId, @FormParam("aquaID") int aquaID, @FormParam("sname") String sname, @FormParam("color") String color,  @FormParam("amount") int amount) {
       String idToString = ownId + ", " + aquaID;

        if(Aquarium.getAllAquariumsEqualToUser(ownId).toString().contains(idToString)){
            Bewoner newBewoner = Bewoner.createBewoner(aquaID, sname, color, amount);
            if (newBewoner == null) {
                System.out.println("Bewoner bestond al");
                return Response.status(Response.Status.CONFLICT).entity(new AbstractMap.SimpleEntry<>("result", "Bewoner bestond al")).build();
            }
        }else{
            System.out.println("aquarium is not yours!");
        }
        return null;
    }

    @DELETE
    @Path("{customerid}")
    @Produces("application/json")
    public Response deleteAquariums(@PathParam("customerid") int id){
        String userno = LoggedInServlet.usernumber;
        int idToString =Integer.parseInt(userno);
        System.out.println("owner id: " + userno);

        String owner_aquarium = idToString + ", " + Bewoner.allBewoners.get((id - 1)).toString();

        if (LoggedInServlet.Amin_or_not.equals("admin")){
            System.out.println("admin can delete it all!!");
            Bewoner.allBewoners.remove(Bewoner.getBewonerById(id));
            Bewoner.ownBewoners.remove(Bewoner.getBewonerById(id));

        }else{
            System.out.println("not the admin");

            for (int i = 0; i <= Aquarium.getAllAquariums().size(); i++) {
                if(Aquarium.getAllAquariums().get(i).toString().equals(owner_aquarium)){
                    Bewoner.allBewoners.remove(Bewoner.getBewonerById(id));
                    Bewoner.ownBewoners.remove(Bewoner.getBewonerById(id));
                    break;
                }else{
                    System.out.println("NIET matchend, komt NIET voor in de lijst");
                }
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
