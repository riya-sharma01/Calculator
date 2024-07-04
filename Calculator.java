import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {
    JFrame frame;
    JTextField displayField;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[13];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton;
    JButton factButton, sqrtButton, sqrButton, cubeButton, cubeRootButton;
    JPanel panel;

    // Font myFont = new Font("Arial", Font.BOLD, 30);
    Font myFont = new Font("Arial", Font.PLAIN, 18);

    double operand1 = 0, operand2 = 0, result = 0;
    char operator;

    Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(535, 600);
        frame.setLayout(null);

        displayField = new JTextField();
        displayField.setBounds(50, 40, 420, 80);
        displayField.setFont(myFont);
        displayField.setEditable(false);
        frame.add(displayField);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("Clr");
        factButton = new JButton("!"); // Factorial
        sqrtButton = new JButton("sR"); // Square root
        sqrButton = new JButton("²"); // Square
        cubeButton = new JButton("³"); // Cube
        cubeRootButton = new JButton("cR"); // Cube root

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = factButton;
        functionButtons[9] = sqrtButton;
        functionButtons[10] = sqrButton;
        functionButtons[11] = cubeButton;
        functionButtons[12] = cubeRootButton;

        for (int i = 0; i < functionButtons.length; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }

        for (int i = 0; i < numberButtons.length; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }

        delButton.setBounds(50, 480, 150, 50);
        clrButton.setBounds(205, 480, 150, 50);
        equButton.setBounds(360, 480, 100,50);

        panel = new JPanel();
        panel.setBounds(50, 160, 420, 300);
        panel.setLayout(new GridLayout(6, 4, 10, 10)); // Adjusted layout to fit all buttons

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(factButton);
        panel.add(divButton);
        panel.add(sqrtButton);
        panel.add(sqrButton);
        panel.add(cubeButton);
        panel.add(cubeRootButton);

        frame.add(panel);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(equButton);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
    }

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                displayField.setText(displayField.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == decButton) {
            displayField.setText(displayField.getText().concat("."));
        }
        if (e.getSource() == addButton) {
            operand1 = Double.parseDouble(displayField.getText());
            operator = '+';
            displayField.setText("");
        }
        if (e.getSource() == subButton) {
            operand1 = Double.parseDouble(displayField.getText());
            operator = '-';
            displayField.setText("");
        }
        if (e.getSource() == mulButton) {
            operand1 = Double.parseDouble(displayField.getText());
            operator = '*';
            displayField.setText("");
        }
        if (e.getSource() == divButton) {
            operand1 = Double.parseDouble(displayField.getText());
            operator = '/';
            displayField.setText("");
        }
        if (e.getSource() == equButton) {
            try {
                operand2 = Double.parseDouble(displayField.getText());

                switch (operator) {
                    case '+':
                        result = operand1 + operand2;
                        break;
                    case '-':
                        result = operand1 - operand2;
                        break;
                    case '*':
                        result = operand1 * operand2;
                        break;
                    case '/':
                        if (operand2 == 0) {
                            displayField.setText("Error: Division by zero");
                            return;
                        } else {
                            result = operand1 / operand2;
                        }
                        break;
                }
                displayField.setText(String.valueOf(result));
                operand1 = result;
            } catch (NumberFormatException ex) {
                displayField.setText("Error: Invalid input");
            }
        }
        if (e.getSource() == clrButton) {
            displayField.setText("");
            operand1 = 0;
            operand2 = 0;
            result = 0;
        }
        if (e.getSource() == delButton) {
            String string = displayField.getText();
            displayField.setText("");
            for (int i = 0; i < string.length() - 1; i++) {
                displayField.setText(displayField.getText() + string.charAt(i));
            }
        }
        if (e.getSource() == factButton) {
            try {
                int num = Integer.parseInt(displayField.getText());
                if (num < 0) {
                    displayField.setText("Error: Factorial undefined for negative numbers");
                    return;
                }
                result = factorial(num);
                displayField.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                displayField.setText("Error: Invalid input");
            }
        }
        if (e.getSource() == sqrtButton) {
            try {
                double num = Double.parseDouble(displayField.getText());
                if (num < 0) {
                    displayField.setText("Error: Square root undefined for negative numbers");
                    return;
                }
                result = Math.sqrt(num);
                displayField.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                displayField.setText("Error: Invalid input");
            }
        }
        if (e.getSource() == sqrButton) {
            try {
                double num = Double.parseDouble(displayField.getText());
                result = num * num;
                displayField.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                displayField.setText("Error: Invalid input");
            }
        }
        if (e.getSource() == cubeButton) {
            try {
                double num = Double.parseDouble(displayField.getText());
                result = Math.pow(num, 3);
                displayField.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                displayField.setText("Error: Invalid input");
            }
        }
        if (e.getSource() == cubeRootButton) {
            try {
                double num = Double.parseDouble(displayField.getText());
                result = Math.cbrt(num);
                displayField.setText(String.valueOf(result));
            } catch (NumberFormatException ex) {
                displayField.setText("Error: Invalid input");
            }
        }
    }

    private double factorial(int num) {
        if (num == 0) {
            return 1;
        } else {
            return num * factorial(num - 1);
        }
    }
}
