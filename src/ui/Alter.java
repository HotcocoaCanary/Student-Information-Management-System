package ui;

import member.Student;

import javax.swing.*;

import java.util.Objects;

import static javax.swing.JOptionPane.showMessageDialog;

public class Alter extends JFrame {
    public Alter(Student p) {
        setTitle("更改学生信息");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(600, 150, 500, 700);

        JLabel name = new JLabel("姓   名：");
        JTextField nam = new JTextField(p.getName(), 20);
        JLabel sex = new JLabel("性   别：");
        ButtonGroup group = new ButtonGroup();
        JRadioButton sex1 = new JRadioButton("男");
        JRadioButton sex2 = new JRadioButton("女");
        if(Objects.equals(p.getSex(), "男")){
            sex1 = new JRadioButton("男",true);
        }else{
            sex2 = new JRadioButton("女",true);
        }
        group.add(sex1);
        group.add(sex2);
        JLabel college = new JLabel("学   院：");
        JTextField col = new JTextField(p.getCollege(), 20);
        JLabel gradeClass = new JLabel("年   级：");
        JTextField gra = new JTextField(p.getGradeClass(), 20);

        JButton submit = new JButton("提交");
        JButton exit = new JButton("退出");

        JPanel jPanel = new JPanel();
        add(jPanel);

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
        jPanel.add(submit);
        jPanel.add(exit);

        name.setBounds(100, 80, 50, 50);
        nam.setBounds(150, 80, 250, 50);
        sex.setBounds(100, 140, 50, 50);
        sex1.setBounds(170, 140, 100, 50);
        sex2.setBounds(270, 140, 100, 50);
        college.setBounds(100, 200, 50, 50);
        col.setBounds(150, 200, 250, 50);
        gradeClass.setBounds(100, 280, 50, 50);
        gra.setBounds(150, 280, 250, 50);

        submit.setBounds(100, 360, 100, 50);
        exit.setBounds(300, 360, 100, 50);

        String s=sex1.isSelected() ? sex1.getText() : sex2.getText();
        submit.addActionListener(e -> {
            new Student().alter(new Student(p.getUsername(), p.getPassword(), nam.getText(), s, col.getText(), gra.getText()));
            showMessageDialog(this, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });
        exit.addActionListener(e -> dispose());
    }
}
