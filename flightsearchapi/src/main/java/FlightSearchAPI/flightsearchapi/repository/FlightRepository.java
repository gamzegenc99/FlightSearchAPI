package FlightSearchAPI.flightsearchapi.repository;


import FlightSearchAPI.flightsearchapi.entity.Airport;
import FlightSearchAPI.flightsearchapi.entity.Flight;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
    
    // Method to find flights for the entire departure day
    List<Flight> findByDepartureAirportCityAndArrivalAirportCityAndDepartureDateTimeBetween(
        String departureCity, String arrivalCity, LocalDateTime departureDayStart, LocalDateTime departureDayEnd);

}