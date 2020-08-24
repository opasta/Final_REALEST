package nl.hu.bep.model;

import java.util.ArrayList;
import java.util.List;

public class Bewoner {
    public static List<Bewoner> allBewoners = new ArrayList<>();
    public static List<Bewoner> ownBewoners = new ArrayList<>();
    public static List<String> tempString = new ArrayList<>();
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
        String intToString = "" + user;
        tempString.clear();

        for (int i = 0; (i ) < Aquarium.allAquariums.size(); i++) {

            String string = Aquarium.allAquariums.get(i).toString();
            String[] parts = string.split(", ");
            Aquarium.listOwner.add(parts[0]);

            if (parts[0].equals(intToString)){
                tempString.add(parts[1]);
            }
        }

        for (int i=0 ; i < allBewoners.size(); i++) {
            if(tempString.contains(allBewoners.get(i).toString())){
                if(ownBewoners.contains(allBewoners.get(i))){
                    System.out.println("Already added");

                }else{
                    System.out.println("Adding to own");
                    ownBewoners.add(allBewoners.get(i));
                }
            }
        }
        return ownBewoners;
    }

    public static Bewoner getBewonerById(int id) {
        return allBewoners.stream().filter(e->e.id==id).findFirst().orElse(null);
    }

    public static List<Bewoner> getAllBewoners() {
        return allBewoners;
    }

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

