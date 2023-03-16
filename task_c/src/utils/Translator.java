package utils;

public interface Translator {
    default String axiom1(String a, String b) {
        return a + "->(" + b + "->" + a + ")";
    }

    default String axiom2(String a, String b, String c) {
        return "(" + (a + "->" + b) + ")->((" + (a + "->(" + b + "->" + c) + "))->(" + (a + "->" + c) + "))";
    }
}
