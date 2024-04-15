package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9251 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //문자열을 받음
        String s1 = br.readLine();
        String[] s1Split = s1.split("");
        String s2 = br.readLine();
        String[] s2Split = s2.split("");

        int[][] arr = new int[(s1Split.length + 1)][(s2Split.length + 1)];

        for (int i = 1; i <= s1Split.length; i++) {
            for (int j = 1; j <= s2Split.length; j++) {
                if (s1Split[i - 1].equals(s2Split[j - 1])) {
                    arr[i][j] = arr[i - 1][j - 1] + 1;
                } else {
                    arr[i][j] = Math.max(arr[i - 1][j], arr[i][j - 1]);
                }
            }
        }

        System.out.println(arr[s1.length()][s2.length()]);

    }
}
