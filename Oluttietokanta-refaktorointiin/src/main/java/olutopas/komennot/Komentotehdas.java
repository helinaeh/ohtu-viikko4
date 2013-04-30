package olutopas.komennot;

import java.util.ArrayList;
import java.util.List;
import olutopas.domain.Datamapper;


public class Komentotehdas {
    private ArrayList<Komento> komennot;
    
    public Komentotehdas(Datamapper dataMapper) {
        komennot = new ArrayList<Komento>();
        komennot.add(new AddBeer(dataMapper));          //0
        komennot.add(new AddBrewery(dataMapper));       //1
        komennot.add(new FindBeer(dataMapper));         //2
        komennot.add(new FindBrewery(dataMapper));      //3
        komennot.add(new ListBeers(dataMapper));        //4
        komennot.add(new ListBreweries(dataMapper));    //5
        komennot.add(new ListUsers(dataMapper));        //6
        komennot.add(new MyRatings(dataMapper));        //7
    }
    
    public Komento getKomento(int i) {
        return komennot.get(i);
    }
    
    public List getKomennot() {
        return komennot;
    }
}