import expression.Expression;
import expression.Variable;
import utils.ExpressionsParser;
import utils.VariableManager;

import java.io.File;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        Expression expression = ExpressionsParser.parse();

        VariableManager variableManager = new VariableManager();
        variableManager.addVariables(expression);

        /*
        System.out.println(variableManager.toString());
        expression.setValue(variableManager);
        System.out.println(expression.getValue());*/

        int countFalse = 0;
        int countTrue = 0;

        while (true) {
            expression.setValue(variableManager);
            if (expression.getValue()) {
                countTrue++;
            } else {
                countFalse++;
            }
            if (!variableManager.isLastValueSet()) {
                variableManager.changeToNextValueSet();
            } else {
                break;
            }
        }

        if (countTrue == 0) {
            System.out.println("Unsatisfiable");
        } else {
            if (countFalse == 0) {
                System.out.println("Valid");
            } else {
                System.out.println("Satisfiable and invalid, " + countTrue + " true and " + countFalse + " false cases");
            }
        }
    }

}
