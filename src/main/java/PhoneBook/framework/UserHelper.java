package PhoneBook.framework;

import PhoneBook.core.BaseHelper;
import PhoneBook.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class UserHelper extends BaseHelper {
    public UserHelper(WebDriver driver) {
        super(driver);
    }

    Logger logger = LoggerFactory.getLogger(UserHelper.class);

    public UserHelper typePassword(String password) {
        logger.info("Typing password: " + password);
        type(By.name("password"), password);
        return this;
    }

    public UserHelper typeEmail(String email) {
        logger.info("Typing email: " + email);
        type(By.name("email"), email);
        return this;
    }

    public UserHelper clickOnLoginButton() {

        click(By.name("login"));
        return this;
    }

    public UserHelper clickOnLoginLink() {

        click(By.xpath("//a[.='LOGIN']"));
        return this;
    }

    public UserHelper checkLogin() {

        Assert.assertTrue(isElementPresent(By.xpath("//button[.='Sign Out']")));
        return this;
    }

    public void login(String email, String password) {
        clickOnLoginLink();
        typeEmail(email);
        typePassword(password);
        clickOnLoginButton();
    }

    public void fillInLoginForm(User user) {
        typeEmail(user.getEmail());
        typePassword(user.getPassword());
    }

    public void clickOnSignOutButton() {
        click(By.xpath("//button[.='Sign Out']"));
    }

    public boolean isSignOutButtonPresent() {
       return isElementPresent(By.xpath("//button[.='Sign Out']"));
    }
}
