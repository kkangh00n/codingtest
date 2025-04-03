package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 포도주를 연속으로 3잔을 마실 수 없다.
 * 많은 양의 포도주를 마시고 싶다.
 *
 * dp 배열
 * i: 해당 자리
 * dp[i]: 해당 자리에 있을 때의 마신 최대 포도주 양
 * a. 포도주를 세 잔 연속으로 마신 경우 -> dp[i-1]
 * b. 포도주를 두 잔 마시고 한 잔 건너뛰고 마신 경우, -> dp[i-2] + arr[i]
 * c. 포도주를 두 잔 마시고 두 잔 건너뛰고 마신 경우, -> dp[i-3] + arr[i-1] + arr[i]
 */
public class BOJ_2156 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N + 1];
        int[] dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = arr[1];
        if (N >= 2) {
            dp[2] = arr[1] + arr[2];
        }

        for (int i = 3; i <= N; i++) {
            dp[i] = Math.max(Math.max(dp[i-3] + arr[i-1] + arr[i], dp[i-2] + arr[i]), dp[i-1]);
        }

        System.out.println(dp[N]);

    }
}
