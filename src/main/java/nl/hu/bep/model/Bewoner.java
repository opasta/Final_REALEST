package nl.hu.bep.model;

import java.util.ArrayList;
import java.util.List;

public class Bewoner {
    public static List<Bewoner> allBewoners = new ArrayList<>();
    public static List<Bewoner> ownBewoners = new ArrayList<>();

    public static List<String> testStringgg = new ArrayList<>();

    public static List<Bewoner> testlistjee = new ArrayList<>();


    private int id;
    public int aquariumID;
    public String soortnaam;
    public String kleurnaam;
    public int aantal;
    public String groepsdier = "Nee";
    private static int numCustomers = 0;

    public Bewoner(int aquaID, String sortnm, String color, int amount){
        id = ++numCustomers;
        aquariumID = aquaID;
        soortnaam = sortnm;
        kleurnaam = color;
        aantal = amount;
        if (amount > 1) {
            groepsdier = "Ja";
        }
        allBewoners.add(this);



    }

    public static Bewoner createBewoner(int aquaID, String sortnm, String color, int amount){
        if (allBewoners.stream().noneMatch(e->e.getSoortnaam().equals(sortnm))) {
            Bewoner newBewoner = new Bewoner(aquaID, sortnm, color, amount);
            return newBewoner;
        }
        else return null;
    }



    public static List<Bewoner> gettAllbewonersEqualToAquarium(int user) {
        String uset = "" + user;
        System.out.println(uset + " usernummer hieroo" );
        testStringgg.clear();

        System.out.println("size of listje: " + Aquarium.allAquariums.size());
        for (int i = 0; (i ) < Aquarium.allAquariums.size(); i++) {

            String string = Aquarium.allAquariums.get(i).toString();
            String[] parts = string.split(", ");
            Aquarium.listOwner.add(parts[0]);
            Aquarium.ListAquarium.add(parts[1]);

            if (parts[0].equals(uset)){
                testStringgg.add(parts[1]);
            }
        }
        String test = "2";
        System.out.println(testStringgg);
        if(testStringgg.contains(test)){
            System.out.println("it contains 2");
        }

        System.out.println("gonna kresj? " + allBewoners + testStringgg);
        for (int i=0 ; i < allBewoners.size(); i++) {
            System.out.println(" loop number ?? " + i);
            System.out.println(" Alle bewonersss " + allBewoners);
            System.out.println(" Teststring ?? " + testStringgg);
            if(testStringgg.contains(allBewoners.get(i).toString())){
                if(ownBewoners.contains(allBewoners.get(i))){
                    System.out.println("YOU SHOULD NOT DO ANYSING");

                }else{
                    System.out.println("apperently equal") ;
                    ownBewoners.add(allBewoners.get(i));

                }
            }
//


          //  if(!testStringgg.contains(allBewoners.get(i).toString())){
            //    ownBewoners.add(allBewoners.get(i));
                //Bewoner newBewoner = Bewoner.createBewoner(aquaID, sname, color, amount);
          //  }
        }




        return ownBewoners;
    }

    public static Bewoner getBewonerById(int id) {
        //  return allAquariums.stream().filter(e->e.ownerId==id).findFirst().orElse(null);
        //  return allMyUsers.stream().filter(e->e.id==id).findFirst().orElse(null);
        return allBewoners.stream().filter(e->e.id==id).findFirst().orElse(null);
    }

    public static boolean removeBewonerall(int id){
        //  if (id>0) return allAquariums.remove(allAquariums.indexOf(Aquarium.allAquariums(id))) != null;
        //  System.out.println("deleted item: " + id + "from list" + allAquariums);
        //   System.out.println("deleted item: " + id + "from list" + ownAquariums);
        System.out.println("test all");
        if (id>0) return allBewoners.remove(allBewoners.indexOf(Bewoner.getBewonerById(id))) != null;

        // if (id>0) return ownAquariums.remove(allAquariums.indexOf(Aquarium.getAquariumByrId(id))) != null;
        //  System.out.println("deleted item: " + id + "from list" + ownAquariums);
        return false;
    }
    public static boolean removeBewonerOwn(int id){
        //  if (id>0) return allAquariums.remove(allAquariums.indexOf(Aquarium.allAquariums(id))) != null;
        //   System.out.println("deleted item: " + id + "from list" + ownAquariums);
     //   removeBewonerall(id);
        System.out.println("test own");
        if (id>0) return ownBewoners.remove(ownBewoners.indexOf(Bewoner.getBewonerById(id))) != null;

        //if (id>0) return ownAquariums.remove(allAquariums.indexOf(Aquarium.getAquariumByrId(id))) != null;
        // System.out.println("deleted item: " + id + "from list" + ownAquariums);
        return false;
    }



    public static List<Bewoner> getAllBewoners() {
        return allBewoners;
    }

 //   @Override

    public int getId(){
        return id;
    }

    public String toString() {
        return ""+ aquariumID;
    }

    public String getSoortnaam() {
        return soortnaam;
    }




}

