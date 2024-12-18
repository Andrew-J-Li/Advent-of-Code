import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Day11 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Day11.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("Day11.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Deque<Long> q = new LinkedList<>();
        while (st.hasMoreTokens()) {
            q.add(Long.parseLong(st.nextToken()));
        }

        for (int i = 0; i < 25; i++) {
            int stat = q.size();
            for (int j = 0; j < stat; j++) {
                long curr = q.removeFirst();
                if (curr == 0) q.addLast(Long.valueOf(1));
                else {
                    long test = curr;
                    int dig = 0;
                    while (test != 0) {
                        test /= 10;
                        dig++;
                    }
                    if (dig % 2 == 0) {
                        q.addLast(Long.parseLong(Long.toString(curr).substring(0, dig / 2)));
                        q.addLast(Long.parseLong(Long.toString(curr).substring(dig / 2)));
                    } else {
                        q.addLast(curr * Long.valueOf(2024));
                    }
                }
            }
        }

        pw.println(q.size());
        br.close();
        pw.close();
    }
}