/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FlightSearchAPI.flightsearchapi.controller;

import FlightSearchAPI.flightsearchapi.entity.Flight;
import FlightSearchAPI.flightsearchapi.service.FlightService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlightSearchController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/health")
    public String health() {
        return "true";
    }

    @GetMapping("/search")
    public List<Flight> searchFlights(@RequestParam String departureCity,
            @RequestParam String arrivalCity,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime returnDate) {
        // Uçuşları arama ve liste alma işlemleri buraya eklenecek
        if (returnDate == null) {
            // one way flights
            return flightService.searchOneWayFlights(departureCity, arrivalCity, departureDate);
        } else {
            // Round-Trip flights
            return flightService.searchRoundTripFlights(departureCity, arrivalCity, departureDate, returnDate);
        }
    }

    // Diğer endpoint'ler buraya 
}
