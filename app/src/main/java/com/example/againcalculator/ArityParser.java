package com.example.againcalculator;

/**
 * Created by просто on 22.11.2016.
 */
import org.javia.arity.Symbols;
import org.javia.arity.SyntaxException;

public class ArityParser implements Parser {
    public String parse(String expression) {
        try {
            Symbols symbols = new Symbols();
            double result = symbols.eval(expression);
            return "" + result;
        } catch (SyntaxException ex) {
            return "ERROR: " + ex.getMessage();
        }
    }

}
