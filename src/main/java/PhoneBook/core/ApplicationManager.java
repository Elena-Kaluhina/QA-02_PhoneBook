package PhoneBook.core;

import PhoneBook.framework.ContactHelper;
import PhoneBook.framework.HomePageHelper;
import PhoneBook.framework.UserHelper;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class ApplicationManager {
    public WebDriver driver;
    private final String browser;
    UserHelper userHelper;
    ContactHelper contactHelper;
    HomePageHelper homePageHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("chrome_headless")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            options.addArguments("window-size=1920x1080");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        } else {
            //driver = new ChromeDriver();
            throw new IllegalArgumentException("❌ Некорректный браузер: " + browser +
                    ". Доступные варианты: chrome, firefox, edge, safari.");
        }
        driver.get("https://telranedu.web.app/home");
        driver.manage().window(); //.maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        userHelper = new UserHelper(driver);
        contactHelper = new ContactHelper(driver);
        homePageHelper = new HomePageHelper(driver);
    }

    public UserHelper getUserHelper() {
        return userHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }

    public HomePageHelper getHomePageHelper() {
        return homePageHelper;
    }

    public void stop() {
        driver.quit();
    }

}
