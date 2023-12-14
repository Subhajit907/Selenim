package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

public class FirstTest {

    public static void main(String[] args) {
        // Setting the path for the Driver
        System.setProperty("webdriver.gecko.driver", "C:\\Selenium WebDriver\\FirefoxDriver\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();

        try {
            // Invoking the browser for loading the web page
            driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");

            // Maximizing the Window
            driver.manage().window().maximize();

            // Adding Explicit Wait for the Table Data
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//text()[contains(.,'Table Data')]/parent::summary"))).click();

            // Adding Explicit Wait for the textarea
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//textarea[@id='jsondata']"))).clear();

            // Read JSON data from file
            String jsonFilePath = "C:\\Users\\Subhajit\\eclipse-workspace\\Selenium4Project\\JsonFile\\Data.json";
            String jsonData = readJsonFromFile(jsonFilePath);

            // Adding values to the table using the textarea
            driver.findElement(By.xpath("//textarea[@id='jsondata']")).sendKeys(jsonData);
            
           // Adding Explicit Wait for the Table Data
            WebDriverWait waiting = new WebDriverWait(driver, Duration.ofSeconds(20));
            
           // Clicking on Refreshing button
            driver.findElement(By.xpath("//button[@id=\"refreshtable\"]")).click();
        } finally {
            // Close the browser session
            driver.quit();
        }
    }

    private static String readJsonFromFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
