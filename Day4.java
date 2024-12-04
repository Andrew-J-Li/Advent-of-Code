import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Day4 {
    static int count = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Day4.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("Day4.out")));
        
        ArrayList<String> test = new ArrayList<>();
        while (br.ready()) {
            test.add(br.readLine());
        }

        count = 0;
        int rslt2 = 0;
        for (int i = 0; i < test.size(); i++) {
            for (int j = 0; j < test.get(i).length(); j++) {
                if (test.get(i).charAt(j) == 'A') {
                    int u = i - 1;
                    int d = i + 1;
                    int r = j + 1;
                    int l = j - 1;
                    if (u < 0 || l < 0 || d >= test.size() || r >= test.get(i).length()) {
                        continue;
                    }
                    boolean left = false;
                    boolean right = false;
                    if ((test.get(u).charAt(l) == 'M' && test.get(d).charAt(r) == 'S') || (test.get(u).charAt(l) == 'S' && test.get(d).charAt(r) == 'M')) left = true;
                    if ((test.get(u).charAt(r) == 'M' && test.get(d).charAt(l) == 'S') || (test.get(u).charAt(r) == 'S' && test.get(d).charAt(l) == 'M')) right = true;
                    if (left && right) rslt2++;

                }
            }
        }
        boolean[][] bw = new boolean[1][1];
        recurse(0, 0, test, 'A', bw, 0);

        pw.println(rslt2);
        br.close();
        pw.close();
    }

    public static boolean recurse(int x, int y, ArrayList<String> test, char curr, boolean[][] visited, int dir) {
        if (x < 0 || x >= test.size()) return false;
        if (y < 0 || y >= test.get(x).length()) return false;
        if (visited[x][y]) {
            visited[x][y] = false;
            return false;
        }
        if (test.get(x).charAt(y) != curr) return false;
        if (curr == 'S') return true;

        visited[x][y] = true;
        boolean found = false;
        if (curr == 'A') curr = 'S';
        if (curr == 'M') curr = 'A';
        if (curr == 'X') curr = 'M';
        if (dir == 0) {
            if (recurse(x + 1, y, test, curr, visited, dir)) {
                count++;
            }
        }
        if (dir == 1) {
            if (recurse(x, y + 1, test, curr, visited, dir)) {
                count++;
            }
        }
        if (dir == 2) {
            if (recurse(x + 1, y + 1, test, curr, visited, dir)) {
                count++;
            }
        }
        if (dir == 3) {
            if (recurse(x - 1, y, test, curr, visited, dir)) {
                count++;
            }
        }
        if (dir == 4) {
            if (recurse(x, y - 1, test, curr, visited, dir)) {
                count++;
            }
        }
        if (dir == 5) {
            if (recurse(x - 1, y - 1, test, curr, visited, dir)) {
                count++;
            }
        }
        if (dir == 6) {
            if (recurse(x + 1, y - 1, test, curr, visited, dir)) {
                count++;
            }
        }
        if (dir == 7) {
            if (recurse(x - 1, y + 1, test, curr, visited, dir)) {
                count++;
            }
        }
        visited[x][y] = false;
        return found;
    }
}
