package nl.hu.bep.webservices;

import nl.hu.bep.model.Aquarium;
import nl.hu.bep.model.Customer;
import nl.hu.bep.model.MyUser;
import nl.hu.bep.servlets.LoggedInServlet;

import javax.annotation.security.PermitAll;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/aquariums")
public class AquariumAllResource {

    @GET
    @Produces("application/json")
    public List<Aquarium> getAllAquariums() {
        return Aquarium.getAllAquariums();
    }

    @GET
    @PermitAll
    @Path("{customerid}")
    @Produces("application/json")

    public List<Aquarium> getCustomer(@PathParam("customerid") int id) {

       // System.out.println(Aquarium.getAllAquariumsEqualToUser(id));

      //  System.out.println(Aquarium.getAquariumByOwnerId(id));


      //  Aquarium aquarium = Aquarium.getAquariumByOwnerId(id);
      //  if (aquarium == null) {
     //       Map<String, String> messages = new HashMap<>();
     //       System.out.println("testerttt");
     //       messages.put("error", "Customer does not exist");
     //       messages.put("requestedId", Integer.toString(id));
     //       return Response.status(409).entity(messages).build();
     //   }
        return Aquarium.getAllAquariumsEqualToUser(id);
        //return Response.ok(aquarium).build();
    }




    @POST
    @Path("aquariumz")
    @Produces("application/json")
    public Response createUser(@FormParam("ownerID") int ownId,
                               @FormParam("name") String nm,
                               @FormParam("length") Double lnt,
                               @FormParam("width") Double wdt,
                               @FormParam("height") Double hgt,
                               @FormParam("bottom")String bdsoort,
                               @FormParam("water")String wtrsoort
    ) {


        Aquarium newAquarium = Aquarium.createAquarium(ownId, nm, lnt, wdt, hgt, bdsoort, wtrsoort);
        System.out.println("hieronder");
        System.out.println(ownId);
        System.out.println("hierboven");
        if (newAquarium == null) {
            System.out.println("Aquarium bestond al");
            return Response.status(Response.Status.CONFLICT).entity(new AbstractMap.SimpleEntry<>("result", "Aquarium bestond al")).build();
        }
        System.out.println("Aquarium Aangemaakt");
        return Response.ok(newAquarium).build();

    }

    @DELETE
    //@RolesAllowed("admin")
    @Path("{customerid}")
    @Produces("application/json")
    public Response deleteAquariums(@PathParam("customerid") int id){
        String idd = "" + id;

        for (int i = 0; (i ) < Aquarium.allAquariums.size(); i++) {
            String string = Aquarium.allAquariums.get(i).toString();
            String[] parts = string.split(", ");

            Aquarium.listOwner.add(parts[0]);
            Aquarium.ListAquarium.add(parts[1]);

        }

        System.out.println("this is not a test" + Aquarium.listOwner);

        System.out.println("this is not a test2" + Aquarium.ListAquarium);



        //!Aquarium.ownAquariums.toString().contains(idd)


        if (LoggedInServlet.adminoes.equals("admin")){
       //     System.out.println(LoggedInServlet.adminoes);
            System.out.println("I AM THE LEADER!!!!");
        //    System.out.println(Aquarium.ownAquariums.toString() + "contains" + idd);
         //   System.out.println(!Aquarium.ownAquariums.toString().contains(idd));





            if(!Aquarium.ownAquariums.toString().contains(idd)){
                System.out.println("DELETING SOMEONE ELES SHIT, or i haven't added it to my own list");
                if(Aquarium.removeAquarium(id))
                    return Response.ok().build();
            }
            System.out.println(LoggedInServlet.adminoes);
            System.out.println("DELETING my own SHIT");
            Aquarium.removeAquariumOthers(id);
            Aquarium.removeAquarium(id);
                return Response.ok().build();

        }else{
            System.out.println("NOT THE LEADER");
            if(Aquarium.removeAquariumOthers(id) && Aquarium.removeAquarium(id))
            return Response.ok().build();
        }
     //   System.out.println(LoggedInServlet.adminoes);
    //    if(Aquarium.removeAquariumOthers(id) && Aquarium.removeAquarium(id))
      //      return Response.ok().build();
        return Response.status(Response.Status.NOT_FOUND).build();
    }





}


