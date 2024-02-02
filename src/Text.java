import ui.LogIn;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;

import java.awt.*;
import java.util.Enumeration;

import static javax.swing.JOptionPane.showMessageDialog;

public class Text {
    public static void main(String[] args){
        try {
            FontUIResource fontRes = new FontUIResource(new Font("alias", Font.PLAIN, 16));
            for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();) {
                Object key = keys.nextElement();
                Object value = UIManager.get(key);
                if (value instanceof FontUIResource) {
                    UIManager.put(key, fontRes);
                }
            }
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            new LogIn();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            showMessageDialog(new JOptionPane(), "UI样式描绘异常", "警告", JOptionPane.WARNING_MESSAGE);
        }
    }
}
