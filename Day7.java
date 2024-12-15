import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;

public class Day7 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Day7.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("Day7.out")));
        ArrayList<String> ls = new ArrayList<>();
        while (br.ready()) {
            ls.add(br.readLine());
        }

        ArrayList<String[]> valid = new ArrayList<>();
        for (String s : ls) {
            String[] params = s.split("[:\\s]+");
            if (validate(params, 2, new BigInteger(params[1]))) valid.add(params);
        }

        BigInteger rslt = new BigInteger("0");
        for (String[] s : valid) {
            rslt = rslt.add(new BigInteger(s[0]));
        }

        pw.println(rslt);
        br.close();
        pw.close();
    }

    public static boolean validate(String[] test, int curr, BigInteger prev) {
        BigInteger total1 = prev.add(new BigInteger(test[curr]));
        BigInteger total2 = prev.multiply(new BigInteger(test[curr]));

        if (curr == test.length - 1) {
            if (total1.equals(new BigInteger(test[0])) || total2.equals(new BigInteger(test[0]))) return true;
            else return false;
        }

        return validate(test, curr + 1, total1) || validate(test, curr + 1, total2);

    }
}