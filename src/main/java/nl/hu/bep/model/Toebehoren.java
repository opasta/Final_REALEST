package nl.hu.bep.model;

import java.util.ArrayList;
import java.util.List;

public class Toebehoren {
    public static List<Toebehoren> allToebehoren = new ArrayList<>();
    private int id;
    public int aquariumID;
    public String model;
    public String Serienummer;
    private static int numCustomers = 0;

    public Toebehoren(int aquaID, String md, String sn){
        id = ++numCustomers;
        aquariumID = aquaID;
        model = md;
        Serienummer = sn;
    }

    public static List<Toebehoren> getAllToebehoren() {
        return allToebehoren;
    }



  //  @Override

    public int getId(){
        return id;
    }

  //  public String toString() {
  //      return ""+ id;
 //   }


}
