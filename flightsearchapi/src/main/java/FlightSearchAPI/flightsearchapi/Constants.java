package FlightSearchAPI.flightsearchapi;

import FlightSearchAPI.flightsearchapi.entity.Airport;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author gamze
 */
public class Constants {

    public static final Set<Airport> PREDEFINED_AIRPORTS = new HashSet<>();
    public static final Map<Long, Airport> CACHED_AIRPORTS = new HashMap<>();

    static {
        PREDEFINED_AIRPORTS.add(new Airport("Hatay Havalimanı"));
        PREDEFINED_AIRPORTS.add(new Airport("Adana Havalimanı"));
        PREDEFINED_AIRPORTS.add(new Airport("Sabiha Gökçen Havalimanı"));
        PREDEFINED_AIRPORTS.add(new Airport("İstanbul Havalimanı"));
        PREDEFINED_AIRPORTS.add(new Airport("İzmir Havalimanı"));
        PREDEFINED_AIRPORTS.add(new Airport("Esenboğa Havalimanı"));
        PREDEFINED_AIRPORTS.add(new Airport("Malatya Havalimanı"));
        PREDEFINED_AIRPORTS.add(new Airport("Frankfurt Havalimanı"));
        PREDEFINED_AIRPORTS.add(new Airport("Köln Havalimanı"));
    }
}
