package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import ru.netology.entity.Location;


public class GeoServiceImpTestByCoordinates {
    static GeoServiceImpl geoService;

    @Test
    public void byCoordinatesTest() {
        geoService = new GeoServiceImpl();
        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class,
                () -> {
                    Location location = geoService.byCoordinates(22, 55);
                });
        Assertions.assertEquals("Not implemented", thrown.getMessage());
    }
}
