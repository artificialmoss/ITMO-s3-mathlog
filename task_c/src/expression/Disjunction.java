package expression;

import java.util.Objects;

public class Disjunction implements Expression {

    private Expression left; //Disjunction
    private Expression right; //Conjunction
    private static final int priority = 2;

    public Disjunction(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disjunction that = (Disjunction) o;
        return Objects.equals(left, that.left) &&
                Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
/*
    @Override
    public String toStringWithoutParentheses() {
        return left.toString() + "|" + right.toString();
    }
*/
    /*@Override
    public String toString(int prevNodePriority) {
        return (prevNodePriority < priority) ?
                "(" + left.toString(priority) + "|" + right.toString(priority) + ")" :
                left.toString(priority) + "|" + right.toString(priority);
    }*/

    public String toString(int prevNodePriority) {
        return "(" + left.toString(priority) + "|" + right.toString(priority) + ")";
    }

    @Override
    public String toString() {
        return left.toString(priority) + "|" + right.toString(priority);
    }

    @Override
    public String toTree() {
        return "(|," + left.toTree() + "," + right.toTree() + ")";
    }

    @Override
    public boolean equals(Expression e) {
        if (e instanceof Disjunction) {
            Disjunction d = (Disjunction) e;
            return this.left.equals(d.getLeft()) && this.right.equals(d.getRight());
        }
        return false;
    }
}
