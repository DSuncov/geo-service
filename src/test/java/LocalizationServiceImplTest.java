import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalizationServiceImplTest {

    @Test
    void shouldSuccessfulLocaleWithMockFirst() {
        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        String expected = "Добро пожаловать";
        assertEquals(expected, localizationService.locale(Country.RUSSIA));
    }

    @Test
    void shouldSuccessfulLocaleWithMockSecond() {
        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);

        String expected = "Welcome";
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");
        assertEquals(expected, localizationService.locale(Country.USA));
    }

    @Test
    void shouldSuccessfulLocaleWithSpy() {
        LocalizationServiceImpl localizationService = Mockito.spy(LocalizationServiceImpl.class);

        String expected = "Welcome";
        String actual = localizationService.locale(Country.USA);

        assertEquals(expected, actual);
    }
}
