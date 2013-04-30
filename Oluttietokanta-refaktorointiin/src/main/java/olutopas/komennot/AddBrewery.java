package olutopas.komennot;

import com.avaje.ebean.EbeanServer;
import java.util.Scanner;
import olutopas.domain.Datamapper;
import olutopas.model.Brewery;
import olutopas.model.User;

public class AddBrewery implements Komento {
    
    Datamapper mapper;
    Komentotehdas komennot;
    
    public AddBrewery(Datamapper mapper) {
        this.mapper = mapper;
        this.komennot = new Komentotehdas(mapper);
    }
    
    @Override
    public void run(Scanner scanner, EbeanServer server, User user) {
        System.out.print("brewery to add: ");
        String name = scanner.nextLine();
        Brewery brewery = server.find(Brewery.class).where().like("name", name).findUnique();

        if (brewery != null) {
            System.out.println(name + " already exists!");
            return;
        }

        server.save(new Brewery(name));
    }
    
}
