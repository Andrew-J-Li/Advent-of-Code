import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Day9 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Day9.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("Day9.out")));
        String s = br.readLine();
        ArrayList<String> n = new ArrayList<>();

        for (int i = 0; i < s.length(); i += 2) {
            int c = s.charAt(i) - '0';
            int sp = (i + 1 < s.length()) ? s.charAt(i + 1) - '0': 0;
            for (int j = 0; j < c; j++) n.add(i / 2 + "");
            for (int j = 0; j < sp; j++) n.add(".");
        }

        int left = 0, right = n.size() - 1;
        while (left < right) {
            if (!n.get(right).equals(".")) {
                while (left < right) {
                    if (n.get(left).equals(".")) {
                        String temp = n.get(right);
                        n.set(right, ".");
                        n.set(left, temp);
                        break;
                    }
                    left++;
                }
            }
            right--;
        }

        long rslt = 0;
        for (long i = 0; i < n.size(); i++) {
            if (n.get((int) i).equals(".")) break;
            rslt += i * (Long.parseLong(n.get((int) i)));
        }

        pw.println(rslt);
        pw.close();
        br.close();
    }
}