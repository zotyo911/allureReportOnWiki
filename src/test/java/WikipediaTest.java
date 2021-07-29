import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class WikipediaTest {

    private WebDriver driver;
    private final String URL = "https://en.wikipedia.org/";

    @BeforeEach
    public void Setup() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void loginToWikipedia(){
        driver.get(URL);
        driver.manage().window().maximize();
        String uname = "zolibafreemail";
        String pssword = "AB1234567890#";
        WebElement login = driver.findElement(By.xpath("//*[@id=\"pt-login\"]/a"));
        login.click();
        WebElement userName = driver.findElement(By.id("wpName1"));
        WebElement passWord = driver.findElement(By.id("wpPassword1"));
        WebElement loggedInCheckBox = driver.findElement(By.id("wpRemember"));
        WebElement logInButton = driver.findElement(By.xpath("//*[@id=\"wpLoginAttempt\"]"));
        userName.sendKeys(uname);
        passWord.sendKeys(pssword);
        loggedInCheckBox.click();
        logInButton.click();

        Assertions.assertEquals("Zolibafreemail", driver.findElement(By.cssSelector("#pt-userpage > a")).getText());
    }

    @Test
    public void searchSeleniumAndGoBack(){
        loginToWikipedia();
        WebElement searchWikiInput = driver.findElement(By.xpath("//*[@id=\"searchInput\"]"));
        searchWikiInput.sendKeys("Selenium");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement selenium = driver.findElement(By.linkText("Selenium (software)"));
        selenium.click();
        String currentPageUrl = driver.getCurrentUrl();

        Assertions.assertEquals(currentPageUrl, "https://en.wikipedia.org/wiki/Selenium_(software)");

        driver.navigate().back();
        String goBackPageUrl = driver.getCurrentUrl();
        Assertions.assertEquals(goBackPageUrl, "https://en.wikipedia.org/wiki/Main_Page");
    }

    @Test
    public void searchFramework(){
        loginToWikipedia();
        WebElement searchFrameworkInput = driver.findElement(By.xpath("//*[@id=\"searchInput\"]"));
        searchFrameworkInput.sendKeys("Framework");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement framework = driver.findElement(By.xpath("//*[@id=\"searchButton\"]"));
        framework.click();

        String actualURL = driver.getCurrentUrl();
        Assertions.assertEquals(actualURL, "https://en.wikipedia.org/wiki/Framework");

    }

    @Test
    public void settingFontSize(){
        loginToWikipedia();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement setLanguageButton = driver.findElement(By.xpath("//*[@id=\"p-lang\"]/button"));
        setLanguageButton.click();
        //    driver.switchTo().frame("language-settings-dialog");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id=\"display-panel-trigger\"]")).click();
        driver.findElement(By.id("uls-display-settings-fonts-tab")).click();
        //    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement tickCheckBox = driver.findElement(By.xpath("//*[@id=\"languagesettings-settings-panel\"]/div/div[3]/div[3]/div/div/label"));
        tickCheckBox.click();
        //    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement systemFontInput = driver.findElement(By.id("content-font-selector"));
        systemFontInput.click();
        //    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        WebElement setComicNeueStyle = driver.findElement(By.xpath("//*[@id=\"content-font-selector\"]/option[1]"));
        setComicNeueStyle.click();
        //    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        WebElement applyButton = driver.findElement(By.xpath("//*[@id=\"language-settings-dialog\"]/div[3]/div/button[2]"));
        applyButton.click();

    }

    @Test
    public void searchSeleniumGrid() {
        loginToWikipedia();
        WebElement searchWikiInput = driver.findElement(By.xpath("//*[@id=\"searchInput\"]"));
        searchWikiInput.sendKeys("Selenium");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement selenium = driver.findElement(By.linkText("Selenium (software)"));
        selenium.click();
        WebElement foundSeleniumGrid = driver.findElement(By.xpath("//*[@id=\"toc\"]/ul/li[2]/ul/li[5]/a"));
        foundSeleniumGrid.click();

        String gridURL = driver.getCurrentUrl();
        Assertions.assertEquals(gridURL, "https://en.wikipedia.org/wiki/Selenium_(software)#Selenium_Grid");
    }

    @Test
    public void testWikipedia() {

    }

    @AfterEach
    public void closeWebDriver() {
        driver.close();
    }
}
