package FlightSearchAPI.flightsearchapi.security;

/**
 *
 * @author gamze
 */
public class AuthenticationResponse {

    private String jwt;

    // Default constructor for JSON Parsing
    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String jwt) {
        setJwt(jwt);
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
