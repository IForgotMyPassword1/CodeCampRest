import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PlanetsPage {
    private WebDriver driver;

    public PlanetsPage(WebDriver driver){
        this.driver = driver;
    }

    private void SetPlanet(String planetName){


    }
    public void navigateTo() {
        driver.findElement(By.cssSelector("[aria-label='planets']")).click();
    }

    public String getRadius(String planetName) {
        var planets = driver.findElements(By.className("planets"));
        WebElement jupiter = null;
        for(WebElement planet : planets){
            if(planet..equals(planetName)){
                jupiter = planet;
                break;
            }
        }
        return jupiter.findElement(By.className("radius")).getText();
    }

}
