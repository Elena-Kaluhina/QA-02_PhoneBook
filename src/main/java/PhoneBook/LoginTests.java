package PhoneBook;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @Test(invocationCount = 1)
    public void loginExistedUserPositiveTest(){
        clickOnLoginLink();
        typeEmail("kalughina1@gmail.com");
        typePassword("Password@1");
        clickOnLoginButton();
        checkLogin();
    }

    @Test
    public void loginWithoutEmailTest(){

        clickOnLoginLink();
        fillInLoginForm(new User()
               //.setEmail("kalughina1@gmail.com")
                .setPassword("Password@1"));
        clickOnLoginButton();
        Assert.assertTrue(isAlertPresent());
    }

}
