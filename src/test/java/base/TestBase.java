package base;

import connectors.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TestBase {
    private WebDriver driver;
    String url = "https://subscribe.jawwy.tv/en";


    public static ChromeOptions chromeOption() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.managed_default_content_settings.ads", 1);
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("−−lang=en");
        options.addArguments("--user-agent='Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) " +
                "Chrome/93.0.4577.63 Safari/537.36'");
        return options;
    }

    @BeforeClass()
    public void startDriver() {
        if (DriverFactory.getDriver() == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(chromeOption());
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            DriverFactory.addDriver(driver);
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            driver.navigate().to(url);
        }
    }

    @AfterClass()
    public void tearsDown() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            DriverFactory.storedDrivers.forEach(WebDriver::quit);
            DriverFactory.removeDriver();
        }));
    }
}
