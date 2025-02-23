package PhoneBook;

import PhoneBook.model.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @Test(invocationCount = 1)
    public void loginExistedUserPositiveTest(){
        app.getUserHelper().clickOnLoginLink();
        app.getUserHelper().typeEmail("kalughina1@gmail.com");
        app.getUserHelper().typePassword("Password@1");
        app.getUserHelper().clickOnLoginButton();
        app.getUserHelper().checkLogin();
    }

    @Test
    public void loginWithoutEmailTest(){

        app.getUserHelper().clickOnLoginLink();
        app.getUserHelper().fillInLoginForm(new User()
               //.setEmail("kalughina1@gmail.com")
                .setPassword("Password@1"));
        app.getUserHelper().clickOnLoginButton();
        Assert.assertTrue(app.getUserHelper().isAlertPresent());
    }

}
