package Objects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class KoelMainPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public KoelMainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 3, 5);
    }

    private WebElement getAvatar(){
      return   driver.findElement(By.xpath("//*[@class='avatar']"));
    }

    public boolean isLogin(){
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("\"//*[@class='avatar']\"")));
        } catch (NoSuchElementException | TimeoutException ignored){}

        return getAvatar().isDisplayed();
    }
}
