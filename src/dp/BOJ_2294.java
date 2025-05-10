package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 냅색 알고리즘
 */
public class BOJ_2294 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] coins = new int[N];
        for (int i = 0; i < coins.length; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[K + 1];
        for (int i = 1; i <= K; i++) {
            dp[i] = Integer.MAX_VALUE-1;
        }

        Arrays.sort(coins);

        for (int i = 0; i < coins.length; i++) {

            int coin = coins[i];

            for (int j = coin; j < dp.length; j++) {
                dp[j] = Math.min(dp[j - coin] + 1, dp[j]);
            }

        }

        if (dp[K] == Integer.MAX_VALUE-1) {
            System.out.println(-1);
            return;
        }

        System.out.println(dp[K]);
    }
}
