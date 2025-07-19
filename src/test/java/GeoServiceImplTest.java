import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeoServiceImplTest {

    @Test
    void shouldSuccessfulByIpWithMockFirst() {
        GeoServiceImpl geoServiceImpl = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoServiceImpl.byIp("172.")).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        Country expected = Country.RUSSIA;
        assertEquals(expected, geoServiceImpl.byIp("172.").getCountry());
    }

    @Test
    void shouldSuccessfulByIpWithMockSecond() {
        GeoServiceImpl geoServiceImpl = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoServiceImpl.byIp("96.")).thenReturn(new Location("New York", Country.USA, null,  0));

        Country expected = Country.USA;
        assertEquals(expected, geoServiceImpl.byIp("96.").getCountry());
    }

    @Test
    void shouldSuccessfulByIpWithMockThird() {
        GeoServiceImpl geoServiceImpl = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoServiceImpl.byIp("96.")).thenReturn(new Location("New York", Country.USA, null,  0));

        String expected = "New York";
        assertEquals(expected, geoServiceImpl.byIp("96.").getCity());
    }

    @Test
    void shouldSuccessfulByIpWithMockFourth() {
        GeoServiceImpl geoServiceImpl = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoServiceImpl.byIp("96.")).thenReturn(new Location("New York", Country.USA, null,  0));

        int expected = 0;
        assertEquals(expected, geoServiceImpl.byIp("96.").getBuiling());
    }

    @Test
    void shouldSuccessfulByIpWithSpy() {
        GeoServiceImpl geoServiceImpl = Mockito.spy(GeoServiceImpl.class);

        Country expected = Country.USA;
        Country actual = geoServiceImpl.byIp("96.").getCountry();

        assertEquals(expected, actual);
    }
}
