package member;

import jdbc.JDBCUtils;
import ui.AdministratorScreen;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static javax.swing.JOptionPane.showMessageDialog;

public class Administrator extends Student implements Member {
    private Statement sql;

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
            Connection con = JDBCUtils.getConnection();
            sql = con.createStatement();
        } catch (SQLException e) {
            showMessageDialog(new JOptionPane(), "数据库异常", "警告", JOptionPane.WARNING_MESSAGE);
        }
    }

    public boolean SignIn(String username, String password) {
        try {
            ResultSet set = sql.executeQuery("select * from mydatabase.administrator where username=" + "'" + username + "'" + " and password=" + "'" + password + "'");
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
