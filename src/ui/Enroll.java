package ui;

import member.Student;

import javax.swing.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class Enroll extends JFrame {
    public Enroll() {
        setBounds(500, 300, 700, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel username = new JLabel("用户名：");
        JTextField use = new JTextField(20);
        JLabel cipher = new JLabel("密  码：");
        JPasswordField cip = new JPasswordField(20);
        cip.setEchoChar('*');
        JButton show = new JButton("\uD83D\uDC41");
        JLabel name = new JLabel("姓  名：");
        JTextField nam = new JTextField(20);
        JLabel sex = new JLabel("性  别：");
        ButtonGroup group = new ButtonGroup();
        JRadioButton sex1 = new JRadioButton("男");
        JRadioButton sex2 = new JRadioButton("女");
        group.add(sex1);
        group.add(sex2);
        JLabel college = new JLabel("学  院：");
        JTextField col = new JTextField(20);
        JLabel gradeClass = new JLabel("年  级：");
        JTextField gra = new JTextField(20);

        JButton submit = new JButton("提交");
        JButton exit = new JButton("退出");

        JPanel jPanel = new JPanel();
        add(jPanel);

        jPanel.setBounds(0, 0, 700, 500);
        jPanel.setLayout(null);
        jPanel.add(username);
        jPanel.add(use);
        jPanel.add(cipher);
        jPanel.add(cip);
        jPanel.add(show);
        jPanel.add(name);
        jPanel.add(nam);
        jPanel.add(sex);
        jPanel.add(sex1);
        jPanel.add(sex2);
        jPanel.add(college);
        jPanel.add(col);
        jPanel.add(gradeClass);
        jPanel.add(gra);
        jPanel.add(submit);
        jPanel.add(exit);

        username.setBounds(100, 20, 50, 50);
        use.setBounds(150, 20, 250, 50);
        cipher.setBounds(100, 100, 50, 50);
        cip.setBounds(150, 100, 200, 50);
        show.setBounds(350,100,50,50);
        name.setBounds(100, 180, 50, 50);
        nam.setBounds(150, 180, 250, 50);
        sex.setBounds(100, 240, 50, 50);
        sex1.setBounds(170, 240, 100, 50);
        sex2.setBounds(270, 240, 100, 50);
        college.setBounds(100, 300, 50, 50);
        col.setBounds(150, 300, 250, 50);
        gradeClass.setBounds(100, 380, 50, 50);
        gra.setBounds(150, 380, 250, 50);

        submit.setBounds(450, 150, 150, 50);
        exit.setBounds(450, 280, 150, 50);

        submit.addActionListener(e -> {
            if(!(use.getText().isEmpty()||cip.getText().isEmpty())){
                if (new Student().add(new Student(use.getText(), cip.getText(), nam.getText(), sex1.isSelected() ? sex1.getText() : sex2.getText(), col.getText(), gra.getText()))) {
                    showMessageDialog(this, "添加成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    showMessageDialog(this, "用户名重复", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                showMessageDialog(this, "用户名与密码为必填项", "错误", JOptionPane.ERROR_MESSAGE);
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
