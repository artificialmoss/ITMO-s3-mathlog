package expression;

import utils.VariableManager;

import java.util.Objects;

public class Variable implements Expression {
    private String name;
    private boolean value;

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
    public String toString() {
        return name;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public boolean getValue() {
        return value;
    }

    @Override
    public void setValue(VariableManager vm) {
        value = vm.getValue(name);
    }
}
