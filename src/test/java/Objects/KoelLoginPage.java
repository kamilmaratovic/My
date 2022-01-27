package Objects;

import KoelTests.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class KoelLoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public KoelLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 3, 5);
    }

    private WebElement getEmailField(){
        return driver.findElement(By.cssSelector("[type='email']"));
    }

    private WebElement getPasswordField(){
        return driver.findElement(By.cssSelector("[type='password']"));
    }

    private WebElement getLoginButton(){
        return driver.findElement(By.cssSelector("[type='submit']"));
    }

    public void open(){
        driver.get("https://bbb.testpro.io/");
    }

    public KoelMainPage login(String email, String password){
        driver.get("https://bbb.testpro.io/");
        getEmailField().sendKeys(email);
        getPasswordField().sendKeys(password);
        getLoginButton().click();
        return new KoelMainPage(driver);
    }

}
