package PolynomialStuff;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Map;
public class Polynomial {
    private Map<Integer, Term> terms;

    public Polynomial() {

        terms = new HashMap<Integer, Term>();
    }

    public Polynomial(Map<Integer, Term> polynomial) {
        terms = polynomial;
    }

    public Map<Integer, Term> getTerms() {
        return terms;
    }

    public int degree() {
        if (terms.isEmpty()) {
            return 0;
        }
        int maxDegree = 0;
        for (Integer degree : terms.keySet()) {
            if (degree > maxDegree) {
                maxDegree = degree;
            }
        }
        return maxDegree;
    }

    public double leadingCoefficient() {
        double degree = degree();
        if (terms.containsKey(degree)) {
            return terms.get(degree).getCoefficient();
        } else {
            return 0;
        }
    }

    public static Polynomial createPolynomial(String polyString) {
        Pattern pattern = Pattern.compile("([+-]?\\d+)?\\s*x(\\s*[+-]?\\s*\\d+)?\\s*(\\^\\s*(\\d+))?");
        Matcher matcher = pattern.matcher(polyString);

        Polynomial poly = new Polynomial();
        while (matcher.find()) {
            String coefficientStr = matcher.group(1);
            if (coefficientStr == null || coefficientStr.equals("+") || coefficientStr.equals("")) {
                coefficientStr = "1";
            } else if (coefficientStr.equals("-")) {
                coefficientStr = "-1";
            }

            int power = matcher.group(4) != null ? Integer.parseInt(matcher.group(4)) : 1;
            double coefficient = Double.parseDouble(coefficientStr);

            if (matcher.group(2) != null) { // daca exista termenul constant
                double constant = Double.parseDouble(matcher.group(2).replaceAll("\\s", "")); // eliminam orice spatiu din termenul constant
                poly.addTerm(new Term(constant, 0)); // adaugam termenul constant
            }

            poly.addTerm(new Term(coefficient, power));
        }
        return poly;
    }
    public void addTerm(Term term) {
        int exponent = term.getExponent();
        Term existingTerm = terms.get(exponent);
        if (existingTerm != null) {
            double coefficient = existingTerm.getCoefficient();
            coefficient += term.getCoefficient();
            terms.put( exponent, new Term(coefficient, exponent));
        } else {
            terms.put(exponent, term);
        }
    }

    public Polynomial add(Polynomial polynomial) {
        Polynomial result = new Polynomial();

        for (Integer exponent : this.terms.keySet()) {
            Term thisTerm = this.terms.get(exponent);
            Term otherTerm = polynomial.terms.get(exponent);
            if (otherTerm != null) {
                double coefficient = thisTerm.getCoefficient() + otherTerm.getCoefficient();
                result.addTerm(new Term(coefficient, exponent));
            } else {
                result.addTerm(thisTerm);
            }
        }

        for (Integer exponent : polynomial.terms.keySet()) {
            Term otherTerm = polynomial.terms.get(exponent);
            if (!this.terms.containsKey(exponent)) {
                result.addTerm(otherTerm);
            }
        }

        return result;
    }

    public Polynomial subtract(Polynomial polynomial) {
        Polynomial negPoly = new Polynomial();
        for (Integer exponent : polynomial.terms.keySet()) {
            double coefficient = polynomial.terms.get(exponent).getCoefficient();
            negPoly.addTerm(new Term(-coefficient, exponent));
        }
        return add(negPoly);
    }

    public Polynomial multiply(Polynomial polynomial) {
        Map<Integer, Double> result = new HashMap<>();

        for (Integer exp1 : this.terms.keySet()) {
            Term term1 = this.terms.get(exp1);
            for (Integer exp2 : polynomial.terms.keySet()) {
                Term term2 = polynomial.terms.get(exp2);
                double c = term1.getCoefficient() * term2.getCoefficient();
                int exp = exp1 + exp2;
                if (result.containsKey(exp)) {
                    c += result.get(exp);
                }
                result.put( exp, c);
            }
        }

        Polynomial polynomialResult = new Polynomial();

        for (Map.Entry<Integer, Double> entry : result.entrySet()) {
            polynomialResult.addTerm(new Term(entry.getValue(), entry.getKey()));
        }

        return polynomialResult;
    }

    public Polynomial derivation(){
        Map<Integer, Double> result = new HashMap<>();
        for(Integer exp:this.terms.keySet()){
            double c;
            int exponent = 0;
            if(exp==0) c=0;
            else {
                c = (this.terms.get(exp).getCoefficient()) * this.terms.get(exp).getExponent();
                exponent=this.terms.get(exp).getExponent()-1;
            }
            if (result.containsKey(exp)) {
                c += result.get(exp);
            }
            result.put(exponent, c);
        }

        Polynomial polynomialResult = new Polynomial();

        for (Map.Entry<Integer, Double> entry : result.entrySet()) {
            polynomialResult.addTerm(new Term(entry.getValue(), entry.getKey()));
        }

        return polynomialResult;
    }

    public Polynomial integration() {
        Map<Integer, Double> result = new HashMap<>();
        for (Integer exp : this.terms.keySet()) {
            double c = this.terms.get(exp).getCoefficient();
            int exponent = exp + 1;
            c /= exponent;

            if (result.containsKey(exponent)) {
                c += result.get(exponent);
            }
            result.put(exponent, c);
        }

        if (!result.containsKey(0)) {
            result.put(0, 0.0);
        }

        Polynomial polynomialResult = new Polynomial();

        for (Map.Entry<Integer, Double> entry : result.entrySet()) {
            polynomialResult.addTerm(new Term(entry.getValue(), entry.getKey()));
        }

        return polynomialResult;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        boolean isFirstTerm = true;
        for (Integer exponent : terms.keySet()) {
            double coefficient = terms.get(exponent).getCoefficient();
            if (coefficient != 0) {
                if (!isFirstTerm && coefficient > 0) {
                    builder.append("+");
                }
                if (coefficient < 0) {
                    builder.append("-");
                    coefficient = -coefficient;
                }
                if (exponent == 0) {
                   if(coefficient==(int)coefficient) builder.append((int)coefficient);
                   else{
                       String coef=String.format("%.1f",coefficient);
                       builder.append(coef);
                   }
                } else {
                    if (coefficient != 1 || exponent == 1) {
                       if(coefficient==(int)coefficient) builder.append((int)coefficient);
                       else{
                           String coef=String.format("%.1f",coefficient);
                           builder.append(coef);
                       }
                    }
                    if (exponent > 0) {
                        builder.append("x");
                        if (exponent > 1) {
                            builder.append("^").append(exponent);
                        }
                    }
                }
                isFirstTerm = false;
            }
        }
        if (isFirstTerm) {
            builder.append("0");
        }
        return builder.toString();
    }

}