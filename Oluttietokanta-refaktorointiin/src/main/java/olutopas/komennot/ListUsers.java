package olutopas.komennot;

import com.avaje.ebean.EbeanServer;
import java.util.List;
import java.util.Scanner;
import olutopas.domain.Datamapper;
import olutopas.model.User;

public class ListUsers implements Komento {
    
    Datamapper mapper;
    Komentotehdas komennot;
    
    public ListUsers(Datamapper mapper) {
        this.mapper = mapper;
        this.komennot = new Komentotehdas(mapper);
    }
    
    @Override
    public void run(Scanner scanner, EbeanServer server, User user) {
        List<User> users = server.find(User.class).findList();
        for (User kayttaja : users) {
            System.out.println(kayttaja.getName() + " " + kayttaja.getRatings().size() + " ratings");
        }
    }
    
}
