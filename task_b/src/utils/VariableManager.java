package utils;

import expression.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class VariableManager {
    Map<String, Boolean> variables = new HashMap<>();

    private void addVariables(Variable e) {
        variables.putIfAbsent(e.getName(), false);
    }

    private void addVariables(Negation n) {
        addVariables(n.getNegated());
    }

    private void addVariables(Implication e) {
        addVariables(e.getLeft());
        addVariables(e.getRight());
    }

    private void addVariables(Disjunction e) {
        addVariables(e.getLeft());
        addVariables(e.getRight());
    }

    private void addVariables(Conjunction e) {
        addVariables(e.getLeft());
        addVariables(e.getRight());
    }

    public void addVariables(Expression e) {
        if (e instanceof Variable) {
            addVariables((Variable) e);
        }
        if (e instanceof Negation) {
            addVariables((Negation) e);
        }
        if (e instanceof Implication) {
            addVariables((Implication) e);
        }
        if (e instanceof Disjunction) {
            addVariables((Disjunction) e);
        }
        if (e instanceof Conjunction) {
            addVariables((Conjunction) e);
        }
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        variables.keySet().stream()
                .sorted()
                .forEachOrdered(
                        v -> res.append(v).append(" ").append(variables.get(v)).append('\t')
                );
        return res.toString();
    }

    private int valuesToNumber() {
        int val = 0;
        int pow = 1;
        for (String v : variables.keySet().stream().sorted().collect(Collectors.toList())) {
            if (variables.get(v)) {
                val += pow;
            }
            pow *= 2;
        }
        return val;
    }

    private void numberToValues(int n) {
        for (String v : variables.keySet().stream().sorted().collect(Collectors.toList())) {
            variables.replace(v, (n % 2) == 1);
            n /= 2;
        }
    }

    public boolean isLastValueSet() {
        for (String v : variables.keySet()) {
            if (!variables.get(v)) {
                return false;
            }
        }
        return true;
    }

    public void changeToNextValueSet() {
        int n = valuesToNumber();
        numberToValues(n + 1);
    }

    public boolean getValue(String name) {
        return variables.get(name);
    }
}
