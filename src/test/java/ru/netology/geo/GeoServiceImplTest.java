package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


class GeoServiceImplTest {

    static GeoServiceImpl geoService;

    @BeforeAll
    static void init() {
        geoService = new GeoServiceImpl();
    }

    @Test
    void byIpRu() {
        String expected = "Moscow";
        String actual = geoService.byIp(GeoServiceImpl.MOSCOW_IP).getCity();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void byIpUsa() {
        String expected = "New York";
        String actual = geoService.byIp(GeoServiceImpl.NEW_YORK_IP).getCity();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void byIpRu_() {
        String expected = "Moscow";
        String actual = geoService.byIp("172.").getCity();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void byIpUsa_() {
        String expected = "New York";
        String actual = geoService.byIp("96.").getCity();
        Assertions.assertEquals(expected, actual);
    }


}