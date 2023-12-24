package FlightSearchAPI.flightsearchapi.repository;


import FlightSearchAPI.flightsearchapi.entity.Airport;
import FlightSearchAPI.flightsearchapi.entity.Flight;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
    
    //Belirli bir şehirden (kalkış havaalanları), belirli bir şehre (varış havaalanları) ve belirli bir tarih aralığına göre uçuşları sorgulaması
    List<Flight> findByDepartureAirportInAndArrivalAirportInAndDepartureDateTimeBetween(
    List<Airport> departureAirport, List<Airport> arrivalAirport,
    LocalDateTime departureDateTimeBegin, LocalDateTime departureDatetimeEnd);
}