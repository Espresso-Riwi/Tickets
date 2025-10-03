package view;

import javax.swing.*;

public class ViewMessages {

    public static void showInfoMessage(String message, String title){
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
