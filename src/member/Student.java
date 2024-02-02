package member;

import jdbc.JDBCUtils;
import ui.StudentScreen;

import javax.swing.*;
import java.sql.*;

import static javax.swing.JOptionPane.showMessageDialog;

public class Student implements Member {
    private Connection con;
    private Statement sql;
    private String username;
    private String password;
    private String name;
    private String sex;
    private String college;
    private String gradeClass;

    public Student(String username, String password, String name, String sex, String college, String gradeClass) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.college = college;
        this.gradeClass = gradeClass;
        try {
            con = JDBCUtils.getConnection();
            sql = con.createStatement();
        } catch (SQLException e) {
            showMessageDialog(new JOptionPane(), "数据库异常", "警告", JOptionPane.WARNING_MESSAGE);
        }
    }

    public Student() {
        super();
        try {
            con = JDBCUtils.getConnection();
            sql = con.createStatement();
        } catch (SQLException e) {
            showMessageDialog(new JOptionPane(), "数据库异常", "警告", JOptionPane.WARNING_MESSAGE);
        }
    }

    public Student(String username, String password) {
        this.username = username;
        this.password = password;
        try {
            con = JDBCUtils.getConnection();
            sql = con.createStatement();
        } catch (SQLException e) {
            showMessageDialog(new JOptionPane(), "数据库异常", "警告", JOptionPane.WARNING_MESSAGE);
        }
    }

    public boolean SignIn(String username, String password) {
        try {
            String query = "SELECT * FROM simsdb.student WHERE username = ? AND password = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                new StudentScreen(new Student(set.getString("username"), set.getString("password"), set.getString("name"), set.getString("sex"), set.getString("college"), set.getString("gradeClass")));
                return true;
            }
        } catch (SQLException ex) {
            showMessageDialog(new JOptionPane(), "数据处理异常", "警告", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getCollege() {
        return college;
    }

    public String getGradeClass() {
        return gradeClass;
    }
}
