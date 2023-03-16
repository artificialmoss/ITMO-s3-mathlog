package expression;

import java.util.Objects;

public class Variable implements Expression {
    private String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public String toTree() {
        return name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Variable variable = (Variable) o;
        return Objects.equals(name, variable.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString(int prevNodePriority) {
        return (prevNodePriority == 0) ?
                "(" + name + ")" :
                name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Expression e) {
        if (e instanceof Variable) {
            Variable v = (Variable) e;
            return name.equals(v.getName());
        }
        return false;
    }
}
