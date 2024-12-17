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
        ArrayList<ArrayList<String>> n = new ArrayList<>();

        for (int i = 0; i < s.length(); i += 2) {
            int c = s.charAt(i) - '0';
            int sp = (i + 1 < s.length()) ? s.charAt(i + 1) - '0': 0;
            n.add(new ArrayList<>());
            n.add(new ArrayList<>());
            for (int j = 0; j < c; j++) n.get(i).add(i / 2 + "");
            for (int j = 0; j < sp; j++) n.get(i + 1).add(".");
        }

        // int left = 0, right = n.size() - 1;
        // while (left < right) {
        //     if (!n.get(right).equals(".")) {
        //         while (left < right) {
        //             if (n.get(left).equals(".")) {
        //                 String temp = n.get(right);
        //                 n.set(right, ".");
        //                 n.set(left, temp);
        //                 break;
        //             }
        //             left++;
        //         }
        //     }
        //     right--;
        // }

        int right = n.size() - 1;
        while (right >= 0) {
            if (!n.get(right).isEmpty() && !n.get(right).get(0).equals(".")) {
                for (int i = 0; i < right; i++) {
                    if (n.get(i).isEmpty()) continue;
                    int consec = 0;
                    boolean valid = false;
                    for (int j = 0; j < n.get(i).size(); j++) {
                        if (n.get(i).get(j).equals(".")) consec++;
                        else consec = 0;
                        if (consec == n.get(right).size()) {
                            String rep = n.get(right).get(0);
                            int idx = 0;
                            while (consec >= 1) {
                                n.get(i).set(j + 1 - consec, rep);
                                n.get(right).set(idx, ".");
                                consec--;
                                idx++;
                            }
                            valid = true;
                            break;
                        }
                    }
                    if (valid) break;
                }
            }
            right--;
        }

        long rslt = 0;
        // for (long i = 0; i < n.size(); i++) {
        //     if (n.get((int) i).equals(".")) break;
        //     rslt += i * (Long.parseLong(n.get((int) i)));
        // }

        int idx = 0;
        for (int i = 0; i < n.size(); i++) {
            for (int j = 0; j < n.get(i).size(); j++) {
                if (!n.get(i).get(j).equals(".")) rslt += idx * Integer.parseInt(n.get(i).get(j));
                idx++;
            }
        }

        pw.println(rslt);
        pw.close();
        br.close();
    }
}