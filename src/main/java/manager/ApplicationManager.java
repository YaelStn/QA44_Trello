package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
        private WebDriver driver;
    private ChromeOptions chromeOptions;

        public WebDriver getDriver() {
            return driver;
        }

        @BeforeMethod
        public void setUp() {
            //добавляет аргумент для запуска браузера с установленным языком интерфейса
            chromeOptions = new ChromeOptions().addArguments("--lang=en");
            driver = new ChromeDriver (chromeOptions);
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

