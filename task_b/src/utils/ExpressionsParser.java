package utils;

import expression.Expression;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import parser.ExpressionLexer;
import parser.ExpressionParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExpressionsParser {

    public static Expression parse(String inPath) throws IOException {
        return readFile(inPath);
    }

    private static Expression readFile(String path) throws IOException {
        try (FastScanner in = new FastScanner(new File(path))) {
            String statement = in.next();
            ANTLRInputStream is = new ANTLRInputStream(statement);
            ExpressionLexer lexer = new ExpressionLexer(is);
            TokenStream ts = new CommonTokenStream(lexer);
            ExpressionParser parser = new ExpressionParser(ts);
            return parser.expression().expr;
        }
    }

    public static Expression parse() throws IOException {
        return readFile();
    }

    private static Expression readFile() throws IOException {
        try (FastScanner in = new FastScanner()) {
            String statement = in.next();
            ANTLRInputStream is = new ANTLRInputStream(statement);
            ExpressionLexer lexer = new ExpressionLexer(is);
            TokenStream ts = new CommonTokenStream(lexer);
            ExpressionParser parser = new ExpressionParser(ts);
            return parser.expression().expr;
        }
    }
}
