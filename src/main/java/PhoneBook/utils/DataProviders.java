package PhoneBook.utils;

import org.testng.annotations.DataProvider;

public class DataProviders {
    @DataProvider
    public static Object[][] loginDataProvider() {
        return new Object[][]{
                {"portishead2024@gmail.com", "Password@1"},
                {"portishead2025@gmail.com", "Password@1"},
                {"portishead2026@gmail.com", "Password@1"}
        };
    }

    @DataProvider
    public Object[][] addContactDataProvider() {
        return new Object[][]{
                {"NameName", "LastName", "1234567890", "admin@gmail.com", "Germany, Berlin", "Some Description"},
                {"NameName", "LastName", "1234567890", "admin@gmail.com", "Germany, Berlin", "Some Description"},
                {"NameName", "LastName", "1234567890", "admin@gmail.com", "Germany, Berlin", "Some Description"}
        };
    }
}
