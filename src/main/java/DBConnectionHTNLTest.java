import org.junit.Test;
import static org.junit.Assert.assertNotNull;

public class DBConnectionHTNLTest {

    @Test
    public void testSuccessfulConnection() {
        DBConnectionHTNL.getDBConnection();
        assertNotNull("Connection should not be null", DBConnectionHTNL.connection);
    }
}