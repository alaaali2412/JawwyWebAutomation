package TestCases;

import base.TestBase;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.PageGenerator;
import propertiesManager.localeLanguageReader;

public class TC03_ValidateSubscriptionPackageForIraq extends TestBase {
    localeLanguageReader locale = new localeLanguageReader();
    @Test
    public void RegisteredUserAddItemToCart() {
        PageGenerator.GetInstance(HomePage.class).chooseCountry(locale.detectLanguage("ThirdCountry"));
        PageGenerator.GetInstance(HomePage.class).checkPlanList();
        PageGenerator.GetInstance(HomePage.class).checkPriceCurrency();
        PageGenerator.GetInstance(HomePage.class).checkPricePerCountry(locale.detectLanguage("ThirdCountry"));
    }
}
