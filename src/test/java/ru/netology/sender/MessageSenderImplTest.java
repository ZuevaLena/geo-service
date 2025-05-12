package ru.netology.sender;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageSenderImplTest {

    @Mock
    private GeoService geoService;

    @Mock
    private LocalizationService localizationService;



    @Test
    void send_RussianIp_ReturnsRussianText(){
        String russianIp = "172.123.12.19";
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, russianIp);


        when(geoService.byIp(russianIp))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        String result = messageSender.send(headers);

        assertEquals("Добро пожаловать", result);
        verify(geoService, times(1)).byIp(russianIp);
        verify(localizationService, times(2)).locale(Country.RUSSIA);
    }

    @Test
    void send_AmericanIp_ReturnsEnglishText() {
        String americanIp = "96.44.183.149";
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, americanIp);


        when(geoService.byIp(americanIp))
                .thenReturn(new Location("New York", Country.USA, null, 0));
        when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        String result = messageSender.send(headers);

        assertEquals("Welcome", result);
        verify(geoService, times(1)).byIp(americanIp);
        verify(localizationService, times(2)).locale(Country.USA);
    }
}
