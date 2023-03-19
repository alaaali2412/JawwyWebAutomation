package propertiesManager;

import pages.HomePage;
import pages.PageGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

public class localeLanguageReader {


    public String detectLanguage(String country) {
        Locale locale = new Locale(PageGenerator.GetInstance(HomePage.class).currentLanguage());
        ClassLoader loader = null;
        try {
            File file = new File(System.getProperty("user.dir") + "src/main/resources/");
            URL[] urls = {file.toURI().toURL()};
            loader = new URLClassLoader(urls);
        } catch (Exception e) {
        }
        return   ResourceBundle.getBundle("Languages",locale , loader).getString(country);
    }
}
