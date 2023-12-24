/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FlightSearchAPI.flightsearchapi.service;

import FlightSearchAPI.flightsearchapi.entity.Flight;
import FlightSearchAPI.flightsearchapi.repository.FlightRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    //CRUD methods 
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Optional<Flight> getFlightById(int id) {
        return flightRepository.findById(id);
    }

    public Flight saveFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public Flight updateFlight(int id, Flight updatedFlight) {
        if (flightRepository.existsById(id)) {
            updatedFlight.setId(id);
            return flightRepository.save(updatedFlight);
        }
        return null; // If the flight with the specified ID is not found, Null will return
    }

    public void deleteFlight(int id) {
        flightRepository.deleteById(id);
    }

    //One -way flight search
    public List<Flight> searchOneWayFlights(String departureCity, String arrivalCity, LocalDateTime departureDate) {
        List<Flight> allFlights = getAllFlights();
        List<Flight> foundFlights = new ArrayList<>();
        for (Flight flight : allFlights) {
            if (flight.getDepartureAirport().getCity().contains(departureCity)
                    && flight.getArrivalAirport().getCity().contains(arrivalCity)) {
                foundFlights.add(flight);
            }
        }
        return foundFlights;
    }

    //Round-Trip flight search
    public List<Flight> searchRoundTripFlights(String departureCity, String arrivalCity, LocalDateTime departureDate, LocalDateTime returnDate) {
        List<Flight> allFlights = getAllFlights();
        List<Flight> foundFlights = new ArrayList<>();
        for (Flight flight : allFlights) {
            if (flight.getDepartureAirport().getCity().contains(departureCity)
                    && flight.getArrivalAirport().getCity().contains(arrivalCity)) {
                foundFlights.add(flight);
            }
        }
        return foundFlights;
    }
}
