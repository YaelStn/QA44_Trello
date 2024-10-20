package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
        private WebDriver driver;

        public WebDriver getDriver() {
            return driver;
        }

        @BeforeMethod
        public void setUp() {
            // chromeOptions = new ChromeOptions().addArguments("--lang=en");
            driver = new ChromeDriver(); //(chromeOptions);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            //   WebDriverListener webDriverListener = new WDListener();
            // driver = new EventFiringDecorator(webDriverListener).decorate(driver);

        }

        @AfterMethod
        public void tearDown() {
//            if (driver != null)
//                driver.quit();
       }
    }

