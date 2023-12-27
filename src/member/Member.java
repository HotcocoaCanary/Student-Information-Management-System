package member;

import jdbc.JDBCUtils;

import javax.swing.*;
import java.sql.*;
import java.util.Objects;

import static javax.swing.JOptionPane.showMessageDialog;

public interface Member {
    boolean SignIn(String username, String password);

    default boolean add(Student p) {
        try {
            Connection con = JDBCUtils.getConnection();
            Statement sql = con.createStatement();
            ResultSet set = sql.executeQuery("select * from mydatabase.student where username=" + "'" + p.getUsername() + "'");
            if (!set.next()) {
                sql.executeUpdate("insert into mydatabase.student values " + "(" + "'" + p.getUsername() + "'" + "," + "'" + p.getPassword() + "'" + "," + "'" + p.getName() + "'" + "," + "'" + p.getSex() + "'" + "," + "'" + p.getCollege() + "'" + "," + "'" + p.getGradeClass() + "'" + ")");
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    default boolean delete(String name) {
        try {
            Connection con = JDBCUtils.getConnection();
            Statement sql = con.createStatement();
            int row = sql.executeUpdate("delete from mydatabase.student where name = " + "'" + name + "'");
            return row != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    default Student find(String name) {
        try {
            Connection con = JDBCUtils.getConnection();
            Statement sql = con.createStatement();
            ResultSet set = sql.executeQuery("select * from mydatabase.student where name=" + "'" + name + "'");
            if (set.next()) {
                return new Student(set.getString("username"), set.getString("password"), set.getString("name"), set.getString("sex"), set.getString("college"), set.getString("gradeClass"));
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    default void alter(Student p) {
        try {
            Connection con = JDBCUtils.getConnection();
            Statement sql = con.createStatement();
            sql.executeUpdate("update mydatabase.student set name = " + "'" + p.getName() + "'" + " where username = " + "'" + p.getUsername() + "'");
            sql.executeUpdate("update mydatabase.student set gradeClass = " + "'" + p.getGradeClass() + "'" + " where username = " + "'" + p.getUsername() + "'");
            sql.executeUpdate("update mydatabase.student set college = " + "'" + p.getCollege() + "'" + " where username = " + "'" + p.getUsername() + "'");
            sql.executeUpdate("update mydatabase.student set sex = " + "'" + p.getSex() + "'" + " where username = " + "'" + p.getUsername() + "'");
        } catch (SQLException ex) {
            showMessageDialog(new JOptionPane(), "数据处理异常", "警告", JOptionPane.WARNING_MESSAGE);
        }
    }

    default String[][] print() {
        try {
            Connection con = JDBCUtils.getConnection();
            Statement sql = con.createStatement();
            ResultSet set = sql.executeQuery("select * from mydatabase.student");
            int size = 1;
            while (set.next()) {
                size++;
            }
            String[][] arr = new String[size - 1][4];
            set = sql.executeQuery("select * from mydatabase.student");
            while (set.next()) {
                arr[size - 2][0] = set.getString("name");
                arr[size - 2][1] = set.getString("sex");
                arr[size - 2][2] = set.getString("college");
                arr[size - 2][3] = set.getString("gradeClass");
                size--;
            }
            return arr;
        } catch (SQLException ex) {
            showMessageDialog(new JOptionPane(), "数据处理异常", "警告", JOptionPane.WARNING_MESSAGE);
        }
        return null;
    }

    default boolean ChangePassword(Student s, String password, String username) {
        try {
            Connection con = JDBCUtils.getConnection();
            Statement sql = con.createStatement();
            ResultSet set = sql.executeQuery("select * from mydatabase.student where username=" + "'" + username + "'");
            if (!set.next() || Objects.equals(s.getUsername(), username)) {
                sql.executeUpdate("update mydatabase.student set username = " + "'" + username + "'" + " where name = " + "'" + s.getName() + "'");
                sql.executeUpdate("update mydatabase.student set password = " + "'" + password + "'" + " where name = " + "'" + s.getName() + "'");
                return true;
            }
        } catch (SQLException e) {
            showMessageDialog(new JOptionPane(), "数据处理异常", "警告", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }
}
