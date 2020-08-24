package nl.hu.bep.listener;

import nl.hu.bep.model.*;
import nl.hu.bep.persistence.PersistenceManager;
import reactor.core.scheduler.Schedulers;
import reactor.netty.http.HttpResources;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.time.Duration;


@WebListener
public class BootUpListen implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce){
 //       try {
 //           PersistenceManager.loadWorldFromAzure();
 //           System.out.println("World loaded!");
 //       } catch (Exception e){
 //           System.out.println("Cannot load world");
 //           e.printStackTrace();
  //      }

        System.out.println("Applicatie is aan het opstarten!");

        Customer.createCustomer("Maikol");
        Customer.createCustomer("Henk-Dirk");
        Customer.createCustomer("Sjonnie");
        MyUser.createUser("Sjon", "ditiseenTest", "Sjon","Van De Buurt");
        new MyUser("Michel", "1234", "Michel", "Kastelein");
        new MyUser("Henk", "Maikol01997", "Henk", "Smikkel").setAdmin();
        new Aquarium(1,"hagedisss",3.0,4.0,5.0,"gras","Zout");

        new Aquarium(3,"Smikkelbeer",3.0,4.0,5.0,"gras","Zout");
        new Aquarium(3,"KikkerAapjes",3.0,4.0,5.0,"gras","Zout");
        Aquarium.createAquarium(3, "Stinksokjee", 1.0, 1.0, 2.0, "water", "zoet");
        System.out.println(Aquarium.allAquariums);


        // add 2 users
        MyUser.allMyUsers.add(new MyUser("CN", "CHN", "China", "Beijing"));
        MyUser.allMyUsers.add(new MyUser("test", "test", "Test", "test"));


        //add Toebehoren
        Toebehoren.allToebehoren.add(new Toebehoren(2, "Groot", "1234AAA"));
        Toebehoren.allToebehoren.add(new Toebehoren(3, "Kippenhokje", "12224325"));
        Toebehoren.allToebehoren.add(new Toebehoren(3, "Mooi", "Geen serienummer"));

        // add Bewoners
    //    Bewoner.allBewoners.add(new Bewoner(1, "Kippen", "Groen", 1));
       // Bewoner.allBewoners.add(new Bewoner(2, "Padden", "Blauw met Oranje", 3));
       // Bewoner.allBewoners.add(new Bewoner(1, "Eend", "Geel", 22));
       // Bewoner.allBewoners.add(new Bewoner(3, "Stinksok", "Paars", 1));

        Bewoner.createBewoner(3, "Stinksokjee", "Paars", 1);
        Bewoner.createBewoner(1, "Kippen", "Groen", 1);
        Bewoner.createBewoner(2, "Padden", "Blauw met Oranje", 3);
            Bewoner.createBewoner(1, "Eend", "Geel", 22);
            Bewoner.createBewoner(3, "Stinksok", "Paars", 1);





    }

    public void contextDestroyed(ServletContextEvent sce){
    //    try {
    //        PersistenceManager.saveWorldToAzure();
   //         System.out.println("World Saved!");
   //     } catch (IOException ioe){
   //         System.out.println("Failed to save the world");
    //        ioe.printStackTrace();
    //    }
    //    Schedulers.shutdownNow();
    //    HttpResources.disposeLoopsAndConnectionsLater(Duration.ZERO, Duration.ZERO).block();

        System.out.println("Applicatie wordt afgesloten!");
    }
}
