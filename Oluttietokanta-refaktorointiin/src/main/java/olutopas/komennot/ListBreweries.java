package olutopas.komennot;

import com.avaje.ebean.EbeanServer;
import java.util.List;
import java.util.Scanner;
import olutopas.domain.Datamapper;
import olutopas.model.Brewery;
import olutopas.model.User;

public class ListBreweries implements Komento {
    
    Datamapper mapper;
    Komentotehdas komennot;
    
    public ListBreweries(Datamapper mapper) {
        this.mapper = mapper;
        this.komennot = new Komentotehdas(mapper);
    }
    
    @Override
    public void run(Scanner scanner, EbeanServer server, User user) {
        List<Brewery> breweries = server.find(Brewery.class).findList();
        for (Brewery brewery : breweries) {
            System.out.println(brewery);
        }
    }
    
}
