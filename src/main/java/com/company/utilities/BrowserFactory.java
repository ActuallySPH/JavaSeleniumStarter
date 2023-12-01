package com.company.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class BrowserFactory {
    private WebDriver driver;

    private static ChromeOptions getChromeOptions(String browserLanguage, String fileDownloadPath) {
        Map<String, Object> prefsMap = new HashMap<String, Object>();
        prefsMap.put("profile.default_content_settings.popups", 0);
        prefsMap.put("download.default_directory", fileDownloadPath);
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("headless");
        options.addArguments("-incognito");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("no-sandbox");
        options.setExperimentalOption("prefs", prefsMap);
        if (!browserLanguage.equals("")) {
            options.addArguments("--lang=" + browserLanguage);
        }
        return options;
    }

    public WebDriver createLocalBrowserInstance(String browser, String browserLanguage, String browserVersion) {
        if (browser.equalsIgnoreCase("CHROME")) {
            String fileDownloadPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "TestData" + File.separator + "Downloads" + File.separator + browser + File.separator;
            ChromeOptions options = getChromeOptions(browserLanguage, fileDownloadPath);

            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\Drivers\\chromedriver.exe");
            driver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("Safari")) {
            driver = new SafariDriver();
        } else if (browser.equalsIgnoreCase("FIREFOX")) {
            FirefoxOptions options = new FirefoxOptions();
            FirefoxBinary binary = new FirefoxBinary();
            binary.addCommandLineOptions("--headless");
            options.setBinary(binary);
            options.addPreference("browser.download.folderList", 2);
            options.addPreference("browser.helperApps.alwaysAsk.force", false);
            options.addPreference("browser.download.dir", System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "TestData" + File.separator + "Downloads" + File.separator + browser + File.separator);
            options.addPreference("browser.download.manager.showWhenStarting", false);
            options.addPreference("browser.helperApps.neverAsk.saveToDisk", "multipart/x-zip,application/zip,application/x-zip-compressed,application/x-compressed,application/msword,application/csv,text/csv,image/png ,image/jpeg, application/pdf, text/html,text/plain,  application/excel, application/vnd.ms-excel, application/x-excel, application/x-msexcel, application/octet-stream, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            options.addPreference("browser.helperApps.neverAsk.openFile", "text/csv,application/x-msexcel,application/excel,application/x-excel,application/vnd.ms-excel,image/png,image/jpeg,text/html,text/plain,application/msword,application/xml,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            options.addPreference("browser.download.manager.alertOnEXEOpen", false);
            options.addPreference("browser.download.manager.focusWhenStarting", false);
            options.addPreference("browser.download.manager.useWindow", false);
            options.addPreference("browser.download.manager.showAlertOnComplete", false);
            options.addPreference("browser.download.manager.closeWhenDone", false);
            options.addPreference("browser.download.manager.alertOnEXEOpen", false);
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(options);
        } else if (browser.equalsIgnoreCase("MsEdge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        driver.manage().window().setSize(new Dimension(1366, 768));
        return driver;
    }

}
