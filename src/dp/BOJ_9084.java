package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 냅색 알고리즘
 *
 * N : 코인 갯수
 * M : 동전으로 만드는 방법의 갯수가 궁금한 금액
 *
 * 1. dp 배열
 * - i : 금액
 * - dp[i] : 금액을 만드는 방법의 수
 *
 * 2. 점화식
 * - 각 금액 별 가질 수 있는 방법의 수 갱신
 * - dp[j] = dp[j] + dp[j-coin]
 */
public class BOJ_9084 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        int N;
        int[] coins;

        //테스트
        while(T--> 0) {
            //동전의 종류
            N = Integer.parseInt(br.readLine());
            coins = new int[N];

            //동전 입력
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }

            //동전 정렬
            Arrays.sort(coins);

            //만들 수 있는 경우의 수를 알고 싶은 금액
            int M = Integer.parseInt(br.readLine());

            //dp 배열 선언
            int[] dp = new int[M+1];
            //0을 만드는 경우는 1가지
            dp[0] = 1;

            for (int i = 0; i < coins.length; i++) {
                int coin = coins[i];

                for (int j = coin; j < dp.length; j++) {
                    //각 금액을 만드는 갯수 = 현재 금액 만드는 갯수 + 현재 금액-현재 동전 만드는 갯수
                    dp[j] = dp[j] + dp[j-coin];
                }
            }

            System.out.println(dp[M]);
        }
    }
}
