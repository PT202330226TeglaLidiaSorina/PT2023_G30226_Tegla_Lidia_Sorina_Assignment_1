package PolynomialStuff;

public class Term {
    private double coefficient;
    private int exponent;

    public Term(double coefficient,int exponent){
        this.coefficient=coefficient;
        this.exponent=exponent;
    }

    public double getCoefficient(){
        return coefficient;
    }

    public int getExponent(){
        return exponent;
    }

}
