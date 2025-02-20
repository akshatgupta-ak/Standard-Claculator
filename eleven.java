import java.awt.*;
import java.awt.event.*;
import java.util.Stack;
import java.util.StringTokenizer;
import javax.swing.*;

public class eleven {
    private JTextField textField; // Display screen
    private StringBuilder expression = new StringBuilder(); // Stores full expression

    public eleven() {
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());

        // **TextField for Display**
        textField = new JTextField();
       textField.setFont(new Font("Arial", Font.BOLD, 40)); // Bigger text
textField.setPreferredSize(new Dimension(400, 80)); // Bigger screen
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setEditable(false);
        textField.setBackground(Color.BLACK);
        textField.setForeground(Color.WHITE);
        textField.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        frame.add(textField, BorderLayout.NORTH);

        // **Panel for Buttons**
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 5, 5));

        String[] buttons = {
                "C", "\u232B", "%", "/",
                "7", "8", "9", "*",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "0", ".", "=", ""
        };

        for (String btnText : buttons) {
            JButton button = new JButton(btnText);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setFocusPainted(false);
            button.addActionListener(new ButtonClickListener(btnText));
            panel.add(button);
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // **Button Click Handler**
    private class ButtonClickListener implements ActionListener {
        private final String value;

        public ButtonClickListener(String value) {
            this.value = value;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (value) {
                case "C" -> {  // Clear all input
                    expression.setLength(0);
                    textField.setText("");
                }
                case "\u232B" -> {  // Backspace
                    if (expression.length() > 0) {
                        expression.deleteCharAt(expression.length() - 1);
                    }
                    textField.setText(expression.toString());
                }
                case "=" -> {  // Evaluate Expression
                    try {
                        double result = evaluateExpression(expression.toString());
                        textField.setText((result % 1 == 0) ? String.valueOf((int) result) : String.valueOf(result));
                        expression.setLength(0);
                        expression.append(result);
                    } catch (Exception ex) {
                        textField.setText("Error");
                        expression.setLength(0);
                    }
                }
                default -> {  // Append input to expression
                    expression.append(value);
                    textField.setText(expression.toString());
                }
            }
        }
    }

    // **Evaluate Full Mathematical Expression Using Stacks**
    private double evaluateExpression(String exp) {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();
        StringTokenizer tokenizer = new StringTokenizer(exp, "+-*/%", true);
        
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            if (token.isEmpty()) continue;
            
            if (isNumber(token)) {
                numbers.push(Double.parseDouble(token));
            } else {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(token.charAt(0))) {
                    double num2 = numbers.pop();
                    double num1 = numbers.pop();
                    numbers.push(applyOperator(num1, num2, operators.pop()));
                }
                operators.push(token.charAt(0));
            }
        }

        while (!operators.isEmpty()) {
            double num2 = numbers.pop();
            double num1 = numbers.pop();
            numbers.push(applyOperator(num1, num2, operators.pop()));
        }

        return numbers.pop();
    }

    // **Check if Token is a Number**
    private boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // **Define Operator Precedence**
    private int precedence(char op) {
        return switch (op) {
            case '+', '-' -> 1;
            case '*', '/', '%' -> 2;
            default -> 0;
        };
    }

    // **Perform Calculation Based on Operator**
    private double applyOperator(double num1, double num2, char operator) {
        return switch (operator) {
            case '+' -> num1 + num2;
            case '-' -> num1 - num2;
            case '*' -> num1 * num2;
            case '/' -> (num2 != 0) ? num1 / num2 : Double.NaN; // Prevent division by zero
            case '%' -> num1 % num2;
            default -> 0;
        };
    }

    public static void main(String[] args) {
        new eleven();
    }
}
