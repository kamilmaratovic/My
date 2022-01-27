package DataDrivenTesting;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.tokens.ScalarToken;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DDT {

    WebDriver driver;

    @BeforeClass

    public void start(){
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test(dataProvider = "LoginData")
    public void loginTest(String email, String pass, String res) throws InterruptedException {
       driver.get("https://admin-demo.nopcommerce.com/login");
        WebElement login =   driver.findElement(By.className("email"));
        WebElement password = driver.findElement(By.className("password"));
        WebElement loginButton = driver.findElement(By.className("button-1"));
       login.clear();
       password.clear();
       login.sendKeys(email);
       password.sendKeys(pass);

       loginButton.click();
       String expTitle = "Dashboard / nopCommerce administration";
       String actualTitle = driver.getTitle();

        System.out.println(actualTitle);

        try {
            if (res.equals("Valid")) {
                if (expTitle.equals(actualTitle)) {
                    driver.findElement(By.xpath("//*[text()='Logout']")).click();
                    Assert.assertTrue(true);
                } else {
                    Assert.assertTrue(false);
                }
            } else if (res.equals("Invalid")) {
                if (expTitle.equals(actualTitle)) {
                  driver.findElement(By.xpath("//*[text()='Logout']")).click();
                    Assert.assertTrue(false);
                } else {
                    Assert.assertTrue(true);
                }
            }
        }catch (NoSuchElementException ignored){}
           }



    @AfterClass

    public void tearDown() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }


    @DataProvider(name="LoginData")
    public Object[][] getData() throws IOException {
//        return new Object[][]{
//                {"admin@yourstore.com", "admin", "Valid"},
//                {"admin@yourstore.com", "adm", "Invalid"},
//                {"adm@yourstore.com", "admin", "Invalid"},
//                {"adm@yourstore.com", "adm", "Invalid"}
//        };

        //get data from XL

        String path = "/Users/kamilmaratovic/IdeaProjects/My/dataStorage/credentials.xlsx";
        ExcelUtilityFile file = new ExcelUtilityFile(path);

       int totalRows =  file.getRowCount("Kamil");
       int totalColumns = file.getCellCount("Kamil", 0);
        System.out.println(totalColumns);
        System.out.println(totalColumns);

        Object[][] loginData =  new Object[totalRows-1][totalColumns];

        for(int i = 1; i<totalRows; i++){
            for (int j = 0; j<totalColumns; j++){
                loginData[i-1][j] = file.getCellData("Kamil", i, j);

            }
        }


        return loginData;
    }

}
