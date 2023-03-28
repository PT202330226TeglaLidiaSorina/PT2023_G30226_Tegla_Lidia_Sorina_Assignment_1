package GUI;
import PolynomialStuff.Polynomial;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PolynomialCalculator extends JFrame {
    private final JTextField polynomialField1;
    private final JTextField polynomialField2;
    private final JTextField resultField;

    private final Polynomial p1;
    private final Polynomial p2;
    private JLabel polynomialLabel1, polynomialLabel2, resultLabel;
    private JButton addButton, subtractButton, multiplyButton, clearButton, derivationButton, integrationButton, divisionButton;

    public Polynomial[] getPolynomialsFromFields() {
        String poly1 = polynomialField1.getText();
        String poly2 = polynomialField2.getText();
        if (isValidInput(poly1, poly2)) {
            Polynomial[] polynomials = new Polynomial[2];
            polynomials[0] = p1.createPolynomial(poly1);
            polynomials[1] = p2.createPolynomial(poly2);
            return polynomials;
        }
        return null;
    }

    public boolean isValidInput(String poly1, String poly2) {
        if (poly1.isEmpty() || poly2.isEmpty()) {
            JOptionPane.showMessageDialog(PolynomialCalculator.this, "Please enter valid polynomials on both fields.", "error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    public PolynomialCalculator() {
        super("Polynomial Calculator");

        //  polynomials = new HashMap<String, Polynomial>();

        polynomialField1 = new JTextField(20);
        polynomialField2 = new JTextField(20);

        resultField = new JTextField(20);

        resultField.setEditable(false);

        polynomialLabel1 = new JLabel("Polynomial 1: ");
        polynomialLabel2 = new JLabel("Polynomial 2: ");


        resultLabel = new JLabel("Result:");
        addButton = new JButton("Add");
        subtractButton = new JButton("Subtract");
        multiplyButton = new JButton("Multiply");
        derivationButton=new JButton("Derivative");
        integrationButton=new JButton("Integrate");
        divisionButton=new JButton("Divide");
        clearButton = new JButton("Clear");

        JPanel panel = new JPanel(new GridLayout(8, 8));

        panel.add(polynomialLabel1);
        panel.add(polynomialField1);
        panel.add(polynomialLabel2);
        panel.add(polynomialField2);

        panel.add(resultLabel);
        panel.add(resultField);

        panel.add(addButton);
        panel.add(subtractButton);
        panel.add(multiplyButton);
        panel.add(derivationButton);
        panel.add(integrationButton);
        panel.add(divisionButton);
        panel.add(clearButton);

        add(panel);

        p1 = new Polynomial();
        p2 = new Polynomial();

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Polynomial[] polynomials = getPolynomialsFromFields();
                if (polynomials != null) {
                    Polynomial sum = polynomials[0].add(polynomials[1]);
                    String result = sum.toString();
                    resultField.setText(result);
                }
            }
        });

        subtractButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Polynomial[] polynomials = getPolynomialsFromFields();
                if (polynomials != null) {
                    Polynomial difference = polynomials[0].subtract(polynomials[1]);
                    String result = difference.toString();
                    resultField.setText(result);
                }
            }
        });

        multiplyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Polynomial[] polynomials = getPolynomialsFromFields();
                if (polynomials != null) {
                    Polynomial product = polynomials[0].multiply(polynomials[1]);
                    String result = product.toString();
                    resultField.setText(result);
                }
            }
        });

        derivationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String poly1 = polynomialField1.getText();
                String poly2 = polynomialField2.getText();
                String result="";
                if((!poly1.isEmpty() && !poly2.isEmpty()))
                {
                    JOptionPane.showMessageDialog(PolynomialCalculator.this, "Please enter one single polynomial", "error", JOptionPane.ERROR_MESSAGE);
                }
                if (!poly1.isEmpty() && poly2.isEmpty()){
                    Polynomial polynomial= p1.createPolynomial(poly1);
                    Polynomial derivation=polynomial.derivation();
                    result=derivation.toString();
                } else if (!poly2.isEmpty() && poly1.isEmpty() ) {
                    Polynomial polynomial= p2.createPolynomial(poly2);
                    Polynomial derivation=polynomial.derivation();
                    result=derivation.toString();
                    resultField.setText(result);
                }
                resultField.setText(result);
            }
        });

      /*  divisionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Polynomial[] polynomials=getPolynomialsFromFields();
                if(polynomials != null) {
                   String q= quotient.toString();
                   String r=remainder.toString();
                   String rez="Quotient: "+q+" Remainder: "+r;
                   resultField.setText(rez);
                }
            }
        });
*/
        integrationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String poly1 = polynomialField1.getText();
                String poly2 = polynomialField2.getText();
                String result="";
                if((!poly1.isEmpty() && !poly2.isEmpty()))
                {
                    JOptionPane.showMessageDialog(PolynomialCalculator.this, "Please enter one single polynomial", "error", JOptionPane.ERROR_MESSAGE);
                }
                if (!poly1.isEmpty() && poly2.isEmpty()){
                    Polynomial polynomial= p1.createPolynomial(poly1);
                    Polynomial integration=polynomial.integration();
                    result=integration.toString();
                    result+=" + C";
                } else if (!poly2.isEmpty() && poly1.isEmpty() ) {
                    Polynomial polynomial= p2.createPolynomial(poly2);
                    Polynomial integration=polynomial.integration();
                    result=integration.toString();
                    result+=" + C";
                }
                resultField.setText(result);
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



