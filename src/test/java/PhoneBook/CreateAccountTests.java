package PhoneBook;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateAccountTests extends TestBase{

    @Test
    public  void CreateAccountPositiveTest(){
        // Click on 'Login' link //a[.='LOGIN']
        app.driver.findElement(By.xpath("//a[.='LOGIN']")).click();
        // Enter 'email' By.name("email")
        app.driver.findElement(By.name("email")).click();
        app.driver.findElement(By.name("email")).clear();
        app.driver.findElement(By.name("email")).sendKeys("kalughina1@gmail.com");
        // Enter 'password' By.name("password")
        app.driver.findElement(By.name("password")).click();
        app.driver.findElement(By.name("password")).clear();
        app.driver.findElement(By.name("password")).sendKeys("Password@1");
        // Click on 'Registration' button By.name("registration")
        app.driver.findElement(By.name("registration")).click();
        //Assert button //button[.='Sign Out']
        Assert.assertTrue(app.getContactHelper().isElementPresent(By.xpath("//button[.='Sign Out']")));
        //Assert.assertFalse(isElementPresent(By.xpath("//div[.='Registration failed with code 409']")));
    }

    @Test
    public  void CreateAccountNegativeTest(){
        app.driver.findElement(By.xpath("//a[.='LOGIN']")).click();
        app.driver.findElement(By.name("email")).click();
        app.driver.findElement(By.name("email")).clear();
        app.driver.findElement(By.name("email")).sendKeys("portishead2027@gmail.com");
        app.driver.findElement(By.name("password")).click();
        app.driver.findElement(By.name("password")).clear();
        app.driver.findElement(By.name("password")).sendKeys("Password@1");
        app.driver.findElement(By.name("registration")).click();
//      isAlertPresent();
        Assert.assertTrue(app.getContactHelper().isAlertPresent());
//      Assert.assertTrue(isElementPresent(By.xpath("//div[.='Registration failed with code 409']")));
    }

    @Test
    public  void CreateAccountNegativeSoftAssertTest(){
        SoftAssert softAssert = new SoftAssert();
        app.driver.findElement(By.xpath("//a[.='LOGIN']")).click();
        app.driver.findElement(By.name("email")).click();
        app.driver.findElement(By.name("email")).clear();
        app.driver.findElement(By.name("email")).sendKeys("portishead2027@gmail.com");
        app.driver.findElement(By.name("password")).click();
        app.driver.findElement(By.name("password")).clear();
        app.driver.findElement(By.name("password")).sendKeys("Password@1");
        app.driver.findElement(By.name("registration")).click();
        /*
         * В Java, SoftAssert — это класс, предоставляемый библиотекой TestNG, который используется для выполнения "мягких"
         * утверждений (soft assertions) в тестах. В отличие от "жестких" (hard assertions), которые немедленно прерывают
         * выполнение теста при ошибке, мягкие утверждении позволяют продолжить выполнение теста даже если одно из утверждений не прошло.
         * Цель: SoftAssert используется для проверки нескольких условий в рамках одного теста, не прерывая его выполнение при первой неудаче
         */
        softAssert.assertTrue(app.getContactHelper().isAlertPresent());
        softAssert.assertTrue(app.getContactHelper().isElementPresent(By.xpath("//div[.='Registration failed with code 409']")));
        softAssert.assertAll();
        /*
         * Назначение: assertAll() используется для проверки всех утверждений, сделанных с помощью SoftAssert, в конце теста.
         * Если одно или несколько утверждений не прошли, assertAll() вызовет исключение и тест будет помечен как неудавшийся
         */

    }
}
