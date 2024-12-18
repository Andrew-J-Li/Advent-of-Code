import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Day10 {


    // static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Day10.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("Day10.out")));
        ArrayList<String> input = new ArrayList<>();
        while (br.ready()) input.add(br.readLine());

        int[][] rslt = new int[input.size()][input.get(0).length()];
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                // visited = new boolean[input.size()][input.get(0).length()];
                if (input.get(i).charAt(j) == '0') rslt[i][j] += dfs(i, j, input, 0); 
            }
        }

        int tot = 0;
        for (int i = 0; i < rslt.length; i++) {
            for (int j = 0; j < rslt[i].length; j++) {
                tot += rslt[i][j];
            }
        }

        pw.println(tot);
        pw.close();
        br.close();
    }

    public static int dfs(int i, int j, ArrayList<String> input, int curr) {
        if (i < 0 || i >= input.size() || j < 0 || j >= input.get(0).length()) return 0;
        if (input.get(i).charAt(j) - '0' == curr) {
            if (curr == 9) {
                // if (visited[i][j]) return 0;
                // visited[i][j] = true;
                return 1;
            }
            else {
                int up = dfs(i - 1, j, input, curr + 1);
                int down = dfs(i + 1, j, input, curr + 1);
                int left = dfs(i, j - 1, input, curr + 1);
                int right = dfs(i, j + 1, input, curr + 1);
                return up + down + left + right;
            }
        } else {
            return 0;
        }
    }
}