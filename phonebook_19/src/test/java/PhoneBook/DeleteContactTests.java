package PhoneBook;

import PhoneBook.data.ContactData;
import PhoneBook.data.UserData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static PhoneBook.fw.ContactHelper.CONTACT_LOCATOR;


public class DeleteContactTests extends TestBase {
    @BeforeMethod
    public void precondition() {
        app.getUserHelper().login(UserData.VALID_EMAIL, UserData.VALID_PASSWORD);
        app.getContactHelper().addContactPositiveData(ContactData.NAME);
    }

    @Test
    public void createOneAdnDeleteOneContactTest() {
        int contactsBefore = app.getContactHelper().getContactsCount();
        app.getContactHelper().clickAndDeleteOneContact();
        new WebDriverWait(app.driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.numberOfElementsToBe(By.className(CONTACT_LOCATOR), contactsBefore - 1));
        int contactsAfter = app.getContactHelper().getContactsCount();
        Assert.assertEquals(contactsAfter, contactsBefore - 1);
    }

    @Test
    public void deleteAllContactsTest() {
        try {
            while (app.getContactHelper().hasContacts()) {
                app.getContactHelper().deleteFirstContact();
            }
        } catch (NoSuchElementException e) {
             System.out.println("Все контакты были удалены");
        }
        Assert.assertEquals(app.getContactHelper().getContactsCount(), 0,"Не все контакты были удалены");
    }
}