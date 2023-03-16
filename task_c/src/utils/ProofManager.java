package utils;

import expression.Expression;
import expression.Implication;

import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class ProofManager implements Runnable {
    private final LinkedHashSet<Expression> statements = new LinkedHashSet<>();
    private ContextLoader contextLoader;
    private Expression alpha;
    private AlphaManager alphaManager;
    private AxiomManager axiomManager;
    private MPManager mpManager;
    private final FastScanner scanner;
    //private PrintWriter writer;


    public ProofManager(FastScanner fs) {
        this.scanner = fs;
        //this.writer = writer;
        String contextLine = fs.next();
        init(contextLine);
    }

    public void init(String line) {
        contextLoader = new ContextLoader(line);
        alpha = contextLoader.getAlpha();
        alphaManager = new AlphaManager(alpha);
        axiomManager = new AxiomManager(contextLoader.getHypotheticals(), alpha);
        mpManager = new MPManager(alpha);
    }

    @Override
    public void run() {
        System.out.println(contextLoader.getTransformedContext());
        String line = scanner.next();
        while (line != null) {
            String result = handleLine(line);
            System.out.println(result);
            //writer.println(result);
            line = scanner.next();
        }
    }

    private String handleLine(String line) {
        line = line.trim().replaceAll("\\s", "");
        //System.out.println(line);
        Expression e = ExpressionsParser.parseLine(line);
        if (axiomManager.isHypothesis(e)) {
            //System.out.println("\n ---- " + line + " is hypothesis");
            statements.add(e);
            return axiomManager.transformHypothesis(e);
        }
        if (axiomManager.isAxiom(e)) {
            //System.out.println("\n ---- " + line + " is axiom");
            statements.add(e);
            return axiomManager.transformAxiom(e);
        }
        if (alphaManager.isAlpha(e)) {
            //System.out.println("\n ---- " + line + " is alpha");
            statements.add(e);
            return alphaManager.transformAlpha();
        }
        //System.out.println("\n ---- " + line + " is mp");
        Implication mp = mpManager.findMP(statements, e);
        statements.add(e);
        //System.out.println(mp.toString());
        return mpManager.transformMP(mp);
    }
}
