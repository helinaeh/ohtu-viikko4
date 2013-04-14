package olutopas.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import olutopas.model.Beer;
import olutopas.model.Brewery;

@Entity
public class User {
    
    private String name;
    
    @Id
    private Integer id;
    
    public User() {
    }
    
    public User(String name) {
        this.name = name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return getName();
    }
    
}
