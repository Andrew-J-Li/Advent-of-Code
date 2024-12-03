import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Day1 {

    public static class Pair {
        int val, index;

        public Pair(int v, int i) {
            val = v;
            index = i;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Day1.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("Day1.out")));

        ArrayList<Pair> arr1 = new ArrayList<>();
        ArrayList<Pair> arr2 = new ArrayList<>();
        int index = 0;
        while (br.ready()) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Pair p1 = new Pair(Integer.parseInt(st.nextToken()), index);
            Pair p2 = new Pair(Integer.parseInt(st.nextToken()), index);
            arr1.add(p1);
            arr2.add(p2);
            index++;
        }

        Collections.sort(arr1, (Pair a, Pair b) -> (a.val == b.val) ? a.index - b.index : a.val - b.val);
        Collections.sort(arr2, (Pair a, Pair b) -> (a.val == b.val) ? a.index - b.index : a.val - b.val);
        long sum = 0;
        for (int i = 0; i < arr1.size(); i++) {
            sum += Math.abs(arr1.get(i).val - arr2.get(i).val);
        }

        HashSet<Integer> hs = new HashSet<>();
        for (Pair i : arr1) {
            hs.add(i.val);
        }

        long part2 = 0;
        for (Integer curr : hs) {
            long temp = (long) curr;
            long count = 0;
            for (Pair i : arr2) {
                if (i.val == temp) {
                    count++;
                }
            }
            part2 += temp * count;
        }


        pw.println(part2);
        pw.close();
        br.close();
    }
}