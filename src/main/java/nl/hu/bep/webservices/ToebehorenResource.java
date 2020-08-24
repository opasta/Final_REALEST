package nl.hu.bep.webservices;

import nl.hu.bep.model.Aquarium;
import nl.hu.bep.model.Bewoner;
import nl.hu.bep.model.Toebehoren;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;



@Path("/toebehoren")
public class ToebehorenResource {

    @GET
    @Produces("application/json")
    public List<Toebehoren> getAllToebehoren() {
        return Toebehoren.getAllToebehoren();
    }

    @GET
    @PermitAll
    @Path("{customerid}")
    @Produces("application/json")

    public List<Aquarium> getCustomer(@PathParam("customerid") int id) {


        return Aquarium.getAllAquariumsEqualToUser(id);
        //return Response.ok(aquarium).build();
    }

   // @GET
  //  @PermitAll
  //  @Path("{customerid}")
  //  @Produces("application/json")

   // public List<Toebehoren> getbewoner(@PathParam("customerid") int id) {

   //     return Toebehoren.gettAllbewonersEqualToAquarium(id);
        //     return Aquarium.getAllAquariumsEqualToUser(id);
        //return Response.ok(aquarium).build();
//    }

}
