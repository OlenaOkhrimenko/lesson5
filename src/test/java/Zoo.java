import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.time.Duration;
@Listeners({AllureTestNg.class})
public class Zoo {

    private WebDriver driver;

    @BeforeTest
    void setupDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        driver = new ChromeDriver();
        driver.get("https://zoolandia.com.ua/");
    }

    @Test
    public void testZoo() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement loginButton = driver.findElement(By.xpath("//a[contains(@class, 'navigation__link navigation__link--enter')]"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class, 'navigation__link navigation__link--enter')]")));
        loginButton.click();

        WebElement enterMail = driver.findElement(By.xpath("//input[@name='data[email]']"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='data[email]']")));
        enterMail.sendKeys("helen@gmail.com");

        WebElement enterPassword = driver.findElement(By.xpath("//input[@name='data[password]']"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='data[password]']")));
        enterPassword.sendKeys("Helen2024!");

        WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit']"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='submit']")));
        submitButton.click();

        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'has-error text-center')]")));
        String actualElementText = message.getText();

        String expectedElementText = "Невірний логін або пароль";

        Assert.assertEquals(actualElementText, expectedElementText,"Expected and Actual are the same");

    }

    @AfterTest
    void closeDriver(){
        driver.quit();
    }

}
