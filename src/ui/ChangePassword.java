package ui;

import member.Student;

import javax.swing.*;
import java.util.Objects;

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
        JTextField cip1 = new JPasswordField(20);
        JLabel cipher2 = new JLabel("新 密 码：");
        JTextField cip2 = new JPasswordField(20);
        JLabel cipher3 = new JLabel("确认密码：");
        JTextField cip3 = new JPasswordField(20);

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
        jPanel.add(cipher2);
        jPanel.add(cip2);
        jPanel.add(cipher3);
        jPanel.add(cip3);
        jPanel.add(submit);
        jPanel.add(cancel);

        username.setBounds(80, 80, 70, 50);
        use.setBounds(170, 80, 250, 50);
        cipher1.setBounds(80, 140, 70, 50);
        cip1.setBounds(170, 140, 250, 50);
        cipher2.setBounds(80, 200, 70, 50);
        cip2.setBounds(170, 200, 250, 50);
        cipher3.setBounds(80, 260, 70, 50);
        cip3.setBounds(170, 260, 250, 50);

        submit.setBounds(100, 360, 100, 50);
        cancel.setBounds(300, 360, 100, 50);

        submit.addActionListener(e -> {
            if (Objects.equals(cip2.getText(), cip3.getText())) {
                if (new Student().find(s.getName()) != null) {
                    if (Objects.equals(new Student().find(s.getName()).getPassword(), cip1.getText())) {
                        if (new Student().ChangePassword(s, cip2.getText(), use.getText())) {
                            showMessageDialog(this, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                        }else{
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
        cancel.addActionListener(e -> {
            dispose();
        });
    }
}
