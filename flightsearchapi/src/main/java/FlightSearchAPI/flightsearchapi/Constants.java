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
        PREDEFINED_AIRPORTS.add(new Airport("Hatay"));
        PREDEFINED_AIRPORTS.add(new Airport("Adana"));
        PREDEFINED_AIRPORTS.add(new Airport("Mugla"));
        PREDEFINED_AIRPORTS.add(new Airport("İstanbul"));
        PREDEFINED_AIRPORTS.add(new Airport("İzmir"));
        PREDEFINED_AIRPORTS.add(new Airport("Ankara"));
        PREDEFINED_AIRPORTS.add(new Airport("Malatya"));
        PREDEFINED_AIRPORTS.add(new Airport("Frankfurt"));
        PREDEFINED_AIRPORTS.add(new Airport("Köln"));
    }
}
