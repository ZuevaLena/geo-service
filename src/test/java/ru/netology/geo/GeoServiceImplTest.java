package ru.netology.geo;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GeoServiceImplTest {

    @Test
    void byIp_RussianIp_ReturnsRussianLocation() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        String russianIp = "172.0.32.11";

        Location location =geoService.byIp(russianIp);

        assertNotNull(location);
        assertEquals("Moscow", location.getCity());
        assertEquals(Country.RUSSIA, location.getCountry());
    }

    @Test
    void byIp_AmericanIp_ReturnsAmericanLocation() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        String americanIp = "96.44.183.149";

        Location location = geoService.byIp(americanIp);

        assertNotNull(location);
        assertEquals("New York", location.getCity());
        assertEquals(Country.USA,location.getCountry());
    }
}
