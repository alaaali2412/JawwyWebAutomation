package TestCases;

import base.TestBase;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.PageGenerator;
import propertiesManager.localeLanguageReader;

public class TC02_ValidateSubscriptionPackageForTunisia extends TestBase {

    localeLanguageReader locale = new localeLanguageReader();
    @Test
    public void RegisteredUserAddItemToCart() {
        PageGenerator.GetInstance(HomePage.class).chooseCountry(locale.detectLanguage("FirstCountry"));
        PageGenerator.GetInstance(HomePage.class).checkPlanList();
        PageGenerator.GetInstance(HomePage.class).checkPriceCurrency();
        PageGenerator.GetInstance(HomePage.class).checkPricePerCountry(locale.detectLanguage("FirstCountry"));
    }
}
