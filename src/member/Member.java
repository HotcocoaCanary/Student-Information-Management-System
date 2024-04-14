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

import static javax.swing.JOptionPane.showMessageDialog;

public interface Member {
    boolean SignIn(String username, String password);

    default boolean add(Student p) {
        try (Connection con = JDBCUtils.getConnection();
             PreparedStatement statement = con.prepareStatement("SELECT * FROM simsdb.student WHERE username = ?");
             ResultSet resultSet = statement.executeQuery()) {

            statement.setString(1, p.getUsername());
            if (!resultSet.next()) {
                try (PreparedStatement insertStatement = con.prepareStatement("INSERT INTO simsdb.student VALUES (?, ?, ?, ?, ?, ?)")) {
                    insertStatement.setString(1, p.getUsername());
                    insertStatement.setString(2, p.getPassword());
                    insertStatement.setString(3, p.getName());
                    insertStatement.setString(4, p.getSex());
                    insertStatement.setString(5, p.getCollege());
                    insertStatement.setString(6, p.getGradeClass());
                    insertStatement.executeUpdate();
                }
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    default boolean delete(String name) {
        try (Connection con = JDBCUtils.getConnection();
             PreparedStatement statement = con.prepareStatement("DELETE FROM simsdb.student WHERE name = ?")) {
            statement.setString(1, name);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    default Student find(String name) {
        try (Connection con = JDBCUtils.getConnection();
             PreparedStatement statement = con.prepareStatement("SELECT * FROM simsdb.student WHERE name = ?")) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Student(
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("name"),
                            resultSet.getString("sex"),
                            resultSet.getString("college"),
                            resultSet.getString("gradeClass")
                    );
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    default void alter(Student p) {
        try (Connection con = JDBCUtils.getConnection();
             PreparedStatement statement = con.prepareStatement("UPDATE simsdb.student SET name = ?, gradeClass = ?, college = ?, sex = ? WHERE username = ?")) {

            statement.setString(1, p.getName());
            statement.setString(2, p.getGradeClass());
            statement.setString(3, p.getCollege());
            statement.setString(4, p.getSex());
            statement.setString(5, p.getUsername());

            statement.executeUpdate();
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

    default boolean changePassword(Student s, String password, String username) {
        try (Connection con = JDBCUtils.getConnection();
             PreparedStatement statement = con.prepareStatement("UPDATE simsdb.student SET username = ?, password = ? WHERE name = ?")) {

            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, s.getName());

            statement.executeUpdate();
            return true;
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
