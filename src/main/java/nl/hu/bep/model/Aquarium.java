package nl.hu.bep.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Aquarium {
    public static List<Aquarium> allAquariums = new ArrayList<>();
    public static List<Aquarium> ownAquariums = new ArrayList<>();
    public int ownerId;
    public String name;
    public Double lengte;
    public Double breedte;
    public Double hoogte;
    public String bodemsoort;
    public String watersoort;
    private int id;
    private static int numCustomers = 0;

    public Aquarium(int ownerId, String name, Double lengte, Double breedte, Double hoogte, String bodemsoort, String watersoort){
        id = ++numCustomers;
        this.ownerId = ownerId;
        this.name = name;
        this.lengte = lengte;
        this.breedte = breedte;
        this.hoogte = hoogte;
        this.bodemsoort = bodemsoort;
        this.watersoort = watersoort;
        allAquariums.add(this);
    }

    public static Aquarium createAquarium(int ownerId, String name, Double lengte, Double breedte, Double hoogte, String bodemsoort, String watersoort){
        if (allAquariums.stream().noneMatch(e->e.name.equals(name))) {
            return new Aquarium(ownerId, name, lengte, breedte, hoogte, bodemsoort, watersoort);
        }
       else return null;
   }

    public static Aquarium getById(int id) {
        return allAquariums.stream().filter(e->e.id==id).findFirst().orElse(null);
    }

    public static List<Aquarium> getAll() {
        return allAquariums;
    }

    public static boolean remove(int id){
        if (id>0) return allAquariums.remove(allAquariums.indexOf(Aquarium.getById(id))) != null;
        return false;
    }

    public static List<Aquarium> getAllEqualToUser(int ownerId) {
        ownAquariums.clear();
        for (Aquarium aquarium : allAquariums) {
            if (aquarium.ownerId == ownerId){
                System.out.println("Aquarium " + aquarium + " is mine");
                ownAquariums.add(aquarium);
            }else{
                System.out.println("I do not own" + aquarium);
            }
        }
        return ownAquariums;
    }

    @Override
    public String toString() {
        return "Aquarium{" +
                "ownerId=" + ownerId +
                ", name='" + name + '\'' +
                ", lengte=" + lengte +
                ", breedte=" + breedte +
                ", hoogte=" + hoogte +
                ", bodemsoort='" + bodemsoort + '\'' +
                ", watersoort='" + watersoort + '\'' +
                ", id=" + id +
                '}';
    }
}
