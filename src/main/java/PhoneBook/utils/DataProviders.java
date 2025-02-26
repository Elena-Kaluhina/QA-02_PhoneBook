package PhoneBook.utils;

import PhoneBook.data.UserData;
import PhoneBook.model.Contact;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {
    @DataProvider
    public static Object[][] loginDataProvider() {
        return new Object[][]{
                {UserData.VALID_EMAIL, UserData.VALID_PASSWORD},
                {"portishead2024@gmail.com", "Password@1"},
                {"portishead2026@gmail.com", "Password@1"}
        };
    }

    /**
    создаём наши объекты:
               {"NameName", "LastName1", "1234567890", "admin@gmail.com", "Germany, Berlin", "Some Description"},
               {"NameName", "LastName2", "1234567890", "admin@gmail.com", "Germany, Berlin", "Some Description"},
               {"NameName", "LastName3", "1234567890", "admin@gmail.com", "Germany, Berlin", "Some Description"}
     */
    @DataProvider // двумерный массив
    public Object[][] addContactDataProvider() {
        return new Object[][]{
                {"NameName1", "LastName1", "1234567890", "admin@gmail.com", "Germany, Berlin1", "Some Description1"},
                {"NameName2", "LastName2", "1234567890", "admin@gmail.com", "Germany, Berlin2", "Some Description2"},
                {"NameName3", "LastName3", "1234567890", "admin@gmail.com", "Germany, Berlin3", "Some Description3"}
        };
    }

    /**
     теперь мы должны заменить public Object[][] iteratorDataProvider() {return new Object[][]{};}
     на public Iterator<Object[]> iteratorDataProvider() { List<Object[]> list= new ArrayList<>();
     заменяем return new Object[][]{};  на  return list.iterator();
     */
    @DataProvider //  одномерный массив
    public Iterator<Object[]> iteratorDataProvider() {
        List<Object[]> list= new ArrayList<>();
        list.add(new Object[]{"NameName1", "LastName1", "1234567890", "admin1@gmail.com", "Germany, Berlin1", "Some Description1"});
        list.add(new Object[]{"NameName2", "LastName2", "1234567891", "admin2@gmail.com", "Germany, Berlin2", "Some Description2"});
        list.add(new Object[]{"NameName3", "LastName3", "1234567892", "admin3@gmail.com", "Germany, Berlin3", "Some Description3"});
        list.add(new Object[]{"NameName4", "LastName4", "1234567893", "admin4@gmail.com", "Germany, Berlin4", "Some Description4"});
        return list.iterator();
    }

    /**
     заменяем public Object[][] objectDataProvider() {return new Object[][]{};}
     на   public Iterator<Object>[] objectDataProvider() { List<Object[]> list = new ArrayList<>();
     и меняем return new Object[][]{}; на return list.iterator();
     */
    @DataProvider
    public Iterator<Object[]> objectDataProvider() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new Contact().setName("Name1").setLastName("LastName1").setPhone("1234567890").setEmail("admin1@gmail.com").setAddress("Germany, Berlin1").setDescription("Description1")});
        list.add(new Object[]{new Contact().setName("Name2").setLastName("LastName2").setPhone("1234567891").setEmail("admin2@gmail.com").setAddress("Germany, Berlin2").setDescription("Description2")});
        list.add(new Object[]{new Contact().setName("Name3").setLastName("LastName3").setPhone("1234567892").setEmail("admin3@gmail.com").setAddress("Germany, Berlin3").setDescription("Description3")});
        list.add(new Object[]{new Contact().setName("Name4").setLastName("LastName4").setPhone("1234567893").setEmail("admin4@gmail.com").setAddress("Germany, Berlin4").setDescription("Description4")});
        return list.iterator();
    }

    /**
     дополнительный DataProvider метод, который мы прописали вручную и он нам нужен для того, чтобы с помощью инструмента
     BufferedReader который умеет обращаться с файлами и FileReader, который умеет скачивать эти файлы и находить путь
     "src/test/resources/contacts.csv" который мы прописали, начинает его читать ПОСТРОЧНО. line - это каждый раз
     новая строка, МАССИВ, который мы скармливаем, а потом в цикле while мы сообщаем о том, что наша строка будет разделена
     запятыми
     */
    @DataProvider
    public Iterator<Object[]> addContactFromCsv() throws IOException {
        // создаём список для хранения данных для тестов
        List<Object[]> list = new ArrayList<>();
        // открываем CSV файл для чтения
        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/contacts.csv"));
        // читаем первую строку из файла
        String line = reader.readLine();
        // обрабатываем каждую строку файла до конца
        while (line != null){
            // разделяем строку на элементы по запятой
            String[] split = line.split(",");
            // создаем объект Contact и устанавливаем его поля из прочитанных данных
            list.add(new Object[]{new Contact()
                    .setName(split[0])
                    .setLastName(split[1])
                    .setPhone(split[2])
                    .setEmail(split[3])
                    .setAddress(split[4])
                    //.setDescription(split[5])
            });
            // читаем следующую строку из файла
            line = reader.readLine();
        }
        // закрываем файл после чтения всех данных
        reader.close();
        // возвращаем итератор для списка объектов
        return list.iterator();
    }
}


