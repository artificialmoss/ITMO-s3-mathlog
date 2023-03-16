package utils;

import expression.Expression;

public class AlphaManager implements Translator {
    private final String alphaString;
    private final Expression alpha;
    private final String beta; // не бета из контекста, просто шаблон (a -> a)

    public AlphaManager(Expression alpha) {
        this.alpha = alpha;
        alphaString = alpha.toString(3);
        this.beta = "(" + alphaString + "->" + alphaString + ")";
    }

    boolean isAlpha(Expression e) {
        return e.equals(alpha);
    }
/*
    boolean isAlpha(String line) {
        return line.equals(alpha);
    }*/

    public String transformAlpha() {
        return axiom1(alphaString, alphaString) + "\n" +
                axiom1(alphaString, beta) + "\n" +
                axiom2(alphaString, beta, alphaString) + "\n" +
                "(" + alphaString + "->(" + beta + "->" + alphaString + "))->" + beta + "\n" +
                alphaString + "->" + alphaString;
    }
}
