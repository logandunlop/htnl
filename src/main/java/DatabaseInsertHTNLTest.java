import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DatabaseInsertHTNLTest {
	private WebDriver driver;

    @Before
    public void setUp() {
    	ChromeOptions options = new ChromeOptions();
    	options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe"); // Update this to the path where Chrome is installed
    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\Logan\\Documents\\Real World\\School Stuff\\Software Engineering\\workspace4830\\workspaceCSCI4830-groupproject\\htnl\\lib\\win\\chromedriver.exe");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @Test
    public void testEmptyInsert() throws Exception {
        driver.get("http://localhost:8080/htnl/DatabaseInsertHTNL.html");
        Thread.sleep(1000);
        driver.findElement(By.name("submit")).click();
        Thread.sleep(1000);
        assertFalse("The insert should add an empty user.", driver.getPageSource().contains("Name:Two"));
    }
    
    @Test
    public void testInsert() throws Exception {
        driver.get("http://localhost:8080/htnl/DatabaseInsertHTNL.html");
        Thread.sleep(200);
        driver.findElement(By.name("firstName")).sendKeys("Two");
        Thread.sleep(200);
        driver.findElement(By.name("lastName")).sendKeys("Test");
        Thread.sleep(200);
        driver.findElement(By.name("gender")).sendKeys("Male");
        Thread.sleep(200);
        driver.findElement(By.name("email")).sendKeys("two@email.com");
        Thread.sleep(200);
        driver.findElement(By.name("phone")).sendKeys("402-222-2222");
        Thread.sleep(200);
        driver.findElement(By.name("major")).sendKeys("Math");
        Thread.sleep(200);
        driver.findElement(By.name("graduation")).sendKeys("2025");
        Thread.sleep(200);
        driver.findElement(By.name("hobbies")).sendKeys("Swimming");
        Thread.sleep(200);
        driver.findElement(By.name("nuid")).sendKeys("22222222");
        Thread.sleep(200);
        driver.findElement(By.name("submit")).click();
        Thread.sleep(200);
        assertTrue("The insert should add a user.", driver.getPageSource().contains("Two"));
    }
    
    @Test
    public void testInsertToSearch() throws Exception {
        driver.get("http://localhost:8080/htnl/DatabaseInsertHTNL.html");
        Thread.sleep(1000);
        driver.findElement(By.name("search")).click();
        Thread.sleep(1000);
        assertTrue("The search page should open.", driver.getPageSource().contains("Search Data"));    
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}