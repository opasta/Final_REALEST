package nl.hu.bep.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Toebehoren {
    public static List<String> tempString = new ArrayList<>();
    public static List<Toebehoren> allToebehoren = new ArrayList<>();
    public static List<Toebehoren> ownToebehoren = new ArrayList<>();
    private int id;
    public int aquariumID;
    public String model;
    public String serienummer;
    private static int numCustomers = 0;

    public Toebehoren(int aquariumID, String model, String serienummer){
        id = ++numCustomers;
        this.aquariumID = aquariumID;
        this.model = model;
        this.serienummer = serienummer;
        allToebehoren.add(this);
    }

    public static Toebehoren createToebehoren(int aquariumID, String model, String serienummer){
        if (allToebehoren.stream().noneMatch(e->e.serienummer.equals(serienummer))) {
            return new Toebehoren(aquariumID, model, serienummer);
        }
        else return null;
    }

    public static List<Toebehoren> getAllEqualToUser(int ownerId) {
        ownToebehoren.clear();
        for (Toebehoren toebehoren : allToebehoren) {
            if (Aquarium.getById(toebehoren.aquariumID).ownerId == ownerId){
                System.out.println("Toebehoren " + toebehoren + " is mine");
                ownToebehoren.add(toebehoren);
            }else{
                System.out.println("I do not own" + toebehoren);
            }
        }
        return ownToebehoren;
    }

    public static Toebehoren getById(int id) {
        return allToebehoren.stream().filter(e->e.id==id).findFirst().orElse(null);
    }

    public static List<Toebehoren> getAll() {
        return allToebehoren;
    }

    public int getId(){
        return id;
    }

    public static boolean remove(int id){
        if (id>0) return allToebehoren.remove(allToebehoren.indexOf(Toebehoren.getById(id))) != null;
        return false;
    }


    @Override
    public String toString() {
        return "Toebehoren{" +
                "id=" + id +
                ", aquariumID=" + aquariumID +
                ", model='" + model + '\'' +
                ", serienummer='" + serienummer + '\'' +
                '}';
    }
}
