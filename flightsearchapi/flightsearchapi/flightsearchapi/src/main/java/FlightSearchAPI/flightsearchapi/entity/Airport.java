package FlightSearchAPI.flightsearchapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

@Entity
public class Airport implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String city;

    public Airport() {
    }

    
    
    public Airport(String city) {
        this.city = city;
    }

    //getter setter methods 
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FlightSearchAPI.flightsearchapi.entity.Airport[ id=" + id + ", city=" + city + " ]";
    }

}
