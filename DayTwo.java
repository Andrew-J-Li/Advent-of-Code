import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DayTwo {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("DayTwo.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("DayTwo.out")));
        int count = 0;
        while (br.ready()) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            ArrayList<Integer> reports = new ArrayList<>();
            while (st.hasMoreTokens()) {
                reports.add(Integer.parseInt(st.nextToken()));
            }
            boolean safe = false; 
            for (int i = 0; i < reports.size(); i++) {
                for (int j = 0; j < reports.size(); j++) {
                    ArrayList<Integer> temp = new ArrayList<>();
                    for (int k = 0; k < reports.size(); k++) {
                        if (k == j) continue;
                        else temp.add(reports.get(k));
                    }
                    if (safe(temp)) {
                        safe = true;
                        count++;
                        break;
                    }
                }
                if (safe) break;
            }
        }

        pw.println(count);
        br.close();
        pw.close();
    }


    public static boolean safe(ArrayList<Integer> reports) {
        if (reports.size() == 1) {
            return true;
        }
        if (reports.get(0) == reports.get(1)) {
            return false;
        }

        boolean inc = false;
        boolean unsafe = false;
        if (reports.get(0) < reports.get(1)) inc = true;
        for (int i = 1; i < reports.size(); i++) {
            if (inc) {
                if (reports.get(i - 1) < reports.get(i)) {
                    if (reports.get(i) - reports.get(i - 1) > 3) {
                        unsafe = true;
                        break;
                    }
                } else {
                    unsafe = true;
                    break;
                }
            } else {
                if (reports.get(i - 1) > reports.get(i)) {
                    if (reports.get(i - 1) - reports.get(i) > 3) {
                        unsafe = true;
                        break;
                    }
                } else {
                    unsafe = true;
                    break;
                }
            }
        }

        return !unsafe;
    }
}