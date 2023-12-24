package FlightSearchAPI.flightsearchapi.security;

/**
 *
 * @author gamze
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private static final Map<String, User> USERS_IN_MEMORY_DB = new HashMap<>();

    static {
        USERS_IN_MEMORY_DB.put("username", new User("username", "password", new ArrayList<>()));
        USERS_IN_MEMORY_DB.put("username2", new User("username2", "password", new ArrayList<>()));
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Here, retrieve your user from the database
        User user = USERS_IN_MEMORY_DB.get(username);
        if (user == null) {
            return null;
        }
        return user;
    }
}
