import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DatabaseDeleteHTNLTest {
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
    public void testEmptyDelete() throws Exception {
        driver.get("http://localhost:8080/htnl/DatabaseDeleteHTNL.html");
        Thread.sleep(1000);
        driver.findElement(By.name("submit")).click();
        Thread.sleep(1000);
        assertTrue("The delete should delete all empty students.", driver.getPageSource().contains("Deleted Student Number"));
    }
    
    @Test
    public void testDelete() throws Exception {
        driver.get("http://localhost:8080/htnl/DatabaseDeleteHTNL.html");
        Thread.sleep(1000);
        driver.findElement(By.name("key")).sendKeys("22222222");
        Thread.sleep(200);
        driver.findElement(By.name("submit")).click();
        Thread.sleep(1000);
        assertTrue("The delete should delete student number 22222222.", driver.getPageSource().contains("Deleted Student Number 22222222"));
    }
    
    @Test
    public void testDeleteToInsert() throws Exception {
        driver.get("http://localhost:8080/htnl/DatabaseDeleteHTNL.html");
        Thread.sleep(1000);
        driver.findElement(By.name("insert")).click();
        Thread.sleep(1000);
        assertTrue("The search page should open.", driver.getPageSource().contains("Search Data"));    
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}