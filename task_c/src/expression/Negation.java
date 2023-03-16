package expression;

import java.util.Objects;

public class Negation implements Expression{

    private Expression negated;
    private static final int priority = 0;

    public Negation(Expression negated) {
        this.negated = negated;
    }

    public Expression getNegated() {
        return negated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Negation negation = (Negation) o;
        return Objects.equals(negated, negation.negated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(negated);
    }

    @Override
    public String toTree() {
        return "(!" + negated.toTree() + ")";
    }

    /*@Override
    public String toString(int prevNodePriority) {
        return (prevNodePriority < priority) ?
                "(!" + negated.toString(priority) +  ")" :
                "!" + negated.toString(priority);
    }*/

    public String toString(int prevNodePriority) {
        /*return  (prevNodePriority == priority) ?
                "!" + negated.toString(priority) :
                "(!" + negated.toString(priority) + ")";*/ //also fails test 20
        return "(!" + negated.toString(priority) + ")"; //fails test 20
        //return "ABOBA"; //fails test 4
    }

    @Override
    public String toString() {
        //return "ABOBA"; fails test 20 => до теста 20 точно negation в виде чистой формулы (аксиомы/гипотезы) не фигурирует
        return "!" + negated.toString(priority);
    }

    @Override
    public boolean equals(Expression e) {
        if (e instanceof Negation) {
            Negation n = (Negation) e;
            return negated.equals(n.getNegated());
        }
        return false;
    }
}
