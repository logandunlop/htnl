import org.junit.Test;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

public class DBConnectionHTNLTest {

    @Test
    public void testSuccessfulConnection() {
        DBConnectionHTNL.getDBConnection();
        assertNotNull("Connection should not be null", DBConnectionHTNL.connection);
    }
    
    @Test
    public void testConnectionWithInvalidProperties() {
        // Manually set invalid properties
        System.setProperty("url", "invalid_url");
        System.setProperty("user", "invalid_user");
        System.setProperty("password", "invalid_password");

        // Attempt to establish a database connection
        DBConnectionHTNL.getDBConnection();

        // Assert that the connection was not established
        assertNull("Connection should be null due to invalid properties", DBConnectionHTNL.connection);
    }
}