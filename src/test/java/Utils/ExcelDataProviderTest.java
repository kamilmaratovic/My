package Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class ExcelDataProviderTest {

//    public static void main(String[] args) throws IOException {
//        String projectPath = System.getProperty("user.dir");
//       ExcelDataProvider.testData(projectPath+"/excel/excelFile.xlsx", "Kamil");
//    }
    WebDriver driver;

    @BeforeMethod
    public void start(){
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
    }

    @Test (dataProvider="testData")

    public void test1(String email, String password){
        System.out.print(email +" / "+password);

        driver.get("https://opensource-demo.orangehrmlive.com/");
        WebElement userName = driver.findElement(By.cssSelector("[id='txtUsername']"));
        WebElement pass = driver.findElement(By.cssSelector("[id='txtPassword']"));
        userName.sendKeys(email);
        pass.sendKeys(password);

    }

    @DataProvider(name = "testData")

    public static Object[][] getData() throws IOException {
        String projectPath = System.getProperty("user.dir");
        Object[][] data = testData(projectPath+"/excel/excelFile.xlsx", "Kamil");
        return data;

    }

    public static Object[][] testData(String path, String sheet) throws IOException {

        ExcelReader excelReader = new ExcelReader(path, sheet);

      int column =  excelReader.getColumnCount();
       int row=  excelReader.getRowCount();

       Object [][] object = new Object[row-1][column];

       for(int i = 1; i<row; i++){
           for(int j = 0; j<column; j++){
               String data = excelReader.getDataString(i, j);
//               System.out.print(data);
               object[i-1][j] = data;
           }
       }
       return object;
    }


}
