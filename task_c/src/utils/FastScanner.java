package utils;

import java.util.*;
import java.io.*;

public class FastScanner implements AutoCloseable {
  BufferedReader br;
  StringTokenizer st;

  public FastScanner(File f) {
    try {
      br = new BufferedReader(new FileReader(f));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

    public FastScanner() {
      br = new BufferedReader(new InputStreamReader(System.in));
    }



    public String next() {
    while (st == null || !st.hasMoreTokens()) {
      try {
          String line = br.readLine();
          if (line == null) {
              return null;
          }
          line = line.replaceAll("\\s", "");
        st = new StringTokenizer(line);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return st.nextToken();
  }

  public int nextInt() {
    return Integer.parseInt(next());
  }

  @Override
  public void close() throws IOException {
    br.close();
  }
}
