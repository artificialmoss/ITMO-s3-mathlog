package utils;

import expression.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

public class AxiomManager implements Translator {
    private final HashSet<Expression> hypotheses;
    private final Expression alpha;
    private final String alphaString;

    public AxiomManager(HashSet<Expression> h, Expression alpha) {
        this.hypotheses = h;
        this.alpha = alpha;
        alphaString = alpha.toString(3);
    }

    public String transformHypothesis(Expression e) {
        return axiom1(e.toString(3), alphaString) + "\n"
                + e.toString() + "\n" +
                alphaString + "->" + e.toString(3);
    }

    public String transformAxiom(Expression e) {
        return e.toString() + "\n"
                + axiom1(e.toString(3), alphaString) + "\n" +
                alphaString + "->" + e.toString(3);
    }
/*
    public String transformAxiom(String axiom) {
        return axiom + "\n" + axiom1(axiom, alphaString) + "\n" + alphaString + "->" + axiom;
    }*/

    public boolean isHypothesis(Expression e) {
        return hypotheses.contains(e);
    }

    public boolean isAxiom(Expression e) {
        if (!(e instanceof Implication)) {
            return false;
        }
        Implication f = (Implication) e;
        return isAxiom1(f) || isAxiom2(f) || isAxiom3(f) || isAxiom4(f) || isAxiom5(f) ||
                isAxiom6(f) || isAxiom7(f) || isAxiom8(f) || isAxiom9(f) || isAxiom10(f);
    }
/*
    public boolean isAxiom(String line) {
        if (hypotheses.contains(line)) return true;
        Expression e = ExpressionsParser.parseLine(line);
        if (!(e instanceof Implication)) {
            return false;
        }
        Implication f = (Implication) e;
        return isAxiom1(f) || isAxiom2(f) || isAxiom3(f) || isAxiom4(f) || isAxiom5(f) ||
                isAxiom6(f) || isAxiom7(f) || isAxiom8(f) || isAxiom9(f) || isAxiom10(f);
    }*/

    private boolean isAxiom1(Implication e) { //кажется тут не правильные приоритеты
        if (e.getRight() instanceof Implication) {
            Implication rightImpl = (Implication) e.getRight();
            return  e.getLeft().equals(rightImpl.getRight());
        }
        return false;
    }

    private boolean isAxiom2(Implication e) {
        if (e.getRight() instanceof Implication) {
            Implication tmpImpl = (Implication) e.getRight();

            if (e.getLeft() instanceof Implication &&
                    tmpImpl.getLeft() instanceof Implication &&
                    tmpImpl.getRight() instanceof Implication) {

                Implication leftImpl = (Implication) e.getLeft();
                Implication middleImpl = (Implication) tmpImpl.getLeft();
                Implication rightImpl = (Implication) tmpImpl.getRight();

                if (leftImpl.getLeft().equals(rightImpl.getLeft()) &&
                        leftImpl.getLeft().equals(middleImpl.getLeft())) {
                    Expression beta = leftImpl.getRight();
                    Expression gamma = rightImpl.getRight();

                    if (middleImpl.getRight() instanceof Implication) {
                        Implication lstImpl = (Implication) middleImpl.getRight();
                        return lstImpl.getLeft().equals(beta) && lstImpl.getRight().equals(gamma);
                    }
                }
            }
        }
        /*
        if (e.getLeft() instanceof Implication) {
            Implication tempImpl = (Implication) e.getLeft();
            System.out.println(1);

            if (tempImpl.getLeft() instanceof Implication &&
            tempImpl.getRight() instanceof Implication &&
                    e.getRight() instanceof Implication) {
                System.out.println(2);
                Implication leftImpl = (Implication) tempImpl.getLeft();
                Implication middleImpl = (Implication) tempImpl.getRight();
                Implication rightImpl = (Implication) tempImpl.getRight();

                if (leftImpl.getLeft().equals(rightImpl.getLeft()) &&
                leftImpl.getLeft().equals(middleImpl.getLeft())) {
                    System.out.println(3);
                    Expression beta = leftImpl.getRight();
                    Expression gamma = rightImpl.getRight();

                    if (middleImpl.getRight() instanceof Implication) {
                        System.out.println(4);
                        Implication lstImpl = (Implication) middleImpl.getRight();
                        return lstImpl.getLeft().equals(beta) && lstImpl.getRight().equals(gamma);
                    }
                }
            }
        }*/

        return false;
    }

