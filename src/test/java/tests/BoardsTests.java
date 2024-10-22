package tests;

import dataproviders.DataProviderBoards;
import dto.BoardDTO;
import dto.UserDTO;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.BoardsPage;
import pages.HomePage;
import pages.PersonalBoardPage;

import java.lang.reflect.Method;
import java.util.Random;

public class BoardsTests extends ApplicationManager {

    UserDTO user = UserDTO.builder()
            .email("yael.stolshtein@gmail.com")
            .password("y6!?3)E-uW-SrCL")
            .build();

    BoardsPage boardsPage = new BoardsPage(getDriver());

    @BeforeMethod
    public void loginBeforeBoards() {
        HomePage homePage = new HomePage(getDriver());
        boardsPage = homePage.clickBtnLogin()
                .typeEmail(user)
                .typePassword(user);
    }
    @Test
    public void createBoardPositive(Method method) {
        //int i = (int) ((System.currentTimeMillis()/1000)%3600);
        int i = new Random().nextInt(1000) + 1000;

        //sobiraem object
        BoardDTO board = BoardDTO.builder()
                .boardTitle("QA44-" + i)
                .build();


        logger.info(method.getName() + " "+ "test with board title -->" +board.getBoardTitle());

     //   HomePage homePage = new HomePage(getDriver());
        Assert.assertTrue(
     //           homePage.clickBtnLogin()
       //         .typeEmail(user)
         //       .typePassword(user)
                boardsPage.typeBoardTitle(board)
                .clickBtnCreateSubmitPositive()
                        .isTextInElementPresent_nameBoard(board.getBoardTitle()));


    }

    @Test
    public void createBoardNegative() {

        BoardDTO board = BoardDTO.builder()
                .boardTitle("   ")
                .build();

       // HomePage homePage = new HomePage(getDriver());
        Assert.assertFalse(
                //homePage.clickBtnLogin()
                //.typeEmail(user)
                //.typePassword(user)
                boardsPage.typeBoardTitle(board)
                .clickBtnCreateSubmitNegative()
                .isElementClickable_btnCreateSubmit(),"element unclickable");
    }
    @Test(dataProvider = "DPFile_deleteBoardPositiveTest",dataProviderClass = DataProviderBoards.class)
    public void deleteBoardPositiveTest(BoardDTO board){
//генерируешь случайное число (например, 1457),
// а затем используешь его для создания уникального названия доски
//        int i = new Random().nextInt(1000) + 1000;
//
//        BoardDTO board = BoardDTO.builder()
//               .boardTitle("QA44-" + i)
//                .build();

        PersonalBoardPage personalBoardPage = boardsPage
                .typeBoardTitle(board)
                .clickBtnCreateSubmitPositive();

        //esli sozdali to udalayem
        if (personalBoardPage.isTextInElementPresent_nameBoard(board.getBoardTitle())) {
            Assert.assertTrue(personalBoardPage.deleteBoard(board)
                    .isTextPopUpPresent());
        } else {
            Assert.fail("board isn`t create");
        }
            ;
    }


}
