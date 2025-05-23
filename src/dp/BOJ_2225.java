package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2225 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] DP = new int[N+1][K+1];

        for (int i = 1; i <= N; i++) {
            DP[i][1] = 1;
        }

        for (int i = 1; i <= K; i++) {
            DP[1][i] = i;
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 2; j <= K; j++) {
                DP[i][j] = (DP[i-1][j] + DP[i][j-1])%1000000000;
            }
        }

        System.out.println(DP[N][K]%1000000000);
    }

}
