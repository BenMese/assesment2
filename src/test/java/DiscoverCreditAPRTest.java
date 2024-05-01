import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DiscoverCreditAPRTest {

    private WebDriver driver;
    private final String expectedAPR = "Over 20%"; // Adjust if the displayed format changes

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver"); // Replace with your ChromeDriver path
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void verifyCashAdvanceAPR() throws InterruptedException {
        // Replace with the actual Discover Credit Cards URL (avoid public sharing)
        driver.get("https://www.discover.com/credit-cards");

        // Click on "Credit Cards" icon (assuming it's a link or button)
        driver.findElement(By.id("credit-cards-link")).click(); // Adjust selector as needed

        // Click "Apply Now" for Secured Credit Card
        driver.findElement(By.xpath("//a[contains(text(), 'Secured Credit Card')]//following::button[text()='Apply Now']")).click();

        // Skip pre-fill step
        driver.findElement(By.linkText("Skip")).click(); // Adjust selector if needed

        // Wait for APR details to load (adjust timeout if needed)
        Thread.sleep(5000);

        String cashAdvanceAPR = driver.findElement(By.xpath("//span[text()='Cash Advance APR:']/following-sibling::span")).getText();

        Assert.assertTrue(cashAdvanceAPR.contains(expectedAPR), "Cash Advance APR is not greater than 20%");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
