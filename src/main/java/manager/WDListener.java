package manager;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

public class WDListener implements WebDriverListener {

    Logger logger = LoggerFactory.getLogger(WDListener.class);

    @Override
    public void beforeFindElement(WebElement element, By locator) {
        WebDriverListener.super.beforeFindElement(element, locator);
        logger.info("Before Find Element --->"+locator);
    }

    @Override
    public void afterFindElement(WebElement element, By locator, WebElement result) {
        WebDriverListener.super.afterFindElement(element, locator, result);
        logger.info("After Find Element --->"+locator);
        logger.info("Location of element " + result.getLocation());
    }

    @Override
    public void beforeClick(WebElement element) {
        WebDriverListener.super.beforeClick(element);
        logger.info("Before click element ---> " +element.getTagName()
                + " text= "+element.getText());
    }

    @Override
    public void afterClick(WebElement element) {
        WebDriverListener.super.afterClick(element);
        logger.info("After click element --->" +element.getTagName());
    }

    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        WebDriverListener.super.afterSendKeys(element, keysToSend);
        logger.info("sendKeys--->" + element.getTagName());
    }


    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
        WebDriverListener.super.onError(target, method, args, e);//otrabotaet tolko esli est oshibki
        logger.info("Huston we have a problem!");//togda budet vivoditsa eta fraza
        logger.info("Object target--->" + target.toString());//posmotret chto budet raspechativatsa
        logger.info("Method name--->" +method.getName()); //gde upali v kakom methode
        logger.info("InvocationTargetException--->" + e.getTargetException());// kakie imenno oshibki
        int i = new Random().nextInt(1000)+1000;//randomalnoe ima skrinshota
        String link = "src/main/java/screenshots/screen_"+i+".png";//put' v papku i nazvanie screen
        logger.info("Screen with error is--->"+link);
        WebDriver wd = (WebDriver) target;
        TakesScreenshot takesScreenshot = (TakesScreenshot) wd;//otdelniy class
        File tmp = takesScreenshot.getScreenshotAs(OutputType.FILE);//polushila file
        try {
            Files.copy(tmp,new File(link));//copy file v papku
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


    }
}


