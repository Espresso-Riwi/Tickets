package view;

import javax.swing.*;

public class ViewMessages {

    public static void showInfoMessage(String message, String title){
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static String showQuestionMessage(String message, String title){
       return JOptionPane.showInputDialog(null, message, title, JOptionPane.QUESTION_MESSAGE);
    }
}
