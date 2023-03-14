
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
public class Polynomial {
    private Map<Integer, Integer> terms;

    public Polynomial() {
        terms = new HashMap<Integer, Integer>();
    }

    public Polynomial createPolynomial(String polyString) {
        Pattern pattern=Pattern.compile("([+-]?(?:(?:\\d+x\\^\\d+)|(?:\\d+x)|(?:\\d+)|(?:x)))");
        Matcher matcher=pattern.matcher(polyString);

        Polynomial poly=new Polynomial();
        while(matcher.find()){
            String coeffString = matcher.group(1);
            String expString = matcher.group(1);

            int coefficient=coeffString.isEmpty() ? 1 :
                    coeffString.equals("-") ? -1 : Integer.parseInt(coeffString);
            int exponent=expString == null ? 0 : Integer.parseInt(expString);

            poly.addTerm(new Term(coefficient,exponent));
        }

        return poly;
    }

    public void addTerm(Term term){
        int exponent=term.getExponent();
        int coefficient=term.getCoefficient();
        if(terms.containsKey(exponent)){
            coefficient+=term.getExponent();
        }
        terms.put(exponent,coefficient);
    }

    public Polynomial add(Polynomial polynomial){
        Polynomial result=new Polynomial();

        for(Integer exponent: this.terms.keySet()){
            int coefficient=polynomial.terms.get(exponent);
            result.addTerm(new Term(coefficient,exponent));
        }

        for(Integer exponent: polynomial.terms.keySet()){
            int coefficient=polynomial.terms.get(exponent);
            if(result.terms.containsKey(exponent)) {
                coefficient += result.terms.get(exponent);
            }
            result.addTerm(new Term(coefficient,exponent));
        }
        return result;
    }

    public Polynomial subtract(Polynomial polynomial){
        Polynomial negPoly=new Polynomial();
        for(Integer exponent: polynomial.terms.keySet()){
            int coefficient = polynomial.terms.get(exponent);
            negPoly.addTerm(new Term(-coefficient, exponent));
        }
        return this.add(negPoly);
    }

    public Polynomial multiply(Polynomial polynomial){
        Polynomial result=new Polynomial();

        for(Integer exp1: this.terms.keySet()){
            for(Integer exp2: polynomial.terms.keySet()){
                int c1 = this.terms.get(exp1);
                int c2 = polynomial.terms.get(exp2);
                int c = c1*c2;
                int exp = exp1 + exp2;

                if(result.terms.containsKey(exp)){
                    c+=result.terms.get(exp);
                }
                result.addTerm(new Term(c,exp));

            }
        }
        return result;
    }

    public String toString(){
        StringBuilder sb=new StringBuilder();
        for(Integer exponent: terms.keySet()){
            int coefficient = terms.get(exponent);
            if(coefficient == 0) continue;
            if(coefficient > 0 && sb.length() >0) sb.append("+");
            if(coefficient == -1 && exponent !=0) sb.append("-");
            if(coefficient == 1 && exponent!= -1 || exponent == 0) sb.append(coefficient);
            if(exponent > 0) sb.append("x");
            if(exponent > 1) sb.append("^"+exponent);
        }

        if(sb.length() == 0) sb.append("0");

        return sb.toString();
    }
}
