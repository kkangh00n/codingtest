package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * DP
 *
 * dp 배열 -> 각 i번째 까지 칠하는데 드는 최소 비용 합
 * 3개의 열로 나눔 -> 빨초파 구분
 */
public class BOJ_1149 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());


        int[][] dp = new int[N+1][3];

        StringTokenizer st;
        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());

            int R = Integer.parseInt(st.nextToken());
            int G = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            dp[i][0] = Math.min(R+dp[i-1][1], R+dp[i-1][2]);
            dp[i][1] = Math.min(G+dp[i-1][0], G+dp[i-1][2]);
            dp[i][2] = Math.min(B+dp[i-1][1] , B+dp[i-1][0]);
        }

        System.out.println(Math.min(dp[N][0], Math.min(dp[N][1], dp[N][2])));
    }
}
