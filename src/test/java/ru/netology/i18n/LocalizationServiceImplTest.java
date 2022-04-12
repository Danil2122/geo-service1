package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

    static LocalizationServiceImpl localizationService;

    @BeforeAll
    static void init() {
        localizationService = new LocalizationServiceImpl();
    }

    @Test
    void localeRu() {
        String expected = "Добро пожаловать";
        String actual = localizationService.locale(Country.RUSSIA);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void localeUsa() {
        String expected = "Welcome";
        String actual = localizationService.locale(Country.USA);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void localeNotEquals() {
        String expected = "Добро пожаловать";
        String actual = localizationService.locale(Country.USA);
        Assertions.assertNotEquals(expected, actual);
    }
}