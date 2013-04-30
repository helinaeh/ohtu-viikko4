package olutopas.komennot;

import com.avaje.ebean.EbeanServer;
import java.util.List;
import java.util.Scanner;
import olutopas.domain.Datamapper;
import olutopas.model.Beer;
import olutopas.model.User;

public class ListBeers implements Komento {
    
    Datamapper mapper;
    Komentotehdas komennot;
    
    public ListBeers(Datamapper mapper) {
        this.mapper = mapper;
        this.komennot = new Komentotehdas(mapper);
    }
    
    @Override
    public void run(Scanner scanner, EbeanServer server, User user) {
        List<Beer> beers = server.find(Beer.class).orderBy("brewery.name").findList();
        for (Beer beer : beers) {
            System.out.println(beer);
            if (beer.getRatings() != null && beer.getRatings().size() != 0) {
                System.out.println("  ratings given " + beer.getRatings().size() + " average " + beer.averageRating());
            } else {
                System.out.println("  no ratings");
            }
        }
    }
    
}
