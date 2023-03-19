package pages;

import base.PageBase;
import org.openqa.selenium.support.PageFactory;

public class PageGenerator {
    public static <TPage extends PageBase> TPage GetInstance(Class<TPage> pageClass) {
        try {
            return PageFactory.initElements(connectors.DriverFactory.getDriver(), pageClass);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
