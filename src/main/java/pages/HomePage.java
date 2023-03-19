package pages;

import base.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import java.util.List;

public class HomePage extends PageBase {

    @FindBy(id = "translation-btn")
    private WebElement translationBtn;

    @FindBy(id = "country-btn")
    private WebElement countryBtn;

    @FindBy(className = "plan-title")
    private List<WebElement> plansList;

    @FindBy(className = "country")
    private List<WebElement> countryList;

    @FindBy(className = "price")
    private List<WebElement> priceCurrency;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public static String language;

    public String currentLanguage() {
        switch (getText(translationBtn)) {
            case "English":
                language = "ar";
                break;
            case "العربية":
                language = "en";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + getText(translationBtn));
        }
        return language;
    }


    public void selectCountry(String countryName) {
        waitForPageToLoad();
        clickButton(countryBtn);
        for (WebElement country : countryList) {
            if (country.getText().contains(countryName)) {
                forceClickElement(country);
                break;
            }
        }
    }

    public void chooseCountry(String country) {
        switch (currentLanguage()) {
            case "en":
                switch (country) {
                    case "Tunisia":
                        selectCountry("Tunisia");
                        break;
                    case "Algeria":
                        selectCountry("Algeria");
                        break;
                    case "Iraq":
                        selectCountry("Iraq");
                        break;
                }
                break;
            case "ar":
                switch (country) {
                    case "تونس":
                        selectCountry("تونس");
                        break;
                    case "الجزائر":
                        selectCountry("الجزائر");
                        break;
                    case "العراق":
                        selectCountry("العراق");
                        break;
                }
                break;
            default:throw new IllegalStateException("Invalid country");
        }
    }

    public void checkPlanList() {
        Assert.assertEquals(plansList.size(), 3);
        switch (currentLanguage()) {
            case "en":
                Assert.assertEquals(plansList.get(0).getText(), "LITE");
                Assert.assertEquals(plansList.get(1).getText(), "CLASSIC");
                Assert.assertEquals(plansList.get(2).getText(), "PREMIUM");
                break;
            case "ar":
                Assert.assertEquals(plansList.get(0).getText(), "لايت");
                Assert.assertEquals(plansList.get(1).getText(), "الأساسية");
                Assert.assertEquals(plansList.get(2).getText(), "بريميوم");
                break;
            default:
                throw new IllegalStateException("Unexpected value: unknown package");
        }
    }

    public void checkPriceCurrency() {
        for (WebElement element : priceCurrency) {
            Assert.assertTrue(element.getText().contains("دولار أمريكي")
                    || element.getText().contains("USD"));

        }
    }

    public void checkPricePerCountry(String country) {
        switch (country) {
            case "Tunisia":
            case "تونس":
                checkPriceAndCurrency("1.7", "3.4", "5.8");
                break;
            case "Algeria":
            case "الجزائر":
                checkPriceAndCurrency("2.7", "5.3", "8");
                break;
            case "Iraq":
            case "العراق":
                checkPriceAndCurrency("2.4", "4.1", "6.2");

                break;
            default:
        }
    }

    public void checkPriceAndCurrency(String litePrice, String classicPrice, String premiumPrice) {
        for (int i = 0; i < plansList.size(); i++) {
            switch (plansList.get(i).getText()) {
                case "LITE":
                case "لايت":
                    Assert.assertTrue(priceCurrency.get(i).getText().contains(litePrice));
                    break;
                case "CLASSIC":
                case "الأساسية":
                    Assert.assertTrue(priceCurrency.get(i).getText().contains(classicPrice));
                    break;
                case "PREMIUM":
                case "بريميوم":
                    Assert.assertTrue(priceCurrency.get(i).getText().contains(premiumPrice));
                    break;
            }

        }
    }
}
