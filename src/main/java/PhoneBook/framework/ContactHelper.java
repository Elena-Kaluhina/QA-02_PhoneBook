package PhoneBook.framework;

import PhoneBook.core.BaseHelper;
import PhoneBook.data.ContactData;
import PhoneBook.model.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void addContactPositiveData(String name) {
        // Click on ADD link
        click(By.xpath("//a[.='ADD']"));
        // enter name
        type(By.xpath("//input[@placeholder='Name']"), name);
        // enter lastName
        type(By.xpath("//input[@placeholder='Last Name']"), "LastName");
        // enter phone
        type(By.xpath("//input[@placeholder='Phone']"), "1234567890");
        // enter email
        type(By.xpath("//input[@placeholder='email']"), "portishead@gmail.com");
        // enter address
        type(By.xpath("//input[@placeholder='Address']"), "Germany, Berlin");
        // enter description
        type(By.xpath("//input[@placeholder='description']"), "My Contact Test");
        // click on Save button
        click(By.xpath("//b[.='Save']"));
    }

    public void addContactPositiveData(Contact contact) {
        click(By.xpath("//a[.='ADD']"));
        type(By.xpath("//input[@placeholder='Name']"), contact.getName());
        type(By.xpath("//input[@placeholder='Last Name']"), contact.getLastName());
        type(By.xpath("//input[@placeholder='Phone']"), contact.getPhone());
        type(By.xpath("//input[@placeholder='email']"), contact.getEmail());
        type(By.xpath("//input[@placeholder='Address']"), contact.getAddress());
        type(By.xpath("//input[@placeholder='description']"), contact.getDescription());
        click(By.xpath("//b[.='Save']"));
    }

    public int getContactsCount() {
        if (isElementPresent(By.className(ContactData.CONTACT_LOCATOR))) {
            return driver.findElements(By.className(ContactData.CONTACT_LOCATOR)).size();
        }
        return 0;
    }

    public boolean isContactAdded(String textToFind) {
        List<WebElement> contacts = driver.findElements(By.className(ContactData.CONTACT_LOCATOR));
        for(WebElement element : contacts){
            if(element.getText().contains(textToFind))
                return true;
        }
        return false;
    }

    public void clickAndDeleteOneContact() {
        click(By.className(ContactData.CONTACT_LOCATOR));
        click(By.xpath("//button[.='Remove']"));
    }

    public boolean hasContacts() {
        return isElementPresent(By.className(ContactData.CONTACT_LOCATOR));
    }

    public void deleteFirstContact() {
        int contactsBefore = getContactsCount();
        clickAndDeleteOneContact();
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.numberOfElementsToBe(By.className(ContactData.CONTACT_LOCATOR), contactsBefore - 1));
    }
}
