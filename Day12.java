import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Day12 {

    public static class Pair {
        int area, perim;

        public Pair(int a, int p) {
            area = a;
            perim = p;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Day12.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("Day12.out")));
        ArrayList<String> a = new ArrayList<>();
        while (br.ready()) {
            a.add(br.readLine());
        }

        int rslt = 0;
        boolean[][] visited = new boolean[a.size()][a.get(0).length()];
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < a.get(i).length(); j++) {
                if (!visited[i][j]) {
                    Pair temp = dfs(a, visited, i, j, new Pair(0, 0), a.get(i).charAt(j));
                    rslt += temp.area * temp.perim;
                }
            }
        }


        pw.println(rslt);
        pw.close();
        br.close();
    }

    public static Pair dfs(ArrayList<String> a, boolean[][] visited, int i, int j, Pair curr, char comp) {
        if (visited[i][j]) return curr;
        visited[i][j] = true;
        curr.area++;
        int temp = 0;
        if (i - 1 < 0 || a.get(i - 1).charAt(j) != comp) temp++;
        else dfs(a, visited, i - 1, j, curr, comp);
        if (i + 1 >= a.size() || a.get(i + 1).charAt(j) != comp) temp++;
        else dfs(a, visited, i + 1, j, curr, comp);
        if (j - 1 < 0 || a.get(i).charAt(j - 1) != comp) temp++;
        else dfs(a, visited, i, j - 1, curr, comp);
        if (j + 1 >= a.get(0).length() || a.get(i).charAt(j + 1) != comp) temp++;
        else dfs(a, visited, i, j + 1, curr, comp);
        curr.perim += temp;

        return curr;
    }
}