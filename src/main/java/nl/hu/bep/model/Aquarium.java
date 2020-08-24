package nl.hu.bep.model;

import java.util.ArrayList;
import java.util.List;

public class Aquarium {
    public int ownerId;
    public String name;
    public Double lengte;
    public Double breedte;
    public Double hoogte;
    public String bodemsoort;
    public String watersoort;
    public static List<Aquarium> allAquariums = new ArrayList<>();
    public static List<Aquarium> ownAquariums = new ArrayList<>();
    private int id;
    private static int numCustomers = 0;



    public static List<String> listOwner = new ArrayList<>();
    public static List<String> ListAquarium = new ArrayList<>();




    public Aquarium(int oId, String nm, Double lnt, Double wdt, Double hgt, String bdsoort, String wtrsoort){
        id = ++numCustomers;
        ownerId = oId;
        name = nm;
        lengte = lnt;
        breedte = wdt;
        hoogte = hgt;
        bodemsoort = bdsoort;
        watersoort =  wtrsoort;
        allAquariums.add(this);
    }

    public static Aquarium createAquarium(int oId, String nm, Double lnt, Double wdt, Double hgt, String bdsoort, String wtrsoort){
        if (allAquariums.stream().noneMatch(e->e.getName().equals(nm))) {
            Aquarium newAquarium = new Aquarium(oId, nm, lnt, wdt, hgt, bdsoort, wtrsoort);
            return newAquarium;
        }
       else return null;
   }

    public static Aquarium getAquariumByrId(int id) {
      //  return allAquariums.stream().filter(e->e.ownerId==id).findFirst().orElse(null);
      //  return allMyUsers.stream().filter(e->e.id==id).findFirst().orElse(null);
        return allAquariums.stream().filter(e->e.id==id).findFirst().orElse(null);
    }

    public static List<Aquarium> getAllAquariums() {
        return allAquariums;
    }

    public static boolean removeAquarium(int id){
      //  if (id>0) return allAquariums.remove(allAquariums.indexOf(Aquarium.allAquariums(id))) != null;
      //  System.out.println("deleted item: " + id + "from list" + allAquariums);
     //   System.out.println("deleted item: " + id + "from list" + ownAquariums);
        if (id>0) return allAquariums.remove(allAquariums.indexOf(Aquarium.getAquariumByrId(id))) != null;

       // if (id>0) return ownAquariums.remove(allAquariums.indexOf(Aquarium.getAquariumByrId(id))) != null;
      //  System.out.println("deleted item: " + id + "from list" + ownAquariums);
        return false;
    }
    public static boolean removeAquariumOthers(int id){
        //  if (id>0) return allAquariums.remove(allAquariums.indexOf(Aquarium.allAquariums(id))) != null;
     //   System.out.println("deleted item: " + id + "from list" + ownAquariums);
        if (id>0) return ownAquariums.remove(ownAquariums.indexOf(Aquarium.getAquariumByrId(id))) != null;

        //if (id>0) return ownAquariums.remove(allAquariums.indexOf(Aquarium.getAquariumByrId(id))) != null;
       // System.out.println("deleted item: " + id + "from list" + ownAquariums);
        return false;
    }

 //   public static List<Aquarium> betterToString(){

      //  allAquariums.get(i).toString()
   //     for (int i = 0; i < allAquariums.size(); i++) {


  //      }
 //   }

    public static List<Aquarium> getAllAquariumsEqualToUser(int owner) {
        System.out.println(allAquariums);
        listOwner.clear();
        ListAquarium.clear();


        System.out.println("size of listje: " + allAquariums.size());
        for (int i = 0; (i ) < allAquariums.size(); i++) {
            String string = allAquariums.get(i).toString();
            String[] parts = string.split(", ");

            listOwner.add(parts[0]);
            ListAquarium.add(parts[1]);

        }

        System.out.println(listOwner);
        System.out.println(ListAquarium);






        System.out.println("it starts right here!");
        System.out.println(owner);
        String ownerId = "" + owner;
        System.out.println("aantal dinges:" + allAquariums.size());
        for (int i = 0; i < allAquariums.size(); i++) {
            if (listOwner.get(i).equals(ownerId)){
                if(ownAquariums.contains(allAquariums.get(i))){
                    System.out.println("YOU SHOULD NOT DO ANYSING");

                }else{
                    System.out.println("apperently equal") ;
                    ownAquariums.add(allAquariums.get(i));
                }
                //System.out.println("loop" + "all aquariums get i:" + allAquariums.get(i)) ;

               // System.out.println("loop" + "aantal aquariums: " + i);
            }
           // System.out.println("all aquariums get i:" + allAquariums.get(i)) ;

           // System.out.println("all aquariums print it:" + allAquariums.get(i).toString()) ;
           // System.out.println("aantal aquariums: " + i);
            //ownAquariums.add(i);
        }
        System.out.println("my own aquariums: " + ownAquariums);

        //System.out.println((Aquarium) ownAquariums);
        return ownAquariums;
    }

  //  public static List<Aquarium> getAllAquariums() {
  //      return allAquariums;
   // }



    public String getName() {
        return name;
    }

    public String toString() {
        return ""+ ownerId + ", " + id;
     //   return ""+ ownerId + "unique key of aquarium itsself!! " + id;
    }

   // public String toArray() {
//
  //         return ""+ id;
  //  }

    public int getId(){
        return id;
    }
}
