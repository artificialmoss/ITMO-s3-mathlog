package utils;

import expression.Expression;
import expression.Implication;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class MPManager implements Translator {
    private final Expression alpha;
    private final String alphaString;

    public MPManager(Expression alpha) {
        this.alpha = alpha;
        this.alphaString = alpha.toString(3);
    }

    public Implication findMP(LinkedHashSet<Expression> statements, Expression result) {
        for (Expression statement:
             statements) {
            if (statement instanceof Implication) {
                Implication impl = (Implication) statement;
                if (impl.getRight().equals(result) && statements.contains(impl.getLeft())) {
                    return impl;
                }
            }
        }
        return null;
    }

    public String transformMP(Implication impl) {
        String left = impl.getLeft().toString(3);
        String right = impl.getRight().toString(3);
        return axiom2(alphaString, left, right) + "\n" +
                "(" + alphaString + "->(" + left + "->" + right + "))->(" + alphaString + "->" + right + ")\n" +
                alphaString + "->" + right;
    }
}
