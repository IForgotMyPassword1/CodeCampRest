import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FormPage {

    private WebDriver driver;
    private By locatorLogin = By.cssSelector("[class='btnPrimary v-btn v-btn--is-elevated v-btn--has-bg theme--light v-size--default primary']");
    private By invalidFormResponse = By.cssSelector("[class='v-messages__message']");
    private By formButton = By.cssSelector("[class='users mr-2 v-btn v-btn--icon v-btn--round theme--light v-size--default']");
    private long delay = 3000;

    public FormPage(WebDriver driver){
        this.driver = driver;
    }


    public void waitForElement(By locator){
        WebDriverWait wait = new WebDriverWait(driver, delay);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void clickLoginButton(){
        waitForElement(locatorLogin);
        driver.findElement(locatorLogin).click();
    }
    public void clickFormButton(){
        driver.findElement(formButton).click();
    }

    public void checkResponse(){
        waitForElement(invalidFormResponse);
        Assertions.assertEquals("Invalid user and password",
                driver.findElement(invalidFormResponse).getText());
    }
}
