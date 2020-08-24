package nl.hu.bep.model;

import java.util.ArrayList;
import java.util.List;

public class Aquarium {
    public static List<Aquarium> allAquariums = new ArrayList<>();
    public static List<Aquarium> ownAquariums = new ArrayList<>();
    public static List<String> listOwner = new ArrayList<>();
    public int ownerId;
    public String name;
    public Double lengte;
    public Double breedte;
    public Double hoogte;
    public String bodemsoort;
    public String watersoort;
    private int id;
    private static int numCustomers = 0;

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
        return allAquariums.stream().filter(e->e.id==id).findFirst().orElse(null);
    }

    public static List<Aquarium> getAllAquariums() {
        return allAquariums;
    }

    public static boolean removeAquarium(int id){
        if (id>0) return allAquariums.remove(allAquariums.indexOf(Aquarium.getAquariumByrId(id))) != null;
        return false;
    }
    public static boolean removeAquariumOthers(int id){
        if (id>0) return ownAquariums.remove(ownAquariums.indexOf(Aquarium.getAquariumByrId(id))) != null;
        return false;
    }

    public static List<Aquarium> getAllAquariumsEqualToUser(int owner) {
        String intToString = "" + owner;
        System.out.println(allAquariums);
        listOwner.clear();

        for (int i = 0; (i ) < allAquariums.size(); i++) {
            String string = allAquariums.get(i).toString();
            String[] parts = string.split(", ");
            listOwner.add(parts[0]);

        }

        System.out.println("aantal dinges:" + allAquariums.size());
        for (int i = 0; i < allAquariums.size(); i++) {
            if (listOwner.get(i).equals(intToString)){
                if(ownAquariums.contains(allAquariums.get(i))){
                    System.out.println("Already added");
                }else{
                    System.out.println("Adding to own");
                    ownAquariums.add(allAquariums.get(i));
                }
            }
        }
        return ownAquariums;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return ""+ ownerId + ", " + id;
    }

    public int getId(){
        return id;
    }
}
