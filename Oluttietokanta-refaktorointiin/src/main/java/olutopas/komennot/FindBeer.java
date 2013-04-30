package olutopas.komennot;

import com.avaje.ebean.EbeanServer;
import java.util.Scanner;
import olutopas.domain.Datamapper;
import olutopas.model.Beer;
import olutopas.model.Rating;
import olutopas.model.User;

public class FindBeer implements Komento {

    Datamapper mapper;
    private Komentotehdas komennot;

    public FindBeer(Datamapper mapper) {
        this.mapper = mapper;
        this.komennot = new Komentotehdas(mapper);
    }

    @Override
    public void run(Scanner scanner, EbeanServer server, User user) {
        System.out.print("beer to find: ");
        String n = scanner.nextLine();
        Beer foundBeer = server.find(Beer.class).where().like("name", n).findUnique();

        if (foundBeer == null) {
            System.out.println(n + " not found");
            return;
        }

        System.out.println(foundBeer);

        if (foundBeer.getRatings() != null && foundBeer.getRatings().size() != 0) {
            System.out.println("  number of ratings: " + foundBeer.getRatings().size() + " average " + foundBeer.averageRating());
        } else {
            System.out.println("no ratings");
        }

        System.out.print("give rating (leave emtpy if not): ");
        try {
            int rating = Integer.parseInt(scanner.nextLine());
            //komennot.getKomento(7).run(scanner, server, user);
            addRating(foundBeer, rating, user, server);
        } catch (Exception e) {
        }
    }
    
    private void addRating(Beer foundBeer, int value, User user, EbeanServer server) {
        Rating rating = new Rating(foundBeer, user, value);
        server.save(rating);
    }
    
}
