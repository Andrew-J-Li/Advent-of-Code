// import java.io.BufferedReader;
// import java.io.BufferedWriter;
// import java.io.FileReader;
// import java.io.FileWriter;
// import java.io.IOException;
// import java.io.PrintWriter;
// import java.util.Deque;
// import java.util.LinkedList;
// import java.util.StringTokenizer;

// public class Day11 {


//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new FileReader("Day11.in"));
//         PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("Day11.out")));
//         StringTokenizer st = new StringTokenizer(br.readLine());
//         Deque<Long> q = new LinkedList<>();
//         Deque<Long> q2 = new LinkedList<>();
//         while (st.hasMoreTokens()) {
//             long temp = Long.parseLong(st.nextToken());
//             q.add(temp);
//             q2.add(temp);
//         }
//         int n = 75;

//         // for (int i = 0; i < n + 1; i++) {
//         //     int stat = q2.size();
//         //     System.out.print(i + ": ");
//         //     for (int j = 0; j < stat; j++) {
//         //         long curr = q2.removeFirst();
//         //         if (curr == 0) q2.addLast(Long.valueOf(1));
//         //         else {
//         //             long test = curr;
//         //             int dig = 0;
//         //             while (test != 0) {
//         //                 test /= 10;
//         //                 dig++;
//         //             }
//         //             if (dig % 2 == 0) {
//         //                 q2.addLast(Long.parseLong(Long.toString(curr).substring(0, dig / 2)));
//         //                 q2.addLast(Long.parseLong(Long.toString(curr).substring(dig / 2)));
//         //             } else {
//         //                 q2.addLast(curr * Long.valueOf(2024));
//         //             }
//         //         }
//         //     }
//         //     System.out.println();
//         // }

//         long[][] dp = new long[n + 1][10];

//         int idx = 0;
//         while (!q.isEmpty() && idx < n + 1) {
//             int stat = q.size();
//             for (int j = 0; j < stat; j++) {
//                 long curr = q.poll();
//                 if (curr / 10 == 0) {
//                     dp[idx][(int) curr]++;
//                 } else {
//                     if (idx + 1 == n + 1) {
//                         q.addLast(curr);
//                         continue;
//                     }
//                     long test = curr;
//                     int dig = 0;
//                     while (test != 0) {
//                         test /= 10;
//                         dig++;
//                     }
//                     if (dig % 2 == 0) {
//                         q.addLast(Long.parseLong(Long.toString(curr).substring(0, dig / 2)));
//                         q.addLast(Long.parseLong(Long.toString(curr).substring(dig / 2)));
//                     } else {
//                         q.addLast(curr * Long.valueOf(2024));
//                     }
//                 }
//             }
//             idx++;
//         }
//         long rslt = q.size();
//         long prev = 0;
//         for (int i = 0; i < n; i++) {
//             System.out.print(i + ": ");
//             for (int j = 0; j < 10; j++) {
//                 if (dp[n][0] != prev) {
//                     prev = dp[n][0];
//                     System.out.println(dp[n][0]);
//                 }
//                 if (dp[i][j] == 0) continue;
//                 System.out.print(dp[i][j] + "-" + j + " ");
//                 if (j == 0) {
//                     dp[i + 1][1] += dp[i][j];
//                     continue;
//                 }
//                 if (j <= 4) {
//                     if (i + 2 == n) rslt += Long.valueOf(2) * dp[i][j];
//                     else if (i + 1 == n) rslt += dp[i][j];
//                     if (i + 2 == n || i + 1 == n) continue;

//                     if (j == 1) {
//                         dp[i + 3][2] += Long.valueOf(2) * dp[i][j];
//                         dp[i + 3][0] += dp[i][j];
//                         dp[i + 3][4] += dp[i][j];
//                     } else if (j == 2) {
//                         dp[i + 3][4] += Long.valueOf(2) * dp[i][j];
//                         dp[i + 3][0] += dp[i][j];
//                         dp[i + 3][8] += dp[i][j];
//                     } else if (j == 3) {
//                         dp[i + 3][6] += dp[i][j];
//                         dp[i + 3][0] += dp[i][j];
//                         dp[i + 3][7] += dp[i][j];
//                         dp[i + 3][2] += dp[i][j];
//                     } else if (j == 4) {
//                         dp[i + 3][8] += dp[i][j];
//                         dp[i + 3][0] += dp[i][j];
//                         dp[i + 3][9] += dp[i][j];
//                         dp[i + 3][6] += dp[i][j];
//                     }
//                 } else {
//                     if (i + 4 == n) rslt += Long.valueOf(4) * dp[i][j];
//                     else if (i + 3 == n) rslt += Long.valueOf(2) * dp[i][j];
//                     else if (i + 2 == n || i + 1 == n) rslt += dp[i][j];
//                     if (i + 4 == n || i + 3 == n || i + 2 == n || i + 1 == n) continue;

