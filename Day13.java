import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Day13 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Day13.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("Day13.out")));
        long rslt = 0;
        while (br.ready()) {
            String a = br.readLine();
            String b = br.readLine();
            String x = br.readLine();
            br.readLine();
            String[] A = a.split("[:,+=\\s]+");
            String[] B = b.split("[:,+=\\s]+");
            String[] X = x.split("[:,+=\\s]+");
            int ax = Integer.parseInt(A[3]);
            int ay = Integer.parseInt(A[5]);
            int bx = Integer.parseInt(B[3]);
            int by = Integer.parseInt(B[5]);
            int xtarget = Integer.parseInt(X[2]);
            int ytarget = Integer.parseInt(X[4]);

            int bmax = Math.min(Math.min(xtarget / bx, ytarget / by), 100);
            long min = Integer.MAX_VALUE;
            for (int i = bmax; i >= 0; i--) {
                if ((xtarget - i * bx) % ax == 0 && (ytarget - i * by) % ay == 0 && (xtarget - i * bx) / ax == (ytarget - i * by) / ay) {
                    min = Math.min(min, i + 3 * (xtarget - i * bx) / ax);
                }
            }
            rslt += (min == Integer.MAX_VALUE) ? 0 : min;
        }

        pw.println(rslt);
        br.close();
        pw.close();
    }
}