import java.sql.Connection;
import java.sql.SQLException;

public interface CrudOperations {
    void create();
    void read();
    void update();
    void delete();
    Connection connection() throws SQLException;
}