package tests;

import dto.UserDTO;
import manager.ApplicationManager;
import manager.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProfileAndVisibilityPage;

import static manager.PropertiesReader.getProperty;

public class ProfileTests extends ApplicationManager {

    UserDTO user = UserDTO.builder()
            .email(getProperty("login.properties","email"))
            .password(getProperty("login.properties","password"))
            .build();

    ProfileAndVisibilityPage profileAndVisibilityPage;

    @BeforeMethod
    public void loginBeforeProfile() {
        HomePage homePage = new HomePage(getDriver());
        profileAndVisibilityPage = homePage.clickBtnLogin()//to chto vernetsa
                .typeEmail(user)
                .typePassword(user)
                .goToProfileAndVisibility();//metod kotoriy perevedet
    }


    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void changeProfilePhotoPositiveTest() {
        Assert.assertTrue(profileAndVisibilityPage
                .changeAvatar("cat3.jpeg")
                .isTextInElementPresent_AvatarAdded());
    }

    @Test
    public void changeProfilePhotoNegativeTest_wrongFileFormat() {
        Assert.assertTrue(profileAndVisibilityPage
                .changeAvatar("log-241007Qa44190611.log")
                .isTextInElementPresent_WrongFileFormat());
    }
}