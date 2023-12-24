/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FlightSearchAPI.flightsearchapi.repository;

import FlightSearchAPI.flightsearchapi.entity.Airport;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> { // long or Integer think!
    
    List<Airport> findByCity(String city);// to find city according to Airport class
}
