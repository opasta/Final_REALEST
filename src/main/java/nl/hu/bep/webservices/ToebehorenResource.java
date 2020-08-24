package nl.hu.bep.webservices;

import nl.hu.bep.model.Aquarium;

import nl.hu.bep.model.Toebehoren;
import nl.hu.bep.servlets.LoggedInServlet;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.AbstractMap;
import java.util.List;


@Path("/toebehoren")
public class ToebehorenResource {

    @GET
    @Produces("application/json")
    public List<Toebehoren> getAllToebehoren() {
        if (LoggedInServlet.Amin_or_not.equals("admin")){
            return Toebehoren.getAllToebehoren();
        }else{
            return null;
        }
    }

    @GET
    @PermitAll
    @Path("{userId}")
    @Produces("application/json")
    public List<Toebehoren> gettoebehoor(@PathParam("userId") int id) {
        String idToString = "" + id;

        if (LoggedInServlet.usernumber.equals(idToString)) {
            return Toebehoren.gettAllToebehorenEqualToAquarium(id);
        }else{
            return null;
        }
    }

    @POST
    @Path("toebehoren_add")
    @Produces("application/json")
    public Response createUser(@FormParam("ownerID2ownerID2") int ownId, @FormParam("aquaID2") int aquaID, @FormParam("model") String md, @FormParam("snumber") String sn) {
        String idToString = ownId + ", " + aquaID;
        if(Aquarium.getAllAquariumsEqualToUser(ownId).toString().contains(idToString)){
            Toebehoren newToebehoren = Toebehoren.createToebehoren(aquaID, md, sn);
            if (newToebehoren == null) {
                System.out.println("Toebehoren bestond al");
                return Response.status(Response.Status.CONFLICT).entity(new AbstractMap.SimpleEntry<>("result", "Toebehoren bestond al")).build();
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
        String userNO = LoggedInServlet.usernumber;
        int idToString =Integer.parseInt(userNO);
        String userAquarium = idToString + ", " + Toebehoren.allToebehoren.get((id - 1)).toString();

        if (LoggedInServlet.Amin_or_not.equals("admin")){
          Toebehoren.allToebehoren.remove(Toebehoren.getBToebehorenById(id));
          Toebehoren.ownToebehoren.remove(Toebehoren.getBToebehorenById(id));
        }else{
            System.out.println("not the admin");
            for (int i = 0; i <= Aquarium.getAllAquariums().size(); i++) {
                if(Aquarium.getAllAquariums().get(i).toString().equals(userAquarium)){
                    System.out.println("matchend, komt voor in de lijst");
                    Toebehoren.allToebehoren.remove(Toebehoren.getBToebehorenById(id));
                    Toebehoren.ownToebehoren.remove(Toebehoren.getBToebehorenById(id));
                    break;
                }else{
                    System.out.println("NIET matchend, komt NIET voor in de lijst");
                }
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
