package olutopas.komennot;

import com.avaje.ebean.EbeanServer;
import java.util.Scanner;
import olutopas.domain.Datamapper;
import olutopas.model.Rating;
import olutopas.model.User;

public class MyRatings implements Komento {
    
    Datamapper mapper;
    Komentotehdas komennot;
    
    public MyRatings(Datamapper mapper) {
        this.mapper = mapper;
        this.komennot = new Komentotehdas(mapper);
    }
    
    @Override
    public void run(Scanner scanner, EbeanServer server, User user) {
        System.out.println("Ratings by " + user.getName());
        for (Rating rating : user.getRatings()) {
            System.out.println(rating);
        }
    }
    
}
