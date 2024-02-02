package member;

import jdbc.JDBCUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;

import static javax.swing.JOptionPane.showMessageDialog;

public interface Member {
    boolean SignIn(String username, String password);

    default boolean add(Student p) {
        try {
            Connection con = JDBCUtils.getConnection();
            Statement sql = con.createStatement();
            ResultSet set = sql.executeQuery("select * from simsdb.student where username=" + "'" + p.getUsername() + "'");
            if (!set.next()) {
                sql.executeUpdate("insert into simsdb.student values " + "(" + "'" + p.getUsername() + "'" + "," + "'" + p.getPassword() + "'" + "," + "'" + p.getName() + "'" + "," + "'" + p.getSex() + "'" + "," + "'" + p.getCollege() + "'" + "," + "'" + p.getGradeClass() + "'" + ")");
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
            int row = sql.executeUpdate("delete from simsdb.student where name = " + "'" + name + "'");
            return row != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    default Student find(String name) {
        try {
            Connection con = JDBCUtils.getConnection();
            Statement sql = con.createStatement();
            ResultSet set = sql.executeQuery("select * from simsdb.student where name=" + "'" + name + "'");
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
            sql.executeUpdate("update simsdb.student set name = " + "'" + p.getName() + "'" + " where username = " + "'" + p.getUsername() + "'");
            sql.executeUpdate("update simsdb.student set gradeClass = " + "'" + p.getGradeClass() + "'" + " where username = " + "'" + p.getUsername() + "'");
            sql.executeUpdate("update simsdb.student set college = " + "'" + p.getCollege() + "'" + " where username = " + "'" + p.getUsername() + "'");
            sql.executeUpdate("update simsdb.student set sex = " + "'" + p.getSex() + "'" + " where username = " + "'" + p.getUsername() + "'");
        } catch (SQLException ex) {
            showMessageDialog(new JOptionPane(), "数据处理异常", "警告", JOptionPane.WARNING_MESSAGE);
        }
    }

    default String[][] print() {
        try {
            Connection con = JDBCUtils.getConnection();
            Statement sql = con.createStatement();
            ResultSet set = sql.executeQuery("select * from simsdb.student");
            int size = 1;
            while (set.next()) {
                size++;
            }
            String[][] arr = new String[size - 1][4];
            set = sql.executeQuery("select * from simsdb.student");
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
            ResultSet set = sql.executeQuery("select * from simsdb.student where username=" + "'" + username + "'");
            if (!set.next() || Objects.equals(s.getUsername(), username)) {
                sql.executeUpdate("update simsdb.student set username = " + "'" + username + "'" + " where name = " + "'" + s.getName() + "'");
                sql.executeUpdate("update simsdb.student set password = " + "'" + password + "'" + " where name = " + "'" + s.getName() + "'");
                return true;
            }
        } catch (SQLException e) {
            showMessageDialog(new JOptionPane(), "数据处理异常", "警告", JOptionPane.WARNING_MESSAGE);
        }
        return false;
    }
    default void exportToExcel(JTable table, String filePath) {
        new Thread(() -> {
            TableModel model = table.getModel();
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("学生信息管理系统");

            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < model.getColumnCount(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(model.getColumnName(i));
            }

            for (int i = 0; i < model.getRowCount(); i++) {
                Row row = sheet.createRow(i + 1);
                for (int j = 0; j < model.getColumnCount(); j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(model.getValueAt(i, j).toString());
                }
            }
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
                fileOut.flush();
                workbook.close();
            } catch (IOException e) {
                showMessageDialog(new JOptionPane(), "找不到文件或文件无法打开", "警告", JOptionPane.WARNING_MESSAGE);
            }
        }).start();
    }
}
