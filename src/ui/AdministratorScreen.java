package ui;

import member.Administrator;
import member.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import static javax.swing.JOptionPane.showMessageDialog;

public class AdministratorScreen extends JFrame {
    public AdministratorScreen(Administrator p) {
        setTitle("学生信息管理系统管理员面板");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JMenuBar jMenuBar = new JMenuBar();

        JMenu tool = new JMenu("工具");
        JMenu help = new JMenu("帮助");

        JMenuItem alter = new JMenuItem("修改学生信息");
        JMenuItem change = new JMenuItem("修改学生密码");
        JMenuItem enroll=new JMenuItem("注册学生信息");
        JMenuItem exportToExcel=new JMenuItem("导出到Excel");
        JMenuItem back = new JMenuItem("退出登录");
        JMenuItem doc = new JMenuItem("帮助文档");

        tool.add(alter);
        tool.add(back);
        tool.add(change);
        tool.add(enroll);
        tool.add(exportToExcel);
        help.add(doc);
        jMenuBar.add(tool);
        jMenuBar.add(help);

        setJMenuBar(jMenuBar);

        setLayout(new BorderLayout());

        String[] arrName = {"姓名", "性别", "学院", "年级班级"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(new Administrator().print(), arrName);
        JTable jTable = new JTable(defaultTableModel);
        JScrollPane jScrollPane = new JScrollPane(jTable);
        add(jScrollPane, BorderLayout.CENTER);

        JPanel right = new JPanel();
        JPanel top = new JPanel();
        add(right, BorderLayout.EAST);
        add(top, BorderLayout.WEST);

        right.setLayout(new GridLayout(2, 1, 10, 10));
        JButton delete = new JButton("删除");
        JButton fresh = new JButton("刷新");
        right.add(fresh);
        right.add(delete);

        top.setLayout(new GridLayout(9,1,20,20));
        JLabel name = new JLabel("姓   名：");
        JTextField jtf5 = new JTextField(20);
        JLabel sex = new JLabel("性   别：");
        JTextField jtf6 = new JTextField(20);
        JLabel college = new JLabel("学   院：");
        JTextField jtf7 = new JTextField(20);
        JLabel gradeClass = new JLabel("年级班级：");
        JTextField jtf8 = new JTextField(20);
        JButton edit = new JButton("修改");
        top.add(name);
        top.add(jtf5);
        top.add(sex);
        top.add(jtf6);
        top.add(college);
        top.add(jtf7);
        top.add(gradeClass);
        top.add(jtf8);
        top.add(edit);

        doc.addActionListener(e -> {
            try {
                Desktop.getDesktop().open(new File("E:\\Code\\Student Information Management System\\src\\help\\Administrator.html"));
            } catch (IOException ex) {
                showMessageDialog(this, "文档异常或不支持", "警告", JOptionPane.WARNING_MESSAGE);
            }
        });
        exportToExcel.addActionListener(e-> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int val = fileChooser.showOpenDialog(exportToExcel);
            if (val == JFileChooser.APPROVE_OPTION) {
                new Administrator().exportToExcel(jTable, fileChooser.getSelectedFile().toString()+"/student.xlsx");
            }
        });
        fresh.addActionListener(e -> defaultTableModel.setDataVector(new Administrator().print(),arrName));
        alter.addActionListener(e -> {
            if (new Student().find(jtf5.getText()) != null) {
                new Alter(new Student().find(jtf5.getText()));
            } else {
                showMessageDialog(this, "未查找到该学生", "警告", JOptionPane.ERROR_MESSAGE);
            }
        });
        back.addActionListener(e -> {
            dispose();
            new LogIn();
        });
        jTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jtf5.setText((String) defaultTableModel.getValueAt(jTable.getSelectedRow(), 0));
                jtf6.setText((String) defaultTableModel.getValueAt(jTable.getSelectedRow(), 1));
                jtf7.setText((String) defaultTableModel.getValueAt(jTable.getSelectedRow(), 2));
                jtf8.setText((String) defaultTableModel.getValueAt(jTable.getSelectedRow(), 3));
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        edit.addActionListener(e -> {
            defaultTableModel.setValueAt(jtf5.getText(), jTable.getSelectedRow(), 0);
            defaultTableModel.setValueAt(jtf6.getText(), jTable.getSelectedRow(), 1);
            defaultTableModel.setValueAt(jtf7.getText(), jTable.getSelectedRow(), 2);
            defaultTableModel.setValueAt(jtf8.getText(), jTable.getSelectedRow(), 3);
            Student s = new Administrator().find(jtf5.getText());
            if (s != null) {
                new Student().alter(new Student(s.getUsername(), s.getPassword(), jtf5.getText(), jtf6.getText(), jtf7.getText(), jtf8.getText()));
                showMessageDialog(this, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
            } else {
                showMessageDialog(this, "未查找到该学生", "警告", JOptionPane.WARNING_MESSAGE);
            }
        });
        delete.addActionListener(e -> {
            defaultTableModel.removeRow(jTable.getSelectedRow());
            if (jtf5.getText() != null) {
                if (p.delete(jtf5.getText())) {
                    showMessageDialog(this, "删除成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    showMessageDialog(this, "未找到所删除学生", "警告", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                showMessageDialog(this, "请输入或选中所需删除的学生", "错误", JOptionPane.ERROR_MESSAGE);
            }
            jtf5.setText(null);
            jtf6.setText(null);
            jtf7.setText(null);
            jtf8.setText(null);
        });
        change.addActionListener(e -> {
            if (new Student().find(jtf5.getText()) != null) {
                new ChangePassword(new Student().find(jtf5.getText()));
            } else {
                showMessageDialog(this, "未查找到该学生", "警告", JOptionPane.WARNING_MESSAGE);
            }
        });
        enroll.addActionListener(e->{
            new Enroll();
            defaultTableModel.setDataVector(new Administrator().print(),arrName);
        });
    }
}
