import javax.swing.JOptionPane;

public class CaculateDialog {
    public static void main(String[] args) {
        String strNum1, strNum2;
        double num1, num2, sum, diff, prod, quot;
        strNum1 = JOptionPane.showInputDialog(null, "Please input the first number: ", "Input the first number", JOptionPane.INFORMATION_MESSAGE);
        num1 = Double.parseDouble(strNum1);
        strNum2 = JOptionPane.showInputDialog(null, "Please input the second number: ", "Input the second number", JOptionPane.INFORMATION_MESSAGE);
        num2 = Double.parseDouble(strNum2);

        sum = num1 + num2;
        diff = num1 - num2;
        prod = num1 * num2;
        quot = num1 / num2;

        String strNoti = "Sum = " + sum + "\n" + "Difference = " + diff + "\n" + "Product = " + prod + "\n" + "Quotient = " + quot;
        JOptionPane.showMessageDialog(null, strNoti, "The result is", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}
