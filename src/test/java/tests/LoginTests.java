package tests;

import manager.ApplicationManager;
import org.testng.annotations.Test;
import pages.HomePage;

public class LoginTests  extends ApplicationManager {

//    UserDTO user = UserDTO.builder()
//            .email(getProperty("login.properties","email"))
//            .password(getProperty("login.properties", "password"))
//            .build();

    @Test
    public void loginTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin();
               // .typeEmail(user)
                //.typePassword(user);


    }

}