//                     if (j == 5) {
//                         dp[i + 5][2] += Long.valueOf(2) * dp[i][j];
//                         dp[i + 5][0] += Long.valueOf(2) * dp[i][j];
//                         dp[i + 5][4] += dp[i][j];
//                         dp[i + 5][8] += Long.valueOf(3) * dp[i][j];
//                     } else if (j == 6) {
//                         dp[i + 5][2] += dp[i][j];
//                         dp[i + 5][4] += Long.valueOf(2) * dp[i][j];
//                         dp[i + 5][5] += Long.valueOf(2) * dp[i][j];
//                         dp[i + 5][7] += dp[i][j];
//                         dp[i + 5][9] += dp[i][j];
//                         dp[i + 5][6] += dp[i][j];
//                     } else if (j == 7) {
//                         dp[i + 5][2] += Long.valueOf(2) * dp[i][j];
//                         dp[i + 5][8] += dp[i][j];
//                         dp[i + 5][6] += Long.valueOf(2) * dp[i][j];
//                         dp[i + 5][7] += dp[i][j];
//                         dp[i + 5][0] += dp[i][j];
//                         dp[i + 5][3] += dp[i][j];
//                     } else if (j == 8) {
//                         dp[i + 5][3] += dp[i][j];
//                         dp[i + 5][2] += Long.valueOf(2) * dp[i][j];
//                         dp[i + 5][7] += Long.valueOf(2) * dp[i][j];
//                         dp[i + 5][6] += dp[i][j];
//                         dp[i + 5][0] += dp[i][j];
//                         dp[i + 5][8] += dp[i][j];
//                     } else if (j == 9) {
//                         dp[i + 5][3] += dp[i][j];
//                         dp[i + 5][6] += Long.valueOf(2) * dp[i][j];
//                         dp[i + 5][8] += Long.valueOf(2) * dp[i][j];
//                         dp[i + 5][1] += dp[i][j];
//                         dp[i + 5][9] += dp[i][j];
//                         dp[i + 5][4] += dp[i][j];
//                     }
//                 }
//             }
//             System.out.println();
//         }

//         for (int i = 0; i < 10; i++) {
//             rslt += dp[n][i];
//         }
//         System.out.println(rslt);
//         // System.out.println(q2.size());
//         br.close();
//         pw.close();
//     }
// }


import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Day11 {

    private static final int YEAR = 2024;
    private static final int DAY = 11;
    private static final String SEPARATOR = " ";

    public static List<String> stepStone(String s) {
        if ("0".equals(s)) {
            return Collections.singletonList("1");
        }

        int length = s.length();
        if (length % 2 == 0) {
            int cutLine = length / 2;
            return Arrays.asList(
                String.valueOf(Long.parseLong(s.substring(0, cutLine))),
                String.valueOf(Long.parseLong(s.substring(cutLine)))
            );
        }

        return Collections.singletonList(String.valueOf(Long.parseLong(s) * 2024));
    }

    public static int part1(List<String> input) {
        List<String> stones = new ArrayList<>(input);

        for (int i = 0; i < 25; i++) {
            stones = stones.stream()
                           .flatMap(stone -> stepStone(stone).stream())
                           .collect(Collectors.toList());
        }

        return stones.size();
    }

    public static long part2(List<String> input) {
        Map<String, Long> stones = input.stream()
                                           .collect(Collectors.toMap(stone -> stone, stone -> 1L));

        for (int i = 0; i < 75; i++) {
            Map<String, Long> newStones = new HashMap<>();

            for (Map.Entry<String, Long> entry : stones.entrySet()) {
                String stone = entry.getKey();
                long count = entry.getValue();

                for (String newStone : stepStone(stone)) {
                    newStones.put(newStone, newStones.getOrDefault(newStone, 0L) + count);
                }
            }

            stones = newStones;
        }

        return stones.values().stream().mapToLong(Long::valueOf).sum();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Day11.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("Day11.out")));
        StringTokenizer st = new StringTokenizer(br.readLine());

        List<String> input = new ArrayList<>();
        while (st.hasMoreTokens()) {
            input.add(st.nextToken());
        }

        // pw.println(part1(input));
        pw.println(part2(input));

        br.close();
        pw.close();
    }
}
