package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;


class MessageSenderTest {
    static MessageSenderImpl messageSender;

    @BeforeAll
    static void init() {
        GeoServiceImpl geoService = Mockito.spy(GeoServiceImpl.class);
        Mockito.when(geoService.byIp("172.0.32.11")).thenReturn(
                new Location("Moscow", Country.RUSSIA, "Lenina", 15));
        Mockito.when(geoService.byIp("96.44.183.149")).thenReturn(
                new Location("New York", Country.USA, " 10th Avenue", 32));
        Mockito.when(geoService.byIp("172.")).thenReturn(
                new Location("Moscow", Country.RUSSIA, null, 0));
        Mockito.when(geoService.byIp("96.")).thenReturn(
                new Location("New York", Country.USA, null, 0));

        LocalizationServiceImpl localizationService = Mockito.spy(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        messageSender = new MessageSenderImpl(geoService, localizationService);

    }

    @ParameterizedTest
    @CsvSource({
            "172.123.12.19, Добро пожаловать",
            "96.44.183.149, Welcome"
    })
    void testIpTextLocalizationUsa(String ip, String msg) {

        Map<String, String> map = new HashMap<String, String>();
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        String result = messageSender.send(map);
        String expected = msg;
        Assertions.assertEquals(expected, result);
    }
}