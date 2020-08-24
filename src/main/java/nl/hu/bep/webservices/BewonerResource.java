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
        return Bewoner.getAllBewoners();
    }

    @GET
    @PermitAll
    @Path("{customerid}")
    @Produces("application/json")

    public List<Bewoner> getbewoner(@PathParam("customerid") int id) {
        System.out.println("testert");

            return Bewoner.gettAllbewonersEqualToAquarium(id);
    }

    @POST
    @Path("bewoner_add")
    @Produces("application/json")
    public Response createUser(@FormParam("ownerID") int ownId,
                               @FormParam("aquaID") int aquaID,
                               @FormParam("sname") String sname,
                               @FormParam("color") String color,
                               @FormParam("amount") int amount

    ) {
       String tester = ownId + ", " + aquaID;

        if(Aquarium.getAllAquariumsEqualToUser(ownId).toString().contains(tester)){


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
        String test = LoggedInServlet.usernumber;
        int intetjuur =Integer.parseInt(test);
        System.out.println(id);
        System.out.println("owner id: " + test);


        String pureExtraa = intetjuur + ", " + Bewoner.allBewoners.get((id - 1)).toString();




        System.out.println("final test!!");

        System.out.println("aquariumid van het ingevoerde bewoner: : " + Bewoner.allBewoners.get((id - 1)).toString());
        System.out.println("owner... aquarium... " + pureExtraa);
        System.out.println("alle aquariums, met owner gevolgd door aquaID" + Aquarium.getAllAquariums().toString());











        if (LoggedInServlet.adminoes.equals("admin")){
            System.out.println("admin can delete it all!!");

            System.out.println(Bewoner.allBewoners.get(id));
            Bewoner.allBewoners.remove(Bewoner.getBewonerById(id));
            Bewoner.ownBewoners.remove(Bewoner.getBewonerById(id));
            System.out.println(Bewoner.allBewoners.get(id));


                   // Bewoner.allBewoners.remove(Bewoner.getBewonerById(id));
                    //Bewoner.ownBewoners.remove(Bewoner.getBewonerById(id));









        }else{
            System.out.println("not the admin");

            for (int i = 0; i <= Aquarium.getAllAquariums().size(); i++) {

                System.out.println("aquarium: " + (i+1));
                System.out.println(Aquarium.getAllAquariums().get(i).toString());
                System.out.println(pureExtraa);

                if(Aquarium.getAllAquariums().get(i).toString().equals(pureExtraa)){
                    System.out.println("matchend, komt voor in de lijst");
                    Bewoner.allBewoners.remove(Bewoner.getBewonerById(id));
                    Bewoner.ownBewoners.remove(Bewoner.getBewonerById(id));
                    break;
                }else{
                    System.out.println("NIET matchend, komt NIET voor in de lijst");
                }


            }


         //   if (Aquarium.getAllAquariumsEqualToUser(intetjuur).toString().contains(pureExtraa)){
           //     System.out.println( "bevat mn dinges");
            //}
           // if(Aquarium.getAllAquariumsEqualToUser(intetjuur).toString().contains(testmannnn)){
             //   System.out.println("Mag m deleten want is mine!  ");
              //  Bewoner.allBewoners.remove(Bewoner.getBewonerById(id));
               // Bewoner.ownBewoners.remove(Bewoner.getBewonerById(id));
             //   System.out.println("test met haal et op" + Aquarium.getAllAquariumsEqualToUser(intetjuur).toString());
              //  System.out.println("test met haal et op 2:  " + testmannnn);
         //       if(Bewoner.removeBewonerOwn(id) && Bewoner.removeBewonerall(id))
            //    return Response.ok().build();
            //    if(Bewoner.removeBewonerOwn(id))
                //    return Response.ok().build();
           // }else{
               // System.out.println("test met haal et op" + Aquarium.getAllAquariumsEqualToUser(intetjuur).toString());
              //  System.out.println("test met haal et op 2" + testmannnn);
               // System.out.println("Deze is doneee!Mag m niet deleten, is van andere gap");
           // }

        }

        return Response.status(Response.Status.NOT_FOUND).build();

      //  System.out.println("kijken of die van mij is!" + Aquarium.getAllAquariumsEqualToUser(intetjuur).toString());

       // System.out.println("should be somesingk " + Bewoner.allBewoners.get((id) - 1).toString());
       // System.out.println("should be somesingk dosss " + Bewoner.allBewoners.toString());



      //  System.out.println("Testnaam  " + testmannnn);



       // Bewoner.allBewoners.get((id) - 1).toString();

      //  if (Aquarium.getAllAquariumsEqualToUser(intetjuur).toString() == )

        //String tester = intetjuur + ", " + aquaID;
     //   if (Aquarium.getAllAquariumsEqualToUser(intetjuur).toString().equals(tester)){
     //       System.out.println("Mijn aquarium!!");
      //  }
    //    String idd = "" + id;

       // for (int i = 0; (i ) < Aquarium.allAquariums.size(); i++) {
         //   String string = Aquarium.allAquariums.get(i).toString();
          //  String[] parts = string.split(", ");

          //  Aquarium.listOwner.add(parts[0]);
          //  Aquarium.ListAquarium.add(parts[1]);

      //  }

       // System.out.println("this is not a test" + Aquarium.listOwner);

        //System.out.println("this is not a test2" + Aquarium.ListAquarium);



        //!Aquarium.ownAquariums.toString().contains(idd)


     //   if (LoggedInServlet.adminoes.equals("admin")){
            //     System.out.println(LoggedInServlet.adminoes);
         //   System.out.println("I AM THE LEADER!!!!");
            //    System.out.println(Aquarium.ownAquariums.toString() + "contains" + idd);
            //   System.out.println(!Aquarium.ownAquariums.toString().contains(idd));





          //  if(!Aquarium.ownAquariums.toString().contains(idd)){
           //     System.out.println("DELETING SOMEONE ELES SHIT, or i haven't added it to my own list");
          //      if(Aquarium.removeAquarium(id))
           //         return Response.ok().build();
        //    }
         //   System.out.println(LoggedInServlet.adminoes);
        //    System.out.println("DELETING my own SHIT");
        //    if(Aquarium.removeAquariumOthers(id) && Aquarium.removeAquarium(id))
        //        return Response.ok().build();

      //  }else{
     //       System.out.println("NOT THE LEADER");
     //       if(Aquarium.removeAquariumOthers(id) && Aquarium.removeAquarium(id))
       //         return Response.ok().build();
     //   }
        //   System.out.println(LoggedInServlet.adminoes);
        //    if(Aquarium.removeAquariumOthers(id) && Aquarium.removeAquarium(id))
        //      return Response.ok().build();
      //  return Response.status(Response.Status.NOT_FOUND).build();
     //   return null;
    }

}
