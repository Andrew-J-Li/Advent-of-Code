import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Day6 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Day6.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("Day6.out")));
        ArrayList<String> prim = new ArrayList<>();

        while (br.ready()) {
            prim.add(br.readLine());
        }

        int x = 0, y = 0;
        char[][] grid = new char[prim.size()][prim.get(0).length()];
        for (int i = 0; i < prim.size(); i++) {
            for (int j = 0; j < prim.get(i).length(); j++) {
                grid[i][j] = prim.get(i).charAt(j);
                if (prim.get(i).charAt(j) != '#' && prim.get(i).charAt(j) != '.') {
                    x = i;
                    y = j;
                }
            }
        }

        int dir = 0;
        char go = grid[x][y];
        if (go == '>') dir = 1;
        else if (go == 'v') dir = 2;
        else if (go == '<') dir = 3;
        grid[x][y] = '.';
        int rslt = 1;

        for (int i = 0; i < 1000000; i++) {
            if (dir == 0) {
                if (x - 1 < 0) break;
                else if (grid[x - 1][y] == '#') dir++;
                else x--;
            }
            else if (dir == 1) {
                if (y + 1 >= grid[x].length) break;
                else if (grid[x][y + 1] == '#') dir++;
                else y++;
            }
            else if (dir == 2) {
                if (x + 1 >= grid[x].length) break;
                else if (x + 1 >= grid[x].length || grid[x + 1][y] == '#') dir++;
                else x++;
            }
            else {
                if (y - 1 < 0) break;
                else if (grid[x][y - 1] == '#') dir = 0;
                else y--;
            }
            
            if (grid[x][y] != 'X') rslt++;
            grid[x][y] = 'X';
        }

        pw.println(rslt);

        pw.close();
        br.close();
    }
    
}
