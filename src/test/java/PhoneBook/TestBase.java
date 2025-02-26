package PhoneBook;

import PhoneBook.core.ApplicationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITest;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class TestBase {
    protected final ApplicationManager app =
            new ApplicationManager(System.getProperty("browser","chrome"));

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite
    public void beforeSuite(){
        //app.init();
    }

    @BeforeMethod
    public void setUp(Method method) {
        logger.info("Test is started: [" + method.getName() + "]");
        app.init(); //мы перенесли инициализацию в BeforeSuite
    }

    @AfterMethod
    public void tearDown(Method method, ITestResult result) {
        app.stop(); //мы перенесли остановку в AfterSuite
        if (result.isSuccess()){
            logger.info("Test is PASSED: [" + method.getName() + "]");
        } else {
            logger.error("Test is FAILED: [" + method.getName() + "], Screenshot: " + app.getUserHelper().takeScreenshot() + "]");
        }
    }

    @AfterSuite(enabled = true)
    public void afterSuite(){
        //app.stop();
    }

}
