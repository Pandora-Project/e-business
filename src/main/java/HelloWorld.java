import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorld {
    private static final Logger logger = LoggerFactory.getLogger(HelloWorld.class);

    public static void main(String[] args) {
        System.out.println("Hello, World!");

        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:sample.db");
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS greetings (id INTEGER PRIMARY KEY, message TEXT)");
            stmt.execute("INSERT INTO greetings (message) VALUES ('Hello from SQLite!')");
            logger.info("Table created and data inserted successfully!");
            conn.close();
        } catch (Exception e) {
            logger.error("An error occurred: ", e);
        }
    }
}