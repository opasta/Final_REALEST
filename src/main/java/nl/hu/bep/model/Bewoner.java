package nl.hu.bep.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
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

    public Bewoner(int aquariumID, String soortnaam, String kleurnaam, int aantal){
        id = ++numCustomers;
        this.aquariumID = aquariumID;
        this.soortnaam = soortnaam;
        this.kleurnaam = kleurnaam;
        this.aantal = aantal;
        if (aantal > 1) {
            groepsdier = "Ja";
        }
        allBewoners.add(this);
    }

    public static Bewoner createBewoner(int aquariumID, String soortnaam, String kleurnaam, int aantal){
        if (allBewoners.stream().noneMatch(e->e.soortnaam.equals(soortnaam))) {
            return new Bewoner(aquariumID, soortnaam, kleurnaam, aantal);
        }
        else return null;
    }

    public static List<Bewoner> getAllEqualToUser(int ownerId) {
        ownBewoners.clear();
        for (Bewoner bewoner : allBewoners) {
            if (Aquarium.getById(bewoner.aquariumID).ownerId == ownerId){
                System.out.println("Bewoner " + bewoner + " is mine");
                ownBewoners.add(bewoner);
            }else{
                System.out.println("I do not own" + bewoner);
            }
        }
        return ownBewoners;
    }

    public static Bewoner getById(int id) {
        return allBewoners.stream().filter(e->e.id==id).findFirst().orElse(null);
    }

    public static List<Bewoner> getAll() {
        return allBewoners;
    }

    public int getId(){
        return id;
    }

    public static boolean remove(int id){
        if (id>0) return allBewoners.remove(allBewoners.indexOf(Bewoner.getById(id))) != null;
        return false;
    }

    @Override
    public String toString() {
        return "Bewoner{" +
                "id=" + id +
                ", aquariumID=" + aquariumID +
                ", soortnaam='" + soortnaam + '\'' +
                ", kleurnaam='" + kleurnaam + '\'' +
                ", aantal=" + aantal +
                ", groepsdier='" + groepsdier + '\'' +
                '}';
    }
}

