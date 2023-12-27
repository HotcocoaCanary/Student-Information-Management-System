package ui;

import member.Student;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class StudentScreen extends JFrame {
    public StudentScreen(@NotNull Student p) {
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

        message.add(alter);
        message.add(flash);
        tool.add(back);
        tool.add(change);
        jMenuBar.add(message);
        jMenuBar.add(tool);
        jMenuBar.add(help);

        setJMenuBar(jMenuBar);

        JLabel name = new JLabel("姓  名：");
        JLabel nam = new JLabel(p.getName());
        JLabel sex = new JLabel("性  别：");
        JLabel s = new JLabel(p.getSex());
        JLabel college = new JLabel("学  院：");
        JLabel col = new JLabel(p.getCollege());
        JLabel gradeClass = new JLabel("年  级：");
        JLabel gra = new JLabel(p.getGradeClass());

        JPanel jPanel = new JPanel();
        add(jPanel, BorderLayout.CENTER);

        jPanel.setBounds(0, 0, 700, 500);
        jPanel.setLayout(null);
        jPanel.add(name);
        jPanel.add(nam);
        jPanel.add(sex);
        jPanel.add(s);
        jPanel.add(college);
        jPanel.add(col);
        jPanel.add(gradeClass);
        jPanel.add(gra);

        name.setBounds(100, 180, 50, 50);
        nam.setBounds(150, 180, 250, 50);
        sex.setBounds(100, 240, 50, 50);
        s.setBounds(170, 240, 100, 50);
        college.setBounds(100, 300, 50, 50);
        col.setBounds(150, 300, 250, 50);
        gradeClass.setBounds(100, 380, 50, 50);
        gra.setBounds(150, 380, 250, 50);

        setLayout(new BorderLayout());

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
                if(p.getName().isEmpty()||p.getCollege().isEmpty()||p.getGradeClass().isEmpty()){
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
