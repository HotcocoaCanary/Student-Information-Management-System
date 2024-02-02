package ui;

import member.Student;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;

import static javax.swing.JOptionPane.showMessageDialog;

public class ChangePassword extends JFrame {
    public ChangePassword(Student s) {
        setTitle("更改密码");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(600, 150, 500, 700);

        JLabel username = new JLabel("用 户 名：");
        JTextField use = new JTextField(s.getUsername());
        JLabel cipher1 = new JLabel("原 密 码：");
        JPasswordField cip1 = new JPasswordField(20);
        cip1.setEchoChar('*');
        JButton show1 = new JButton("\uD83D\uDC41");
        JLabel cipher2 = new JLabel("新 密 码：");
        JPasswordField cip2 = new JPasswordField(20);
        cip2.setEchoChar('*');
        JButton show2 = new JButton("\uD83D\uDC41");
        JLabel cipher3 = new JLabel("确认密码：");
        JPasswordField cip3 = new JPasswordField(20);
        cip3.setEchoChar('*');
        JButton show3 = new JButton("\uD83D\uDC41");

        JButton submit = new JButton("提交");
        JButton cancel = new JButton("取消");

        JPanel jPanel = new JPanel();
        add(jPanel);

        jPanel.setBounds(0, 0, 700, 500);
        jPanel.setLayout(null);
        jPanel.add(username);
        jPanel.add(use);
        jPanel.add(cipher1);
        jPanel.add(cip1);
        jPanel.add(show1);
        jPanel.add(cipher2);
        jPanel.add(cip2);
        jPanel.add(show2);
        jPanel.add(cipher3);
        jPanel.add(cip3);
        jPanel.add(show3);
        jPanel.add(submit);
        jPanel.add(cancel);

        username.setBounds(80, 80, 70, 50);
        use.setBounds(170, 80, 250, 50);
        cipher1.setBounds(80, 140, 70, 50);
        cip1.setBounds(170, 140, 200, 50);
        show1.setBounds(370,140,50,50);
        cipher2.setBounds(80, 200, 70, 50);
        cip2.setBounds(170, 200, 200, 50);
        show2.setBounds(370,200,50,50);
        cipher3.setBounds(80, 260, 70, 50);
        cip3.setBounds(170, 260, 200, 50);
        show3.setBounds(370,260,50,50);

        submit.setBounds(100, 360, 100, 50);
        cancel.setBounds(300, 360, 100, 50);

        submit.addActionListener(e -> {
            if (Arrays.equals(cip2.getPassword(), cip3.getPassword())) {
                if (new Student().find(s.getName()) != null) {
                    if (Arrays.equals(new Student().find(s.getName()).getPassword().toCharArray(), cip1.getPassword())) {
                        if (new Student().ChangePassword(s, new String(cip2.getPassword()), use.getText())) {
                            showMessageDialog(this, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                        } else {
                            showMessageDialog(this, "用户名重复", "错误", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        showMessageDialog(this, "原密码错误", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    showMessageDialog(this, "未查找到该学生", "警告", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                showMessageDialog(this, "所输入密码不同", "警告", JOptionPane.WARNING_MESSAGE);
            }
        });
        cancel.addActionListener(e -> dispose());
        show1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cip1.setEchoChar('*');
            }

            @Override
            public void mousePressed(MouseEvent e) {
                cip1.setEchoChar((char) 0);
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
        show2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cip2.setEchoChar('*');
            }

            @Override
            public void mousePressed(MouseEvent e) {
                cip2.setEchoChar((char) 0);
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
        show3.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cip3.setEchoChar('*');
            }

            @Override
            public void mousePressed(MouseEvent e) {
                cip3.setEchoChar((char) 0);
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
