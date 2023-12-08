import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DatabaseSearchHTNLTest {
	private WebDriver driver;

    @Before
    public void setUp() {
    	ChromeOptions options = new ChromeOptions();
    	options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe"); // Update this to the path where Chrome is installed
    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\Logan\\Documents\\Real World\\School Stuff\\Software Engineering\\workspace4830\\workspaceCSCI4830-groupproject\\htnl\\lib\\win\\chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void testEmptySearchResults() throws Exception {
        driver.get("http://localhost:8080/htnl/DatabaseSearchHTNL.html");
        Thread.sleep(1000);
        driver.findElement(By.name("search")).click();
        Thread.sleep(1000);
        assertTrue("The search should return all records.", driver.getPageSource().contains("ID:"));
    }
    
    @Test
    public void testSearchWithValidKeyword() throws Exception {
        driver.get("http://localhost:8080/htnl/DatabaseSearchHTNL.html");
        Thread.sleep(1000);
        driver.findElement(By.name("keyword")).sendKeys("English");
        Thread.sleep(1000);
        driver.findElement(By.name("search")).click();
        Thread.sleep(1000);
        assertTrue("The search should return relevant records.", driver.getPageSource().contains("English"));
    }
    
    @Test
    public void testSearchWithInvalidKeyword() throws Exception {
        driver.get("http://localhost:8080/htnl/DatabaseSearchHTNL.html");
        Thread.sleep(1000);
        driver.findElement(By.name("keyword")).sendKeys("Math");
        Thread.sleep(1000);
        driver.findElement(By.name("search")).click();
        Thread.sleep(1000);
        assertFalse("The search should not return any records.", driver.getPageSource().contains("ID:"));    
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}