package cucumber;

import dev.failsafe.internal.util.Assert;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class StepDefinitions {
    WebDriver dr;

    @Given("open Google Chrome Browser")
    public void openGoogleChromeBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Desktop\\Luxoft\\StreetMap\\src\\main\\resources\\chromedriver.exe");
        dr = new ChromeDriver();
        dr.manage().window().maximize();
    }
    @When("search Open Street Map")
    public void searchOpenStreetMap() {
        dr.get("https://www.openstreetmap.org/");
    }
    @Then("homepage is displayed")
    public void thePageIsDisplayed() {
        assertTrue(dr.findElement(By.xpath("//*[@id='map']")).isDisplayed(), "Element not found");
        dr.close();
    }

    @Given("user is on the website")
    public void userIsOnTheWebsite() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Desktop\\Luxoft\\StreetMap\\src\\main\\resources\\chromedriver.exe");
        dr = new ChromeDriver();
        dr.manage().window().maximize();
        dr.get("https://www.openstreetmap.org/#map=5/51.083/15.271");
    }
    @When("click on search button")
    public void clickOnSearchButton() {
        dr.findElement(By.xpath("//*[@id='sidebar']/div[1]/form[1]/div/div[2]/a/img")).click();
        dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }
    @Then("direction page opens")
    public void directionPageOpens() {
        assertTrue(dr.findElement(By.xpath("//*[@id='sidebar']/div[1]/form[2]")).isDisplayed(), "Element not found");
        dr.close();
    }

    @Given("user is on direction page")
    public void userIsOnDirectionPage() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Desktop\\Luxoft\\StreetMap\\src\\main\\resources\\chromedriver.exe");
        dr = new ChromeDriver();
        dr.manage().window().maximize();
        dr.get("https://www.openstreetmap.org/directions#map=5/51.083/15.271");
    }
    @When("enter direction points")
    public void enterDeparturePoint() {
        dr.findElement(By.xpath("/html/body/div/div[1]/div[1]/form[2]/div[2]/div[2]/input")).sendKeys("Радищева,Київ");
        dr.findElement(By.xpath("/html/body/div/div[1]/div[1]/form[2]/div[3]/div[2]/input")).sendKeys("Кембридж");
        dr.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
    }
    @And("input transport type")
    public void inputTransportType() {
        WebElement selectTransport = dr.findElement(By.xpath("/html/body/div/div[1]/div[1]/form[2]/div[4]/div[1]/select"));
        Select select = new Select(selectTransport);
        select.selectByVisibleText("Велосипед (OSRM)");
    }
    @And("click on Go Button")
    public void clickOnGoButton() {
        dr.findElement(By.xpath("//*[@id=\"sidebar\"]/div[1]/form[2]/div[4]/div[2]/input")).click();
    }
    @Then("distance is 2425 km")
    public void distanceIs2425Km() {
        String expectedText = "Відстань: 2425km";
        String actualText = dr.findElement(By.xpath("/html/body/div/div[1]/div[5]/p[1]")).getText();
        assertEquals("Відстань: 2425km", expectedText, actualText);
    }

    @After()
    public void closeBrowser() {
        dr.quit();
    }
}





