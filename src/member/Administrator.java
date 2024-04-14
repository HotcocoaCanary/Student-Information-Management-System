package member;

import jdbc.JDBCUtils;
import ui.AdministratorScreen;

import javax.swing.*;
import java.sql.*;

import static javax.swing.JOptionPane.showMessageDialog;

public class Administrator extends Student implements Member {
    private Connection con;

    public Administrator(String username, String password) {
        super(username, password);
        initAdministrator();
    }

    public Administrator() {
        super();
        initAdministrator();
    }

    private void initAdministrator() {
        try {
            con = JDBCUtils.getConnection();
        } catch (SQLException e) {
            showMessageDialog(new JOptionPane(), "数据库异常", "警告", JOptionPane.WARNING_MESSAGE);
        }
    }

    public boolean SignIn(String username, String password) {
        try {
            String query = "SELECT * FROM simsdb.administrator WHERE username = ? AND password = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                new AdministratorScreen(new Administrator(set.getString("username"), set.getString("password")));
                return true;
            }
        } catch (SQLException ex) {
            showMessageDialog(new JOptionPane(), "数据处理异常", "警告", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }
}
