package pages;

import interfaces.Path;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProfileAndVisibilityPage extends BasePage implements Path {

    public ProfileAndVisibilityPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//div[@data-test-selector='profile-hover-info']")
    WebElement profilePhoto;
    @FindBy(xpath = "//button[@data-testid='change-avatar']")
    WebElement changeProfilePhoto;

     @FindBy(xpath = "//button[@data-testid='upload-button']")
      WebElement uploadPhoto;
   @FindBy(xpath = "//button[@type='submit']")
    WebElement btnUpload;
    @FindBy(id = "image-input")  //tk zagruj ne v knopku
    WebElement inputUploadPhoto;
    @FindBy(xpath = "//div[@class='css-1748k3u']")
    WebElement popUpMessageAvatarAdded;
    @FindBy(xpath = "//span[@class='css-1fd0fe1']")
    WebElement messageWrongFileFormat;


    public ProfileAndVisibilityPage changeAvatar(String fileName) {
        List<String> tabs = new ArrayList<>(driver.getWindowHandles());//nado prochitat spisok nashix otkritix okon
        driver.switchTo().window(tabs.get(1));//driver perekluchis na str kot pod index 1 v arraytabs

        Actions actions = new Actions(driver);
        actions.moveToElement(profilePhoto).pause(2000).click().perform();//smeshaem selenium na div, obazat method perform
        changeProfilePhoto.click();//menyaem foto
        // pause(3000);
       // uploadPhoto.sendKeys("Absolute Path");
        File file = new File(PHOTOS_PATH + fileName);//sozdaem object file
        String filePath = file.getAbsolutePath();//absolutniy put
        inputUploadPhoto.sendKeys(filePath);//zagrujaem foto ne v knopku a v input
        // pause(3000);
        //btnUpload.click();//clickaem na sinyuy knopku upload
        clickWait(btnUpload,3);

        return this;
    }

    public boolean isTextInElementPresent_AvatarAdded() {
        return isTextInElementPresent(popUpMessageAvatarAdded,"We've uploaded your new avatar. " +
                "It may take a few minutes to display everywhere.", 7 );
    }

    public boolean isTextInElementPresent_WrongFileFormat() {
        return isTextInElementPresent(messageWrongFileFormat, "Upload a photo or select from some default options",5);
    }
}