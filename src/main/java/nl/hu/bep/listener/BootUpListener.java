package nl.hu.bep.listener;

import nl.hu.bep.model.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class BootUpListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce){

        System.out.println("Applicatie is aan het opstarten!");

        //Create Users
        User.createUser("Sjon", "ditiseenTest", "Sjon","Van De Buurt");
        User.createUser("Michel", "Password", "Michel","Kastelein");
        User.createUser("Henk", "Maikol01997", "Henk","Kastelein").setAdmin();
        User.createUser("Bob", "mijnnaamisbob", "Bob","Dauwe");
        User.createUser("root", "usbw", "SystemTester","Mooiman");


        //Create Aquariums
        Aquarium.createAquarium(1, "XXL", 1.0, 1.0, 2.0, "Zand", "zoet");
        Aquarium.createAquarium(3, "Klein", 3.0, 1.5, 6.0, "Bladeren", "zoet");
        Aquarium.createAquarium(2, "Groot", 1.0, 1.0, 2.0, "water", "Zout");
        Aquarium.createAquarium(3, "Middel", 1.0, 1.0, 2.0, "gras", "zoet");

        //Create Toebehoren
        Toebehoren.createToebehoren(2, "Lichtunit", "1234AAA");
        Toebehoren.createToebehoren(3, "Waterfles", "298478");
        Toebehoren.createToebehoren(3, "Mooi", "Geen serienummer");

        //Create Bewoners
        Bewoner.createBewoner(3, "Hagedis", "Bruin", 1);
        Bewoner.createBewoner(1, "Wandelende Tak", "Groen", 24);
        Bewoner.createBewoner(2, "Padden", "Blauw met Oranje", 3);
        Bewoner.createBewoner(1, "Schildpad", "Groen", 15);
        Bewoner.createBewoner(3, "Egel", "Bruin", 1);

    }

    public void contextDestroyed(ServletContextEvent sce){

        System.out.println("Applicatie wordt afgesloten!");
    }
}
