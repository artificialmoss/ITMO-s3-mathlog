package expression;

import utils.VariableManager;

public interface Expression {
    String toTree();

    boolean getValue();

    void setValue(VariableManager vm);
}
