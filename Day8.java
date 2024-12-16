import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.awt.Point;

public class Day8 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Day8.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("Day8.out")));
        ArrayList<String> input = new ArrayList<>();
        while (br.ready()) {
            input.add(br.readLine());
        }

        HashMap<Character, ArrayList<Point>> locs = new HashMap<>();
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0; j < input.get(i).length(); j++) {
                if (input.get(i).charAt(j) != '.') {
                    if (!locs.containsKey(input.get(i).charAt(j))) locs.put(input.get(i).charAt(j), new ArrayList<>());
                    locs.get(input.get(i).charAt(j)).add(new Point(i, j));
                }
            }
        }

        HashSet<Point> nodes = new HashSet<>();
        for (Map.Entry<Character, ArrayList<Point>> entry : locs.entrySet()) {
            for (int i = 0; i < entry.getValue().size(); i++) {
                for (int j = i + 1; j < entry.getValue().size(); j++) {
                    Point p1 = entry.getValue().get(i);
                    Point p2 = entry.getValue().get(j);
                    int dx = (int) Math.abs(p1.getX() - p2.getX());
                    int dy = (int) Math.abs(p1.getY() - p2.getY());

                    Point n1 = new Point();
                    Point n2 = new Point();
                    if (p1.getX() < p2.getX()) {
                        n1.x = p1.x - dx;
                        n2.x = p2.x + dx;
                    } else {
                        n1.x = p1.x + dx;
                        n2.x = p2.x - dx;
                    }

                    if (p1.getY() < p2.getY()) {
                        n1.y = p1.y - dy;
                        n2.y = p2.y + dy;
                    } else {
                        n1.y = p1.y + dy;
                        n2.y = p2.y - dy;
                    }

                    if (n1.x >= 0 && n1.x < input.size() && n1.y >= 0 && n1.y < input.get(0).length()) nodes.add(n1);
                    if (n2.x >= 0 && n2.x < input.size() && n2.y >= 0 && n2.y < input.get(0).length()) nodes.add(n2);
                }
            }
        }

        pw.println(nodes.size());
        pw.close();
        br.close();
    }
}