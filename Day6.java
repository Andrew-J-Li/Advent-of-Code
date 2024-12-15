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
        int[][] dirs = new int[grid.length][grid[0].length];
        dirs[x][y] = dir;
        int rslt = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '#' || (i == x && j == y)) continue;
                grid[i][j] = '#';
                if (verify(x, y, dir, grid, dirs)) rslt++;
                grid[i][j] = '.';
                for (int a = 0; a < grid.length; a++) {
                    for (int b = 0; b < grid[a].length; b++) {
                        if (grid[a][b] == 'X') grid[a][b] = '.';
                    }
                }
            }
        }

        pw.println(rslt);

        pw.close();
        br.close();
    }


    public static boolean verify(int x, int y, int dir, char[][] grid, int[][] dirs) {
        while (true) {
            if (dir == 0) {
                if (x - 1 < 0) break;
                else if (grid[x - 1][y] == '#') {
                    dir++;
                    continue;
                }
                else x--;
            }
            else if (dir == 1) {
                if (y + 1 >= grid[x].length) break;
                else if (grid[x][y + 1] == '#') {
                    dir++;
                    continue;
                }
                else y++;
            }
            else if (dir == 2) {
                if (x + 1 >= grid[x].length) break;
                else if (grid[x + 1][y] == '#') {
                    dir++;
                    continue;
                }
                else x++;
            }
            else {
                if (y - 1 < 0) break;
                else if (grid[x][y - 1] == '#') {
                    dir = 0;
                    continue;
                }
                else y--;
            }
            
            if (grid[x][y] == 'X' && dirs[x][y] == dir) return true;

            dirs[x][y] = dir;
            grid[x][y] = 'X';
        }

        return false;
    }
}

