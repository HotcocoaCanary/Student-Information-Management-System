package ui;

import member.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static javax.swing.JOptionPane.showMessageDialog;

public class StudentScreen extends JFrame {
    public StudentScreen(Student p) {
        setTitle("学生信息管理系统");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JMenuBar jMenuBar = new JMenuBar();

        JMenu message = new JMenu("我的信息");
        JMenu tool = new JMenu("工具");
        JMenu help = new JMenu("帮助");

        JMenuItem alter = new JMenuItem("修改");
        JMenuItem flash = new JMenuItem("刷新");
        JMenuItem back = new JMenuItem("退出登录");
        JMenuItem change = new JMenuItem("修改密码");
        JMenuItem doc = new JMenuItem("帮助文档");

        message.add(alter);
        message.add(flash);
        tool.add(back);
        tool.add(change);
        help.add(doc);
        jMenuBar.add(message);
        jMenuBar.add(tool);
        jMenuBar.add(help);

        setJMenuBar(jMenuBar);

        JLabel name = new JLabel("姓  名：");
        JTextField nam = new JTextField(p.getName());
        JLabel sex = new JLabel("性  别：");
        ButtonGroup group = new ButtonGroup();
        JRadioButton sex1 = new JRadioButton("男");
        JRadioButton sex2 = new JRadioButton("女");
        if (Objects.equals(p.getSex(), "男")) {
            sex1 = new JRadioButton("男", true);
        } else {
            sex2 = new JRadioButton("女", true);
        }
        group.add(sex1);
        group.add(sex2);
        JLabel college = new JLabel("学  院：");
        JTextField col = new JTextField(p.getCollege());
        JLabel gradeClass = new JLabel("年  级：");
        JTextField gra = new JTextField(p.getGradeClass());
        JButton edit = new JButton("修    改");

        JPanel jPanel = new JPanel();
        add(jPanel, BorderLayout.CENTER);

        jPanel.setBounds(0, 0, 700, 500);
        jPanel.setLayout(null);
        jPanel.add(name);
        jPanel.add(nam);
        jPanel.add(sex);
        jPanel.add(sex1);
        jPanel.add(sex2);
        jPanel.add(college);
        jPanel.add(col);
        jPanel.add(gradeClass);
        jPanel.add(gra);
        jPanel.add(edit);

        name.setBounds(100, 180, 50, 50);
        nam.setBounds(150, 180, 250, 50);
        sex.setBounds(100, 240, 50, 50);
        sex1.setBounds(150, 240, 60, 50);
        sex2.setBounds(260, 240, 60, 50);
        college.setBounds(100, 300, 50, 50);
        col.setBounds(150, 300, 250, 50);
        gradeClass.setBounds(100, 380, 50, 50);
        gra.setBounds(150, 380, 250, 50);
        edit.setBounds(100, 440, 300, 50);

        setLayout(new BorderLayout());

        doc.addActionListener(e -> {
            try {
                Desktop.getDesktop().open(new File("E:\\Code\\Student Information Management System\\src\\help\\Student.html"));
            } catch (IOException ex) {
                showMessageDialog(this, "文档异常或不支持", "警告", JOptionPane.WARNING_MESSAGE);
            }
        });
        String s = sex1.isSelected() ? sex1.getText() : sex2.getText();
        edit.addActionListener(e -> {
            new Student().alter(new Student(p.getUsername(), p.getPassword(), nam.getText(), s, col.getText(), gra.getText()));
            showMessageDialog(this, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
        });
        back.addActionListener(e -> {
            dispose();
            new LogIn();
        });
        change.addActionListener(e -> new ChangePassword(p));
        flash.addActionListener(e -> {
            new Student().SignIn(p.getUsername(), p.getPassword());
            dispose();
        });
        alter.addActionListener(e -> new Alter(new Student(p.getUsername(), p.getPassword(), p.getName(), p.getSex(), p.getCollege(), p.getGradeClass())));
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                if (p.getName().isEmpty() || p.getCollege().isEmpty() || p.getGradeClass().isEmpty()) {
                    showMessageDialog(new Alter(new Student(p.getUsername(), p.getPassword(), p.getName(), p.getSex(), p.getCollege(), p.getGradeClass())), "请完善个人信息", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            @Override
            public void windowClosing(WindowEvent e) {

            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }
}
