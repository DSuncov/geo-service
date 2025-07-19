import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageSenderTest {

    @Test
    void testSendShouldSuccessfulFirst() {
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("172.")).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        String expected = "Добро пожаловать";

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String > headersTest = new HashMap<>();
        headersTest.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.");
        assertEquals(expected, messageSender.send(headersTest));
    }

    @Test
    void testSendShouldSuccessfulSecond() {
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("96.")).thenReturn(new Location("New York", Country.USA, null,  0));

        String expected = "Welcome";

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String > headersTest = new HashMap<>();
        headersTest.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.");
        assertEquals(expected, messageSender.send(headersTest));
    }

    @Test
    void testSendShouldSuccessful() {
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("96.")).thenReturn(new Location("New York", Country.USA, null,  0));

        String expected = "Welcome";

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String > headersTest = new HashMap<>();
        headersTest.put(MessageSenderImpl.IP_ADDRESS_HEADER, ""); // ip-адрес пустой
        assertEquals(expected, messageSender.send(headersTest)); // метод send() должен вернуть Welcome
    }
}
