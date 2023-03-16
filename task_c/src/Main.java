import utils.FastScanner;
import utils.ProofManager;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        //FastScanner fastScanner = new FastScanner(new File("input.txt"));
        FastScanner fastScanner = new FastScanner();
        ProofManager proofManager = new ProofManager(fastScanner);
        proofManager.run();
    }
}
