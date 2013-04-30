package olutopas.komennot;

import com.avaje.ebean.EbeanServer;
import java.util.Scanner;
import olutopas.model.User;

public interface Komento {
    
    public void run(Scanner scanner, EbeanServer server, User user);
    
}
