/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FlightSearchAPI.flightsearchapi.service;

import FlightSearchAPI.flightsearchapi.entity.Flight;
import FlightSearchAPI.flightsearchapi.repository.FlightRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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

    public List<Flight> searchOneWayFlights(String departureCity, String arrivalCity, LocalDateTime departureDate) {
        LocalDateTime departureDayStart = departureDate.toLocalDate().atStartOfDay(); // start of the departure day
        LocalDateTime departureDayEnd = departureDate.toLocalDate().atTime(23, 59, 59); // end of the departure day

        return flightRepository.findByDepartureAirportCityAndArrivalAirportCityAndDepartureDateTimeBetween(
                departureCity, arrivalCity, departureDayStart, departureDayEnd);
    }

    public List<Flight> searchRoundTripFlights(String departureCity, String arrivalCity, LocalDateTime departureDate, LocalDateTime returnDate) {
        LocalDateTime departureDayStart = departureDate.toLocalDate().atStartOfDay(); // start of the departure day
        LocalDateTime departureDayEnd = departureDate.toLocalDate().atTime(23, 59, 59); // end of the departure day
        LocalDateTime returnDayStart = returnDate.toLocalDate().atStartOfDay(); // start of the return day
        LocalDateTime returnDayEnd = returnDate.toLocalDate().atTime(23, 59, 59); // end of the return day

        List<Flight> foundFlights = new ArrayList<>();
        foundFlights.addAll(flightRepository.findByDepartureAirportCityAndArrivalAirportCityAndDepartureDateTimeBetween(departureCity, arrivalCity, departureDayStart, departureDayEnd));
        foundFlights.addAll(flightRepository.findByDepartureAirportCityAndArrivalAirportCityAndDepartureDateTimeBetween(arrivalCity, departureCity, returnDayStart, returnDayEnd));
        return foundFlights;
    }

}
