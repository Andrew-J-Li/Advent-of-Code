import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Day5 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Day5.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("Day5.out")));
        HashMap<Integer, ArrayList<Integer>> adj = new HashMap<>();
        
        while (br.ready()) {
            String[] curr = br.readLine().split("\\|");
            if (curr.length == 1) break;

            if (!adj.containsKey(Integer.parseInt(curr[0]))) adj.put(Integer.parseInt(curr[0]), new ArrayList<>());
            adj.get(Integer.parseInt(curr[0])).add(Integer.parseInt(curr[1]));
        }

        ArrayList<String[]> correct = new ArrayList<>();
        ArrayList<String[]> incorrect = new ArrayList<>();
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
            else incorrect.add(curr);
        }

        int sum = 0;
        for (String[] s : correct) {
            sum += Integer.parseInt(s[s.length / 2]);
        }

        pw.println(sum);
        ArrayList<String[]> nowCorrect = new ArrayList<>();

        for (String[] s : incorrect) {
            HashMap<Integer, ArrayList<Integer>> prereq = new HashMap<>();
            HashSet<Integer> inArray = new HashSet<>();
            for (String curr : s) {
                inArray.add(Integer.parseInt(curr));
                if (!adj.containsKey(Integer.parseInt(curr))) continue;
                for (int i : adj.get(Integer.parseInt(curr))) {
                    if (!prereq.containsKey(i)) prereq.put(i, new ArrayList<>());
                    prereq.get(i).add(Integer.parseInt(curr));
                }
            }

            Queue<Integer> q = new LinkedList<>();
            for (Integer i : inArray) {
                if (!prereq.containsKey(i)) q.add(i);
            }

            String[] better = new String[s.length];
            int idx = 0;
            HashSet<Integer> prevIncluded = new HashSet<>();
            while (!q.isEmpty()) {
                int curr = q.poll();
                prevIncluded.add(curr);
                better[idx] = Integer.toString(curr);
                idx++;

                for (Map.Entry<Integer, ArrayList<Integer>> entry : prereq.entrySet()) {
                    entry.getValue().remove((Integer) curr);
                    if (entry.getValue().size() == 0 && !prevIncluded.contains(entry.getKey()) && inArray.contains(entry.getKey())) {
                        q.add(entry.getKey());
                    }
                }
            }

            nowCorrect.add(better);
        }

        int sum2 = 0;
        for (String[] s : nowCorrect) {
            sum2 += Integer.parseInt(s[s.length / 2]);
        }

        pw.println(sum2);
        pw.close();
        br.close();
    }

    public static boolean ordered(String[] curr, HashMap<Integer, ArrayList<Integer>> adj) {
        HashSet<Integer> prev = new HashSet<>();
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

        return good;
    }
}