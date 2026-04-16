package hust.soict.hedspi.lab01;

import javax.swing.JOptionPane;

public class HelloNameDialog {
    public static void main(String args[]){
        String res;
        res = JOptionPane.showInputDialog("Please enter your name: ");
        JOptionPane.showMessageDialog(null, "Hi " + res + "!");
        System.exit(0);
    }
}
