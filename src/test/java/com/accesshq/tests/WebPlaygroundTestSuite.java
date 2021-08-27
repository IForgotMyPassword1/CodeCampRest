import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
public class WebPlaygroundTestSuite {

    private WebDriver driver;

    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
        driver.get("https://d1iw6mb9di5l9r.cloudfront.net/#/");
    }

    @AfterEach
    public void cleanUp(){
        driver.quit();
    }

    @Test
    public void WebTest(){

        driver.findElement(By.id("forename")).sendKeys("Ben");

    }

    @Test
    public void LoginButtonTest(){
        WebDriver drive = new ChromeDriver();
        drive.get("https://d1iw6mb9di5l9r.cloudfront.net/#/");
        HomePage homePage = new HomePage(drive);
        homePage.clickLoginButton();
        drive.quit();
    }

    @Test
    public void ClickAllTheButtonsTest(){
        WebDriver drive = new ChromeDriver();
        drive.get("https://d1iw6mb9di5l9r.cloudfront.net/#/");
        List<WebElement> buttons = drive.findElements(By.tagName("button"));

        for(WebElement button : buttons){
            if(!button.getText().contains("Click Me")) {
                button.click();
            }
        }
    }

    @Test
    public void ClickHiddenLoginButtonTest(){
        FormPage formPage = new FormPage(driver);

        //Arrange
        formPage.clickFormButton();
        //Act
        formPage.clickLoginButton();
        //Assert
        formPage.checkResponse();
    }

    @Test
    public void FormInputErrorTest() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://d1iw6mb9di5l9r.cloudfront.net/#/");

        By formElements[] = {By.id("name-err"),  By.id("email-err"), By.id("agree-err")};
        String responseArr[] = {"Your name is required", "Your email is required", "You must agree to continue"};

        driver.findElement(By.cssSelector("[aria-label='forms']")).click();
        driver.findElement(By.cssSelector("[class='v-btn v-btn--is-elevated v-btn--has-bg theme--light v-size--default']")).click();

        for (int i = 0; i < formElements.length; i++){
            Assertions.assertEquals(responseArr[i],
                    driver.findElement(formElements[i]).getText());
        }
    }

    @Test
    public void FormValidationTest(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://d1iw6mb9di5l9r.cloudfront.net/#/");

        By formElements[] = {By.id("name"),  By.id("email"), By.cssSelector("[class='v-input--selection-controls__ripple']")};
        By formErrors[] = {By.id("name-err"),  By.id("email-err"), By.id("agree-err")};

        String responseArr[] = {"Your name is required", "Your email is required", "You must agree to continue"};
        String inputs[] = {"Ben", "ben@email.com"};

        driver.findElement(By.cssSelector("[aria-label='forms']")).click();

        driver.findElement(formElements[0]).sendKeys(inputs[0]);
        driver.findElement(formElements[1]).sendKeys(inputs[1]);
        driver.findElement(formElements[2]).click();

        driver.findElement(By.cssSelector("[class='v-btn v-btn--is-elevated v-btn--has-bg theme--light v-size--default']")).click();

        for (int i = 0; i < responseArr.length; i++){
            Assertions.assertFalse(checkForElement(formErrors[i], driver));
        }
        driver.quit();
    }

    public boolean checkForElement(By locator, WebDriver driver){
        try {
            driver.findElement(locator);
            return true;
        }catch(org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    @Test
    public void checkRadiusJupiterTest(){
        PlanetsPage planetsPage = new PlanetsPage(driver);

        planetsPage.navigateTo();
        String radius = planetsPage.getRadius("Jupiter");
        Assertions.assertEquals("69,911 km", radius);

    }

}
