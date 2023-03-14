import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
public class PolynomialCalculator extends JFrame {
    private JTextField polynomialField1, polynomialField2, resultField;
    private JLabel polynomialLabel1, polynomialLabel2, resultLabel;
    private JButton addButton, subtractButton, multiplyButton, clearButton;
    private Map<String, Polynomial> polynomials;

    public PolynomialCalculator() {
        super("Polynomial Calculator");

        polynomials = new HashMap<String, Polynomial>();

        polynomialField1 = new JTextField(20);
        polynomialField2 = new JTextField(20);

        resultField = new JTextField(20);

        resultField.setEditable(false);

        polynomialLabel1 = new JLabel("Polynomial 1: ");
        polynomialLabel2 = new JLabel("polynomial 2: ");


        resultLabel = new JLabel("Result");
        addButton = new JButton("Add");
        subtractButton = new JButton("Subtract");
        multiplyButton = new JButton("Multiply");

        clearButton = new JButton("Cler");

        JPanel panel = new JPanel(new GridLayout(5, 5));

        panel.add(polynomialLabel1);
        panel.add(polynomialField1);
        panel.add(polynomialLabel2);
        panel.add(polynomialField2);

        panel.add(resultLabel);
        panel.add(resultField);

        panel.add(addButton);
        panel.add(subtractButton);
        panel.add(multiplyButton);
        panel.add(clearButton);

        add(panel);

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String poly1 = polynomialField1.getText();
                String poly2 = polynomialField1.getText();
                if (poly1.isEmpty() || poly2.isEmpty()) {
                    JOptionPane.showMessageDialog(PolynomialCalculator.this, "Please enter valid polynomials.", "error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Polynomial p1 = new Polynomial().createPolynomial(poly1);
                    Polynomial p2 = new Polynomial().createPolynomial(poly2);
                    Polynomial sum = p1.add(p2);
                    String result = sum.toString();
                    resultField.setText(result);
                    polynomials.put("sum", sum);

                }
            }
        });

        subtractButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String poly1 = polynomialField1.getText();
                String poly2 = polynomialField2.getText();
                if (poly1.isEmpty() || poly2.isEmpty()) {
                    JOptionPane.showMessageDialog(PolynomialCalculator.this, "Please enter valid polynomials.", "error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Polynomial p1 = new Polynomial().createPolynomial(poly1);
                    Polynomial p2 = new Polynomial().createPolynomial(poly2);
                    Polynomial difference = p1.subtract(p2);
                    String result = difference.toString();
                    resultField.setText(result);
                    polynomials.put("difference", difference);
                }
            }
        });


        multiplyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String poly1 = polynomialField1.getText();
                String poly2 = polynomialField2.getText();
                if (poly1.isEmpty() || poly2.isEmpty()) {
                    JOptionPane.showMessageDialog(PolynomialCalculator.this, "Please enter valid polynomials.", "error", JOptionPane.ERROR_MESSAGE);
                } else {
                    Polynomial p1 = new Polynomial().createPolynomial(poly1);
                    Polynomial p2 = new Polynomial().createPolynomial(poly2);
                    Polynomial product = p1.multiply(p2);
                    String result = product.toString();
                    resultField.setText(result);
                    polynomials.put("product", product);
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                polynomialField1.setText("");
                polynomialField2.setText("");
                resultField.setText("");
            }
        });
    }
}
