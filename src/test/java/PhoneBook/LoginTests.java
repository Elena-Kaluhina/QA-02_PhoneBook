package PhoneBook;

import PhoneBook.model.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{

    @Test
    public void loginExistedUserPositiveTest(){
        app.getUserHelper().clickOnLoginLink();
        app.getUserHelper().typeEmail("kalughina123@bk.ru");
        app.getUserHelper().typePassword("Password@1");
        app.getUserHelper().clickOnLoginButton();
        app.getUserHelper().checkLogin();
    }

    @Test
    public void loginWOEmailNegativeTest(){
        app.getUserHelper().clickOnLoginLink();
       // fillInLoginForm("Password@1");
        app.getUserHelper().fillInLoginForm(new User()
                //.setEmail("kalughina123@bk.ru")
                .setPassword("Password@1"));
        app.getUserHelper().clickOnLoginButton();
        Assert.assertTrue(app.getUserHelper().isAlertPresent());
    }
}
