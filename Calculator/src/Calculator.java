import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;  // Added this line
    
    private JTextField display;
    private JButton[] numberButtons;
    private JButton[] operationButtons;
    private JButton equalsButton, clearButton;
    private JPanel panel;

    private double num1 = 0, num2 = 0, result = 0;
    private char operator;

    public Calculator() {
        setTitle("Simple Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
        }

        operationButtons = new JButton[4];
        operationButtons[0] = new JButton("+");
        operationButtons[1] = new JButton("-");
        operationButtons[2] = new JButton("*");
        operationButtons[3] = new JButton("/");

        for (JButton button : operationButtons) {
            button.addActionListener(this);
        }

        equalsButton = new JButton("=");
        equalsButton.addActionListener(this);

        clearButton = new JButton("C");
        clearButton.addActionListener(this);

        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(operationButtons[0]);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(operationButtons[1]);
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(operationButtons[2]);
        panel.add(numberButtons[0]);
        panel.add(clearButton);
        panel.add(equalsButton);
        panel.add(operationButtons[3]);

        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                display.setText(display.getText() + i);
                return;
            }
        }

        if (e.getSource() == clearButton) {
            display.setText("");
            num1 = num2 = result = 0;
            operator = '\0';
        } else if (e.getSource() == equalsButton) {
            num2 = Double.parseDouble(display.getText());
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }
            display.setText(String.valueOf(result));
            num1 = result;
        } else {
            for (JButton opButton : operationButtons) {
                if (e.getSource() == opButton) {
                    num1 = Double.parseDouble(display.getText());
                    operator = opButton.getText().charAt(0);
                    display.setText("");
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calc = new Calculator();
            calc.setVisible(true);
        });
    }
}