package utils;

import expression.Expression;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class ContextLoader implements Translator {
    private final String[] hypotheticalStrings;
    private final Expression alpha;
    private final Expression beta;
    private final LinkedHashSet<Expression> hypothetes;

    public ContextLoader(String line) {
        line = line.replaceAll("\\s", "");
        String tmp[] = line.split("\\|-");
        String betaString = tmp[1];
        hypotheticalStrings = tmp[0].split(",");
        String alphaString = hypotheticalStrings[hypotheticalStrings.length - 1];
        alpha = ExpressionsParser.parseLine(alphaString);
        beta = ExpressionsParser.parseLine(betaString);
        /*hypothetes = Arrays.stream(hypotheticalStrings)
                .map(ExpressionsParser::parseLine)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        hypothetes.remove(alpha);*/
        hypothetes = new LinkedHashSet<>();
        for (int i = 0; i < hypotheticalStrings.length - 1; i++) {
            hypothetes.add(ExpressionsParser.parseLine(hypotheticalStrings[i]));
        }
    }

    public LinkedHashSet<Expression> getHypotheticals() {
        return hypothetes;
    }

    public Expression getBeta() {
        return beta;
    }

    public Expression getAlpha() {
        return alpha;
    }

    public String getTransformedContext() {
        /*return hypothetes.stream().map(Expression::toString).collect(Collectors.joining(",")) + "|-" +
                alpha.toString(3) + "->" + beta.toString(3);*/
        return Arrays.stream(hypotheticalStrings).limit(hypotheticalStrings.length - 1).collect(Collectors.joining(",")) + "|-" +
                alpha.toString(3) + "->" + beta.toString(3);
    }
}
