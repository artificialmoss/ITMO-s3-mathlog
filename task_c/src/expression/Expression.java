package expression;

public interface Expression {
    String toTree();

    String toString();

    String toString(int prevNodePriority);

    boolean equals(Expression e);


}
