import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Day5 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Day5.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("Day5.out")));
        HashMap<Integer, ArrayList<Integer>> adj = new HashMap<Integer, ArrayList<Integer>>();
        
        while (br.ready()) {
            String[] curr = br.readLine().split("\\|");
            if (curr.length == 1) break;

            if (!adj.containsKey(Integer.parseInt(curr[0]))) adj.put(Integer.parseInt(curr[0]), new ArrayList<>());
            adj.get(Integer.parseInt(curr[0])).add(Integer.parseInt(curr[1]));
        }

        ArrayList<String[]> correct = new ArrayList<>();
        while (br.ready()) {
            String update = br.readLine();
            HashSet<Integer> prev = new HashSet<>();
            String[] curr = update.split(",");
            boolean good = true;

            for (int i = 0; i < curr.length; i++) {
                prev.add(Integer.parseInt(curr[i]));
                if (!adj.containsKey(Integer.parseInt(curr[i]))) continue;
                for (int before : adj.get(Integer.parseInt(curr[i]))) {
                    if (prev.contains(before)) {
                        good = false;
                        break;
                    }
                }
                if (!good) break;
            }

            if (good) correct.add(curr);
        }

        int sum = 0;
        for (String[] s : correct) {
            sum += Integer.parseInt(s[s.length / 2]);
        }

        pw.println(sum);

        pw.close();
        br.close();
    }
}