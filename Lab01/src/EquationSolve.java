import javax.swing.JOptionPane;

public class EquationSolve {
    public static void main(String[] args) {
        String menu = "---Choose the equations you want to solve---\n1. The first-degree equation\n2.The system of first-degree equations\n3. The second-degree equation\nType your choice: ";

        String menuInput = JOptionPane.showInputDialog(null, menu, "Menu", JOptionPane.QUESTION_MESSAGE);
        if (menuInput == null)
            System.exit(0);

        switch (menuInput) {
            case "1" -> firstDegreeEquation();
            case "2" -> systemOfFirstDegree();
            case "3" -> secondDegreeEquation();
            default -> JOptionPane.showMessageDialog(null, "Error. Please choose again!");
        }
    }

    private static void firstDegreeEquation() {
        double a = Double.parseDouble(JOptionPane.showInputDialog("Type a: "));
        double b = Double.parseDouble(JOptionPane.showInputDialog("Type b:"));

        if (a == 0) {
            String result = (b == 0) ? "Infinitely solutions" : "No solution";
            JOptionPane.showMessageDialog(null, result);
        } else {
            JOptionPane.showMessageDialog(null, "x = " + (-b / a));
        }
    }

    private static void systemOfFirstDegree() {
        double a11 = Double.parseDouble(JOptionPane.showInputDialog("Type a11:"));
        double a12 = Double.parseDouble(JOptionPane.showInputDialog("Type a12:"));
        double b1 = Double.parseDouble(JOptionPane.showInputDialog("Type b1:"));
        double a21 = Double.parseDouble(JOptionPane.showInputDialog("Type a21:"));
        double a22 = Double.parseDouble(JOptionPane.showInputDialog("Type a22:"));
        double b2 = Double.parseDouble(JOptionPane.showInputDialog("Type b2:"));

        double D = a11 * a22 - a21 * a12;
        double D1 = b1 * a22 - b2 * a12;
        double D2 = a11 * b2 - a21 * b1;

        if (D != 0) {
            JOptionPane.showMessageDialog(null, String.format("x1 = %f\nx2 = %f", D1/D, D2/D));
        }
        else {
            String result = (D1 == 0 && D2 == 0) ? "Infinitely solutions" : "No solution";
            JOptionPane.showMessageDialog(null, result);
        }
    }

    private static void secondDegreeEquation() {
        double a = Double.parseDouble(JOptionPane.showInputDialog("Type a:"));
        double b = Double.parseDouble(JOptionPane.showInputDialog("Type b:"));
        double c = Double.parseDouble(JOptionPane.showInputDialog("Type c:"));

        if (a == 0) {
            JOptionPane.showMessageDialog(null, "x = " + (-c/b));
            return;
        }

        double delta = b * b - 4 * a * c;
        if (delta > 0) {
            double x1 = (-b + Math.sqrt(delta)) / (2 * a);
            double x2 = (-b - Math.sqrt(delta)) / (2 * a);

            JOptionPane.showMessageDialog(null, String.format("x1 = %f, x2 = %f", x1, x2));
        }
        else if (delta == 0) {
            JOptionPane.showMessageDialog(null, "x = " + (-b / (2 * a)));
        }
        else {
            JOptionPane.showMessageDialog(null, "No solution");
        }
    }
}

