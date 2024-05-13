package Scalculator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Scientificcalculator extends JFrame implements ActionListener {
	    private JTextField displayField;
	    private JButton[] digitButtons;
	    private JButton[] operationButtons;
	    private JButton clearButton;
	    private JButton deleteButton;
	    private JButton sqrtButton;
	    // Other scientific function buttons can be added here

	    private String currentInput = "";
	    private double result = 0;
	    private String currentOperation = "";

	    public Scientificcalculator() {
	        setTitle("Calculator");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLayout(new BorderLayout());

	        // Display area
	        displayField = new JTextField();
	        displayField.setEditable(false);
	        add(displayField, BorderLayout.NORTH);

	        // Button panel
	        JPanel buttonPanel = new JPanel(new GridLayout(5, 4));
	        add(buttonPanel, BorderLayout.CENTER);

	        // Digit buttons
	        digitButtons = new JButton[10];
	        for (int i = 0; i < 10; i++) {
	            digitButtons[i] = new JButton("" + i);
	            digitButtons[i].addActionListener(this);
	            buttonPanel.add(digitButtons[i]);
	        }

	        // Operation buttons
	        operationButtons = new JButton[]{
	                new JButton("+"), new JButton("-"),
	                new JButton("*"), new JButton("/"),
	                new JButton("=")
	        };
	        for (JButton button : operationButtons) {
	            button.addActionListener(this);
	            buttonPanel.add(button);
	        }

	        // Clear and Delete buttons
	        clearButton = new JButton("C");
	        clearButton.addActionListener(this);
	        buttonPanel.add(clearButton);

	        deleteButton = new JButton("DEL");
	        deleteButton.addActionListener(this);
	        buttonPanel.add(deleteButton);

	        // Scientific function buttons
	        sqrtButton = new JButton("sqrt");
	        sqrtButton.addActionListener(this);
	        buttonPanel.add(sqrtButton);

	        // Set up the frame
	        pack();
	        setLocationRelativeTo(null);
	        setVisible(true);
	    }

	    public void actionPerformed(ActionEvent e) {
	        JButton source = (JButton) e.getSource();
	        String buttonText = source.getText();

	        if ("0123456789".contains(buttonText)) {
	            currentInput += buttonText;
	            displayField.setText(currentInput);
	        } else if ("+-*/".contains(buttonText)) {
	            if (!currentInput.isEmpty()) {
	                result = Double.parseDouble(currentInput);
	                currentOperation = buttonText;
	                currentInput = "";
	            }
	        } else if (buttonText.equals("=")) {
	            if (!currentInput.isEmpty()) {
	                double secondOperand = Double.parseDouble(currentInput);
	                switch (currentOperation) {
	                    case "+":
	                        result += secondOperand;
	                        break;
	                    case "-":
	                        result -= secondOperand;
	                        break;
	                    case "*":
	                        result *= secondOperand;
	                        break;
	                    case "/":
	                        result /= secondOperand;
	                        break;
	                }
	                displayField.setText(Double.toString(result));
	                currentInput = "";
	            }
	        } else if (buttonText.equals("C")) {
	            currentInput = "";
	            result = 0;
	            currentOperation = "";
	            displayField.setText("");
	        } else if (buttonText.equals("DEL")) {
	            if (!currentInput.isEmpty()) {
	                currentInput = currentInput.substring(0, currentInput.length() - 1);
	                displayField.setText(currentInput);
	            }
	        } else if (buttonText.equals("sqrt")) {
	            if (!currentInput.isEmpty()) {
	                double operand = Double.parseDouble(currentInput);
	                double sqrtResult = Math.sqrt(operand);
	                displayField.setText(Double.toString(sqrtResult));
	                currentInput = Double.toString(sqrtResult);
	            }
	        }
	    }

	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(Scientificcalculator::new);
	    }
	}


