package KoelTests;

import Objects.KoelLoginPage;
import Objects.KoelMainPage;
import Utils.ExcelReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginPage {

    WebDriver driver;
    String email;
    String password;
    ExcelReader myTable;

    @BeforeMethod
    public void start() throws IOException {
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        String projectPath = System.getProperty("user.dir");
     myTable = new ExcelReader(projectPath+"/excel/excelFile.xlsx", "Kamil");
     email = myTable.getDataString(1, 0);
     password = myTable.getDataString(1,1);
    }


    @Test

    public void logInWithCorrectCredentials() throws InterruptedException {

        KoelLoginPage loginPage = new KoelLoginPage(driver);
        loginPage.open();
        KoelMainPage mainPage = loginPage.login(email, password);
        Assert.assertTrue(mainPage.isLogin());


    }

    @AfterMethod

    public void tearDown(){
        driver.quit();
    }


}
