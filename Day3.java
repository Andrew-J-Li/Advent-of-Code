import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Day3 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Day3.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("Day3.out")));
        StringBuilder str = new StringBuilder();
        while (br.ready()) {
            str.append(br.readLine());
        }

        long sum = 0;
        String itr = str.toString();
        boolean enable = true;
        for (int i = 0; i + 4 <= itr.length(); i++) {
            if (itr.substring(i, i + 4).equals("do()")) {
                enable = true;
                continue;
            }

            if (i + 7 <= itr.length() && itr.substring(i, i + 7).equals("don't()")) {
                enable = false;
                continue;
            }
            if (enable && itr.substring(i, i + 4).equals("mul(")) {
                int temp = i + 4;
                if (!(temp < itr.length())) continue;
                if (Character.isDigit(itr.charAt(temp))) temp++;

                if (!(temp < itr.length())) continue;
                if (Character.isDigit(itr.charAt(temp))) temp++;

                if (!(temp < itr.length())) continue;
                if (Character.isDigit(itr.charAt(temp))) temp++;

                if (!(temp < itr.length())) continue;
                if (temp == i + 3) continue;
                if (Character.isDigit(itr.charAt(temp))) continue;
                if (itr.charAt(temp) != ',') continue;
                temp++;

                if (!(temp < itr.length())) continue;

                int check = temp;
                if (Character.isDigit(itr.charAt(temp))) temp++;

                if (!(temp < itr.length())) continue;
                if (Character.isDigit(itr.charAt(temp))) temp++;

                if (!(temp < itr.length())) continue;
                if (Character.isDigit(itr.charAt(temp))) temp++;

                if (!(temp < itr.length())) continue;
                if (temp == check) continue;
                if (Character.isDigit(itr.charAt(temp))) continue;
                if (itr.charAt(temp) != ')') continue;

                String curr = itr.substring(i + 4, temp);
                String[] mult = curr.split(",");
                sum += Integer.parseInt(mult[0]) * Integer.parseInt(mult[1]);
            }
        }

        pw.println(sum);
        br.close();
        pw.close();
    }
}