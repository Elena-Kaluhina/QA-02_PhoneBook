package PhoneBook;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.List;

public class TestBase {
    public static final String CONTACT_LOCATOR = "contact-item_card__2SOIM";
    public static final String CONTACT_NAME = "NameName";
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://telranedu.web.app/home");
        driver.manage().window().setPosition(new Point(2500, 0));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @AfterMethod // (enabled = false)
    public void tearDown() {
        driver.quit();
    }

    public boolean isHomeComponentPresent() {
        return isElementPresent(By.cssSelector("div:nth-child(2) h1"));
    }

    public boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public boolean isAlertPresent() {
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
        if(alert == null){
            return false;
        } else {
            driver.switchTo().alert().accept();
            return true;
        }
    }

    public void type(By locator, String text) {
        click(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void typePassword(String password) {
        type(By.name("password"), password);
    }

    public void typeEmail(String email) {
        type(By.name("email"), email);
    }

    public void clickOnLoginButton() {
        click(By.name("login"));
    }

    public void clickOnLoginLink() {
        click(By.xpath("//a[.='LOGIN']"));
    }

    public void checkLogin() {
        Assert.assertTrue(isElementPresent(By.xpath("//button[.='Sign Out']")));
    }

    public void login(String email, String password) {
        clickOnLoginLink();
        typeEmail(email);
        typePassword(password);
        clickOnLoginButton();
    }

    protected void addContactPositiveData(String name) {
        // Click on ADD link
        click(By.xpath("//a[.='ADD']"));
        // enter name
        type(By.xpath("//input[@placeholder='Name']"), name);
        // enter lastName
        type(By.xpath("//input[@placeholder='Last Name']"), "LastName");
        // enter phone
        type(By.xpath("//input[@placeholder='Phone']"), "1234567890");
        // enter email
        type(By.xpath("//input[@placeholder='email']"), "kalughina1@gmail.com");
        // enter address
        type(By.xpath("//input[@placeholder='Address']"), "Germany, Berlin");
        // enter description
        type(By.xpath("//input[@placeholder='description']"), "My Contact Test");
        // click on Save button
        click(By.xpath("//b[.='Save']"));
    }

    protected int getContactsCount() {
        if (isElementPresent(By.className(CONTACT_LOCATOR))) {
            return driver.findElements(By.className(CONTACT_LOCATOR)).size();
        }
        return 0;
    }

    protected boolean isContactAdded(String textToFind) {
        List<WebElement> contacts = driver.findElements(By.className(CONTACT_LOCATOR));
        for(WebElement element : contacts){
            if(element.getText().contains(textToFind))
                return true;
        }
        return false;
    }

    protected void clickAndDeleteOneContact() {
        click(By.className(CONTACT_LOCATOR));
        click(By.xpath("//button[.='Remove']"));
    }

    protected boolean hasContacts() {
        return isElementPresent(By.className(CONTACT_LOCATOR));
    }

    protected void deleteFirstContact() {
        int contactsBefore = getContactsCount();
        clickAndDeleteOneContact();
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.numberOfElementsToBe(By.className(CONTACT_LOCATOR), contactsBefore - 1));
    }
}
