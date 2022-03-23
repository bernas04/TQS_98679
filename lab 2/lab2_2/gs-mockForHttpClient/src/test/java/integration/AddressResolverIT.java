package integration;

import connection.TqsBasicHttpClient;
import geocoding.Address;
import geocoding.AddressResolver;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddressResolverIT {

    private AddressResolver resolver;


    @BeforeEach
    public void init(){
        resolver = new AddressResolver(new TqsBasicHttpClient());
    }

    @Test
    public void whenGoodCoordidates_returnAddress() throws IOException, URISyntaxException, ParseException {

        //todo
        Address answer = resolver.findAddressForLocation(40.640661, -8.656688);
        Address real_address = new Address("Cais do Alboi", "Glória e Vera Cruz", "Centro", "3800-246", null);
        // repeat the same tests conditions from AddressResolverTest, without mocks
        assertEquals(answer, real_address);

    }

    @Test
    public void whenBadCoordidates_thenReturnNoValidAddrress() throws IOException, URISyntaxException, ParseException {

        //todo
        // repeat the same tests conditions from AddressResolverTest, without mocks
        assertThrows(IndexOutOfBoundsException.class, () -> 
            resolver.findAddressForLocation(-300, -810)
        );
        
    }

}
