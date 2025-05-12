package ru.netology.i18n;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocalizationServiceImplTest {

    @Test
    void locale_Russian_ReturnsRussianText(){
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

        String result = localizationService.locale(Country.RUSSIA);

        assertEquals("Добро пожаловать", result);
    }

    @Test
    void locale_OtherCountry_ReturnsEnglishText() {
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();

        String resultUSA = localizationService.locale(Country.USA);

        assertEquals("Welcome", resultUSA);
    }
}
