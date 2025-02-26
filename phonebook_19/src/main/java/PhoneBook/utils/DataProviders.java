package PhoneBook.utils;

import PhoneBook.data.UserData;
import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider
    public static Object[][] loginDataProvider() {
        return new Object[][]{
                {UserData.VALID_EMAIL, UserData.VALID_PASSWORD},
                {"portishead2026@gmail.com", "Password@1"}
        };
    }
}
