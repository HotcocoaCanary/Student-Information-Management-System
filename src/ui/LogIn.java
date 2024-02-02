package ui;

import javax.swing.*;

import member.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class LogIn extends JFrame {
    public LogIn() {
        setTitle("学生管理系统");
        setBounds(500, 300, 700, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel username = new JLabel("用户名：");
        JLabel cipher = new JLabel("密  码：");
        JTextField use = new JTextField(20);
        JPasswordField cip = new JPasswordField(20);
        cip.setEchoChar('*');
        JButton show = new JButton("\uD83D\uDC41");
        JButton logIn = new JButton("登录");
        JButton enroll = new JButton("注册");
        JButton exit = new JButton("退出");
        ButtonGroup group = new ButtonGroup();
        JRadioButton student = new JRadioButton("我是学生");
        JRadioButton administrator = new JRadioButton("我是管理员");
        group.add(student);
        group.add(administrator);

        JPanel jPanel = new JPanel();
        add(jPanel);

        jPanel.setBounds(0, 0, 700, 500);
        jPanel.setLayout(null);
        jPanel.add(student);
        jPanel.add(administrator);
        jPanel.add(username);
        jPanel.add(use);
        jPanel.add(cipher);
        jPanel.add(cip);
        jPanel.add(show);
        jPanel.add(logIn);
        jPanel.add(enroll);
        jPanel.add(exit);

        student.setBounds(225, 50, 100, 50);
        administrator.setBounds(400, 50, 100, 50);
        username.setBounds(200, 120, 50, 50);
        use.setBounds(250, 120, 250, 50);
        cipher.setBounds(200, 223, 50, 50);
        cip.setBounds(250, 223, 200, 50);
        show.setBounds(450, 223, 50, 50);
        logIn.setBounds(230, 350, 70, 50);
        enroll.setBounds(315, 350, 70, 50);
        exit.setBounds(400, 350, 70, 50);

        logIn.addActionListener(e -> {
            if (student.isSelected()) {
                if (!new Student().SignIn(use.getText(), cip.getText())) {
                    dispose();
                    new LogIn();
                    showMessageDialog(this, "账户或密码错误", "错误", JOptionPane.ERROR_MESSAGE);
                } else {
                    dispose();
                }
            } else if (administrator.isSelected()) {
                if (!new Administrator().SignIn(use.getText(), cip.getText())) {
                    dispose();
                    new LogIn();
                    showMessageDialog(this, "账户或密码错误", "错误", JOptionPane.ERROR_MESSAGE);
                } else {
                    dispose();
                }
            } else {
                showMessageDialog(this, "请选择登录用户类型", "错误", JOptionPane.ERROR_MESSAGE);
            }
        });
        enroll.addActionListener(e -> {
            if (administrator.isSelected()) {
                showMessageDialog(this, "管理员不允许注册，请寻求超级管理员的帮助", "警告", JOptionPane.WARNING_MESSAGE);
            } else if (student.isSelected()) {
                new Enroll();
            } else {
                showMessageDialog(this, "请选择注册用户类型", "错误", JOptionPane.ERROR_MESSAGE);
            }
        });
        exit.addActionListener(e -> dispose());
        show.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cip.setEchoChar('*');
            }

            @Override
            public void mousePressed(MouseEvent e) {
                cip.setEchoChar((char) 0);
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
    }
}
