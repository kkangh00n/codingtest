package dp;

import java.util.Scanner;

/**
 * DP
 * dp[2] = 3개
 * dp[4] = dp[4-2]*dp[2]개 + 특별한 모양 2개 = 11개
 * dp[6] = dp[6-2]*dp[2]개 + 특별한 모양 2개 + dp[2]*2개(dp[4]에서 나올 수 있는 특별한 모양) = 41
 * dp[8] = dp[8-2]*dp[2]개 + 특별한 모양 2개 + dp[2]*2개(dp[6]에서 나올 수 있는 특별한 모양)
 *                                       + dp[4]*2개(dp[4]에서 나올 수 있는 특별한 모양) =
 *                                       .
 *                                       .
 * dp[i] = dp[i-2] * dp[2]개 + 특별한 모양 2개 + 2*(dp[2] + dp[4] + ... + dp[i-4])
 * -> dp[i] = dp[i-2]*dp[2]개 + dp[0] * 2개 + 2*(dp[2] + dp[4] + ... + dp[i-4])
 * -> dp[i] = dp[i-2]*dp[2]개 + 2*(dp[0] + dp[2] + dp[4] + ... + dp[i-4])
 */
public class BOJ_2133 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] dp = new int[31];

        dp[0] = 1;
        dp[1] = 0;
        dp[2] = 3;

        for (int i = 4; i <= 30; i+=2) {
            dp[i] = dp[i-2]*3;

            for (int j = 0; j <= i-4; j+=2) {
                dp[i]+=dp[j]*2;
            }
        }

        System.out.println(dp[N]);

    }
}