    private boolean isAxiom3(Implication e) {
        //System.out.println(e.toString());
        //System.out.println(e.toTree());
        if (e.getRight() instanceof Implication) {
            Implication impl = (Implication) e.getRight();
            if (impl.getRight() instanceof Conjunction) {
                Conjunction conj = (Conjunction) impl.getRight();
                return conj.getRight().equals(impl.getLeft()) && e.getLeft().equals(conj.getLeft());
            }
        }
        /*
        if (e.getLeft() instanceof Implication && e.getRight() instanceof Conjunction) {
            Implication leftImpl = (Implication) e.getLeft();
            Conjunction rightConj = (Conjunction) e.getRight();
            System.out.println(1);

            return leftImpl.getLeft().equals(rightConj.getLeft()) &&
                    leftImpl.getRight().equals(rightConj.getRight());
        }*/
        return false;
    }

    private boolean isAxiom4(Implication e) {
        if (e.getLeft() instanceof Conjunction) {
            Conjunction leftConj = (Conjunction) e.getLeft();
            return (leftConj.getLeft().equals(e.getRight()));
        }
        return false;
    }

    private boolean isAxiom5(Implication e) {
        if (e.getLeft() instanceof Conjunction) {
            Conjunction leftConj = (Conjunction) e.getLeft();
            return (leftConj.getRight().equals(e.getRight()));
        }
        return false;
    }

    private boolean isAxiom6(Implication e) {
        if (e.getRight() instanceof Disjunction) {
            Disjunction rightDisj = (Disjunction) e.getRight();
            return e.getLeft().equals(rightDisj.getLeft());
        }
        return false;
    }

    private boolean isAxiom7(Implication e) {
        if (e.getRight() instanceof Disjunction) {
            Disjunction rightDisj = (Disjunction) e.getRight();
            return e.getLeft().equals(rightDisj.getRight());
        }
        return false;
    }

    private boolean isAxiom8(Implication e) {
        if (e.getRight() instanceof Implication) {
            Implication tmpImpl = (Implication) e.getRight();
            if (tmpImpl.getLeft() instanceof Implication &&
                    tmpImpl.getRight() instanceof Implication &&
            e.getLeft() instanceof Implication) {

                Implication leftImpl = (Implication) e.getLeft();
                Implication middleImpl = (Implication) tmpImpl.getLeft();
                Implication rightImpl = (Implication) tmpImpl.getRight();


                if (leftImpl.getRight().equals(middleImpl.getRight()) &&
                leftImpl.getRight().equals(rightImpl.getRight())) {
                    if (rightImpl.getLeft() instanceof Disjunction) {
                        Disjunction d = (Disjunction) rightImpl.getLeft();
                        return leftImpl.getLeft().equals(d.getLeft()) &&
                                middleImpl.getLeft().equals(d.getRight());
                    }
                }
            }
        }
        return false;
    }

    private boolean isAxiom9(Implication e) {
        if (e.getRight() instanceof Implication) {
            Implication tmpImpl = (Implication) e.getRight();

            if (tmpImpl.getLeft() instanceof Implication &&
                    e.getLeft() instanceof Implication ) {

                Implication leftImpl = (Implication) e.getLeft();
                Implication middleImpl = (Implication) tmpImpl.getLeft();

                if (leftImpl.getLeft().equals(middleImpl.getLeft())) {
                    if (tmpImpl.getRight() instanceof Negation &&
                            middleImpl.getRight() instanceof Negation) {
                        Negation rightNeg = (Negation) tmpImpl.getRight();
                        Negation middleNeg = (Negation) middleImpl.getRight();

                        return (leftImpl.getRight().equals(middleNeg.getNegated()) &&
                                leftImpl.getLeft().equals(rightNeg.getNegated()));
                    }
                }
            }
        }

        return false;
    }

    private boolean isAxiom10(Implication e) {
        if (e.getLeft() instanceof Negation) {
            Negation n1 = (Negation) e.getLeft();
            if (n1.getNegated() instanceof Negation) {
                Negation n2 = (Negation) n1.getNegated();
                return (n2.getNegated().equals(e.getRight()));
            }
        }
        return false;
    }

}
