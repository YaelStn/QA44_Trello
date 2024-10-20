package tests;

import dto.UserDTO;
import manager.ApplicationManager;
import org.testng.annotations.Test;
import pages.HomePage;

public class LoginTests  extends ApplicationManager {

    UserDTO user = UserDTO.builder()
            .email("yael.stolshtein@gmail.com")
            .password("y6!?3)E-uW-SrCL")
            .build();

    @Test
    public void loginTest() {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBtnLogin()
                .typeEmail(user)
                .typePassword(user);


    }

}

