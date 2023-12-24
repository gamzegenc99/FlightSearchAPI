package FlightSearchAPI.flightsearchapi;

import FlightSearchAPI.flightsearchapi.entity.Airport;
import FlightSearchAPI.flightsearchapi.entity.Flight;
import FlightSearchAPI.flightsearchapi.repository.AirportRepository;
import FlightSearchAPI.flightsearchapi.repository.FlightRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author gamze
 */
@Component
public class StartupRunner implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(StartupRunner.class);

    @Autowired
    private AirportRepository airportRepository;
    @Autowired
    private FlightRepository flightRepository;

    @Override
    public void run(String... args) throws Exception {
        long count = airportRepository.count();
        if (count == 0) {
            LOG.info("No airport defined in db predefined airports will be persisted.");
            airportRepository.saveAll(Constants.PREDEFINED_AIRPORTS);
            LOG.info("All predefined airports persisted!");
        } else {
            LOG.info("In DB predefined airports existed!");
        }

        List<Airport> allAirports = airportRepository.findAll();
        allAirports.forEach((airport) -> {
            Constants.CACHED_AIRPORTS.put(airport.getId(), airport);
        });

        // ScheduledExecutorService oluşturuluyor
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Her gün belirli bir saatte job'u çalıştırmak için bir Runnable oluşturuluyor
        Runnable job = new FlightDataFetchJob();

        // Başlangıç gecikmesi: Şu anda başlaması için 0, bir gün gecikme için 24L * 60L * 60L * 1000L
        long initialDelay = 0;

        // Her gün belirli bir saatte çalışması için periyot belirleniyor
        //long period = 24L * 60L * 60L * 1000L; // 24 saat
        long period = 60L * 1000L; // 60 saniye

        // Job'u belirlenen periyot ve gecikmeyle çalıştırmak için schedule ediliyor
        scheduler.scheduleAtFixedRate(job, initialDelay, period, TimeUnit.MILLISECONDS);

    }

    class FlightDataFetchJob implements Runnable {

        @Override
        public void run() {
            // Mock API isteği yapılıyor ve uçuş bilgileri alınıyor
            Flight mockApiResponse = MockFlightApiRequest.getFlightData();

            // Alınan uçuş bilgileri veritabanına kaydediliyor (Bu kısmı gerçek bir veritabanına kaydetmek için uyarlamanız gerekebilir)
            saveFlightDataToDatabase(mockApiResponse);
            LOG.info("Flight data fetched and saved to database.");
        }

        private void saveFlightDataToDatabase(Flight flight) {
            // Bu kısım, alınan uçuş bilgilerini gerçek bir veritabanına kaydetmek için kullanılıyor
            flightRepository.save(flight);
            // Bu örnek sadece basit bir çıktı yazdırma işlemi içermektedir
            LOG.info("Saving flight data to database: " + flight);
        }
    }

    class MockFlightApiRequest {
        // Mock bir API isteği yaparak yapay veri üretiyoruz

        public static Flight getFlightData() {
            Airport departure = getRandomValue(Constants.CACHED_AIRPORTS);
            Airport arrival = getRandomValue(Constants.CACHED_AIRPORTS);

            Flight flight = new Flight();
            flight.setArrivalAirport(arrival);
            flight.setDepartureAirport(departure);
            flight.setArrivalDateTime(new Date());
            flight.setDepartureDateTime(new Date());
            flight.setPrice(new BigDecimal(100));

            // Burada gerçek bir API isteği yapılabilir, ancak bu örnekte mock bir veri döndürüyoruz
            return flight;
        }

        private static <K, V> V getRandomValue(Map<K, V> map) {
            // Convert values to a List
            List<V> valuesList = new ArrayList<>(map.values());

            // Get a random index
            Random random = new Random();
            int randomIndex = random.nextInt(valuesList.size());

            // Return the random value
            return valuesList.get(randomIndex);
        }
    }
}
