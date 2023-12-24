package FlightSearchAPI.flightsearchapi.service;

import FlightSearchAPI.flightsearchapi.entity.Airport;
import FlightSearchAPI.flightsearchapi.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;

    //CRUD methods 
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    public Optional<Airport> getAirportById(Long id) {
        return airportRepository.findById(id);
    }

    public Airport saveAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    public Airport updateAirport(Long id, Airport updatedAirport) {
        if (airportRepository.existsById(id)) {
            updatedAirport.setId(id);
            return airportRepository.save(updatedAirport);
        }
        return null; // If the flight with the specified ID is not found, Null will return
    }

    public void deleteAirport(Long id) {
        airportRepository.deleteById(id);
    }
}
