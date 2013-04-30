package olutopas.komennot;

import com.avaje.ebean.EbeanServer;
import java.util.Scanner;
import olutopas.domain.Datamapper;
import olutopas.model.Beer;
import olutopas.model.Brewery;
import olutopas.model.User;

public class AddBeer implements Komento {
    
    Datamapper mapper;
    Komentotehdas komennot;
    
    public AddBeer(Datamapper mapper) {
        this.mapper = mapper;
        this.komennot = new Komentotehdas(mapper);
    }
    
    @Override
    public void run(Scanner scanner, EbeanServer server, User user) {
        System.out.print("to which brewery: ");
        String name = scanner.nextLine();
        Brewery brewery = server.find(Brewery.class).where().like("name", name).findUnique();

        if (brewery == null) {
            System.out.println(name + " does not exist");
            return;
        }

        System.out.print("beer to add: ");

        name = scanner.nextLine();

        Beer exists = server.find(Beer.class).where().like("name", name).findUnique();
        if (exists != null) {
            System.out.println(name + " exists already");
            return;
        }

        brewery.addBeer(new Beer(name));
        server.save(brewery);
        System.out.println(name + " added to " + brewery.getName());
    }
    
}
