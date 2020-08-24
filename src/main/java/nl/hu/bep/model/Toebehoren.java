package nl.hu.bep.model;

import java.util.ArrayList;
import java.util.List;

public class Toebehoren {
    public static List<String> tempString = new ArrayList<>();
    public static List<Toebehoren> allToebehoren = new ArrayList<>();
    public static List<Toebehoren> ownToebehoren = new ArrayList<>();
    private int id;
    public int aquariumID;
    public String model;
    public String serienummer;
    private static int numCustomers = 0;

    public Toebehoren(int aquaID, String md, String sn){
        id = ++numCustomers;
        aquariumID = aquaID;
        model = md;
        serienummer = sn;
        allToebehoren.add(this);
    }

    public static List<Toebehoren> getAllToebehoren() {
        return allToebehoren;
    }

    public static Toebehoren createToebehoren(int aquaID, String md, String sn){
        if (allToebehoren.stream().noneMatch(e->e.getSerienummer().equals(sn))) {
            Toebehoren newToebehoren = new Toebehoren(aquaID, md, sn);
            return newToebehoren;
        }
        else return null;
    }

    public static List<Toebehoren> gettAllToebehorenEqualToAquarium(int user) {
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

        for (int i=0 ; i < allToebehoren.size(); i++) {
            if(tempString.contains(allToebehoren.get(i).toString())){
                if(ownToebehoren.contains(allToebehoren.get(i))){
                    System.out.println("Already added");
                }else{
                    System.out.println("Adding to own");
                    ownToebehoren.add(allToebehoren.get(i));
                }
            }
        }
        return ownToebehoren;
    }

    public static Toebehoren getBToebehorenById(int id) {
        return allToebehoren.stream().filter(e->e.id==id).findFirst().orElse(null);
    }

    public int getId(){
        return id;
    }

    public String toString() {
        return ""+ aquariumID;
    }

    public String getSerienummer() {
        return serienummer;
    }
}
