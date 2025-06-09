package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 */
public class BOJ_11057 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        //dp 배열 초기 셋팅
        int[][] dp = new int[N+1][10];
        for (int i = 0; i < 10; i++) {
            dp[1][i] = 1;
        }

        for (int i = 0; i <= N; i++) {
            dp[i][0] = 1;
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 1; j < 10; j++) {
                dp[i][j] = (dp[i-1][j] + dp[i][j-1])%10007;
            }
        }

//        for (int i = 0; i <= N; i++) {
//            for (int j = 0; j < 10; j++) {
//                System.out.print(dp[i][j]);
//            }
//            System.out.println();
//        }

        int answer = 0;
        for (int i = 0; i < 10 ; i++) {
            answer = (answer + dp[N][i])%10007;
        }

        System.out.println(answer);
    }
}
