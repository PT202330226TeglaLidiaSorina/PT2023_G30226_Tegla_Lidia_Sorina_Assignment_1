package GUI;

import javax.swing.*;

public class MainClass {
    public static void main(String[] args){
        PolynomialCalculator calculator = new PolynomialCalculator();
        calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculator.setSize(600, 400);
        calculator.setVisible(true);
    }
}
