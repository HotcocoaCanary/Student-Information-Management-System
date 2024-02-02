package jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {
    private static String url;
    private static String username;
    private static String password;

    static {
        try (InputStream in = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc/db.properties")) {
            Properties properties = new Properties();
            properties.load(in);

            String driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");

            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动加载异常");
        } catch (IOException e) {
            System.out.println("数据库配置文件异常");
        }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
