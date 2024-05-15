package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1010 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        long[][] D = new long[31][31];
        for (int i = 1; i <=30 ; i++) {
            D[i][1] = i;
            D[i][i] = 1;
        }

        for (int j = 3; j <= 30; j++) {
            for (int i = 2; i < j; i++) {
                D[j][i] = D[j-1][i-1] + D[j-1][i];
            }
        }

        int[][] temp = new int[T][2];
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            temp[i][0] = M;
            temp[i][1] = N;
        }

        for (int i = 0; i < T; i++) {
            System.out.println(D[temp[i][0]][temp[i][1]]);
        }
    }
}
