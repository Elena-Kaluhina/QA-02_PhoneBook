package PhoneBook;

import PhoneBook.data.ContactData;
import PhoneBook.data.UserData;
import PhoneBook.model.Contact;
import PhoneBook.utils.DataProviders;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static PhoneBook.framework.ContactHelper.CONTACT_LOCATOR;

public class AddContactTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        /**
        if мы закомментировали, потому что app.init() мы убрали из
         @BeforeSuite в TestBase и вернули его в @BeforeMethod
         то же самое мы сделали с app.stop(), забрали его из
         @AfterSuite в TestBase и вернули его в @AfterMethod
         и теперь мы стали открывать каждый тест в новом окне,
         если бы мы оставили app.init() и app.stop() в Suite,
         то все наши тесты проходили бы в одном окне
        */
//        if (app.getUserHelper().isSignOutButtonPresent()){
//            logger.info("User already Logged in. Sign Out..");
//            app.getUserHelper().clickOnSignOutButton();
//        }else{
//            logger.info("LOGIN link is present. No need to Sign Out.");
//        }
            app.getUserHelper().login(UserData.VALID_EMAIL, UserData.VALID_PASSWORD);
    }

    @Test
    public void addContactPositiveTest() {
        int contactsBefore = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ДО теста: " + contactsBefore);
        //addContactPositiveData(CONTACT_NAME);
        String name = "Name";
        app.getContactHelper().addContactPositiveData(new Contact()
                .setName(ContactData.NAME)
                .setLastName("LastName")
                .setPhone("1234567890")
                .setEmail("admin@gmail.com")
                .setAddress("Germany, Berlin")
                .setDescription("Some Description"));
        int contactsAfter = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ теста: " + contactsAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(ContactData.NAME));
        Assert.assertEquals(contactsAfter, contactsBefore + 1);
    }

    @Test
    public void addContactDataPositiveTest() {
        int contactsBefore = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ДО теста: " + contactsBefore);
        //addContactPositiveData(CONTACT_NAME);
        app.getContactHelper().addContactPositiveData(new Contact()
                .setName(ContactData.NAME)
                .setLastName(ContactData.LAST_NAME)
                .setPhone(ContactData.PHONE)
                .setEmail(ContactData.EMAIL)
                .setAddress(ContactData.ADDRESS)
                .setDescription(ContactData.DESCRIPTION));
        int contactsAfter = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ теста: " + contactsAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(ContactData.NAME));
        Assert.assertEquals(contactsAfter, contactsBefore + 1);
    }

    /**
    1. Для того, чтобы адаптировать наш тест "addContactPositiveTest" под DataProvider, мы копируем весь тест и дублируем
    его ниже. Возле аннотации @Test в скобках мы пишем (dataProvider = "addContactDataProvider", dataProviderClass = DataProviders.class).
    Дальше выделяем в скобках текст вместе с кавычками
                    .setName(ContactData.NAME)
                    .setLastName("LastName")
                    .setPhone("1234567890")
                    .setEmail("admin@gmail.com")
                    .setAddress("Germany, Berlin")
                    .setDescription("Some Description"));
    (каждый отдельно) и вытягиваем из них параметры в виде String присваивая им переменные. Теперь это будет выглядеть так :
    @Test(dataProvider = "addContactDataProvider", dataProviderClass = DataProviders.class)
        public void addContactDataProviderPositiveTest(String name, String lastName, String phone, String email,
                                                       String address, String description) {
            int contactsBefore = app.getContactHelper().getContactsCount();
            System.out.println("Количество контактов ДО теста: " + contactsBefore);
            //addContactPositiveData(CONTACT_NAME);
            app.getContactHelper().addContactPositiveData(new Contact()
                    .setName(name)
                    .setLastName(lastName)
                    .setPhone(phone)
                    .setEmail(email)
                    .setAddress(address)
                    .setDescription(description));
    2. После этого мы наводим мышкой на наш "addContactDataProvider" и создаём метод в пакете Utils.DataProviders.
    3. Переходим в папку Utils, DataProviders

    Также для того чтобы адаптировать наш тест "addContactPositiveTest" под DataProvider и сделать так, чтобы он не падал,
    нам нужно из LoginTestsClass @BeforeMethod public void precondition() перенести вот эту часть кода

            if (app.getUserHelper().isSignOutButtonPresent()){
                logger.info("User already Logged in. Sign Out..");
                app.getUserHelper().clickOnSignOutButton();
            }else{
                logger.info("LOGIN link is present. No need to Sign Out.");
            }

    и вставить в наш класс в @BeforeMethod в public void precondition
     */
    @Test(dataProvider = "addContactDataProvider", dataProviderClass = DataProviders.class)
    public void addContactDataProviderPositiveTest(String name, String lastName, String phone, String email,
                                                   String address, String description) {
        int contactsBefore = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ДО теста: " + contactsBefore);
        //addContactPositiveData(CONTACT_NAME);
        String nameContact = "Name";
        app.getContactHelper().addContactPositiveData(new Contact()
                .setName(nameContact)
                .setLastName(lastName)
                .setPhone(phone)
                .setEmail(email)
                .setAddress(address)
                .setDescription(description));
        int contactsAfter = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ теста: " + contactsAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(ContactData.NAME));
        Assert.assertEquals(contactsAfter, contactsBefore + 1);
    }

    /**
     создаём новый дата провайдер, но уже Iterator
     для этого копируем тест выше "DataProvider"
     и меняем название "addContactDataProvider" на "iteratorDataProvider"
     наводим на "IteratorDataProvider" и создаём новый метод, который переносит нас в Utils
    */
    @Test(dataProvider = "iteratorDataProvider", dataProviderClass = DataProviders.class)
    public void addContactDataProviderIteratorPositiveTest(String name, String lastName, String phone, String email,
                                                   String address, String description) {
        int contactsBefore = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ДО теста: " + contactsBefore);
        //addContactPositiveData(CONTACT_NAME);
        String nameContact = name;
        app.getContactHelper().addContactPositiveData(new Contact()
                .setName(nameContact)
                .setLastName(lastName)
                .setPhone(phone)
                .setEmail(email)
                .setAddress(address)
                .setDescription(description));
        int contactsAfter = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ теста: " + contactsAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(ContactData.NAME));
        Assert.assertEquals(contactsAfter, contactsBefore + 1);
    }

    /**
     создаём новый дата провайдер, но уже Object
     для этого копируем тест выше "DataProvider"
     и меняем название "addContactDataProvider" на "objectDataProvider"
     и так как мы теперь будем работать с объектом, а не со стрингами, то мы
     удаляем в скобках все String и меняем их на (Contact contact), убираем все setters (так как они будут
     использоваться в DataProvider и здесь они просто не нужны) и в скобках для
     addContactPositiveData передаём только contact. Вместо .addContactPositiveData(new Contact()
     .setName(nameContact)
     .setLastName(lastName)
     .setPhone(phone)
     .setEmail(email)
     .setAddress(address)
     .setDescription(description)); у нас теперь будет .addContactPositiveData(contact);
     НО! Так как раньше мы передавали String nameContact = name; наш name теперь стал красным, поэтому мы
     его удаляем и в .isContactAdded(ContactData.NAME)); меняем ContactData.NAME на contact.getName()
     После того, как мы всё поменяли, мы наводим курсор на "objectDataProvider" и создаём метод, который
     нас переносит в Utils
     */
    @Test(dataProvider = "objectDataProvider", dataProviderClass = DataProviders.class)
    public void addContactDataProviderObjectPositiveTest(Contact contact) {
        int contactsBefore = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ДО теста: " + contactsBefore);
        app.getContactHelper().addContactPositiveData(contact);
        int contactsAfter = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ теста: " + contactsAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(contact.getName()));
        Assert.assertEquals(contactsAfter, contactsBefore + 1);
    }

    /**
     копируем тест выше "@Test(dataProvider = "objectDataProvider", dataProviderClass = DataProviders.class)"
     и меняем название этого теста на"addContactFromCsv" которое мы скопировали из Utils.DataProviders
     проведя тест мы убедились в том, что все данные были взяты из файла contacts.csv
     */
    @Test(dataProvider = "addContactFromCsv", dataProviderClass = DataProviders.class)
    public void addContactDataProviderFromCsvPositiveTest(Contact contact) {
        int contactsBefore = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ДО теста: " + contactsBefore);
        app.getContactHelper().addContactPositiveData(contact);
        int contactsAfter = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ теста: " + contactsAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(contact.getName()));
        Assert.assertEquals(contactsAfter, contactsBefore + 1);
    }

    @Test
    public void addContactWODescriptionPositiveTest() {
        int contactsBefore = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ДО теста: " + contactsBefore);
        //addContactPositiveData(CONTACT_NAME);
        app.getContactHelper().addContactPositiveData(new Contact()
                .setName(ContactData.NAME)
                .setLastName("LastName")
                .setPhone("1234567890")
                .setEmail("admin@gmail.com")
                .setAddress("Germany, Berlin")
                //.setDescription("Some Description")
        );
        int contactsAfter = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ теста: " + contactsAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(ContactData.NAME));
        Assert.assertEquals(contactsAfter, contactsBefore + 1);
    }

    @AfterMethod(enabled = false)
    public void postCondition() {
        // Сколько контактов на странице
        int contactsBefore = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ДО удаления: " + contactsBefore);

        if (contactsBefore == 0) {
            System.out.println("Количество контактов 0. Нечего удалять");
            return;
        }
        app.getContactHelper().click(By.className(CONTACT_LOCATOR));
        app.getContactHelper().click(By.xpath("//button[.='Remove']"));
        // Ждём, пока не выполнено условие:
        // условие: уменьшилось ли количество контактов по сравнению с исходным значением contactsBefore
        //new WebDriverWait(driver, Duration.ofSeconds(2)).until(driver -> getContactsCount() < contactsBefore);
        // Явное ожидание, пока количество контактов не уменьшится (contactsBefore - 1)
        new WebDriverWait(app.driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.numberOfElementsToBe(By.className(CONTACT_LOCATOR), contactsBefore - 1));
        int contactsAfter = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ удаления: " + contactsAfter);
        // Проверяем, что стало на -1 контакт
        Assert.assertEquals(contactsAfter, contactsBefore - 1);
        System.out.println("Удаление контакта прошло успешно");
    }


}
