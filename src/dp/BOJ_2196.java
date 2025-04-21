package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * DP
 * 각 칸마다 최대 과자 값을 갱신한다.
 *      - 왼쪽에서 오른쪽으로 이동하는 경우
 *      - 오른쪽에서 왼쪽으로 이동하는 경우
 *      - 위쪽에서 아랫쪽으로 이동하는 경우
 */

public class BOJ_2196 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        int[][] dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = map[0][0];

        //첫쨋 줄 dp 배열 초기화
        for (int i = 1; i < M; i++) {
            dp[0][i] = dp[0][i-1] + map[0][i];
        }

        //둘쨋 줄부터 dp 배열 초기화
        for (int i = 1; i < N; i++) {
            int[] leftToRight = new int[M];
            int[] rightToLeft = new int[M];

            //첫 칸은 무조건 위에서 온 것으로 채워짐
            leftToRight[0] = dp[i-1][0] + map[i][0];
            //왼쪽에서 오른쪽
            for (int j = 1; j < M; j++) {
                leftToRight[j] = Math.max(leftToRight[j-1], dp[i-1][j]) + map[i][j];
            }

            rightToLeft[M-1] = dp[i-1][M-1] + map[i][M-1];
            for (int j = M-2; j >= 0; j--) {
                rightToLeft[j] = Math.max(rightToLeft[j+1], dp[i-1][j]) + map[i][j];
            }

            for (int j = 0; j < M; j++) {
                dp[i][j] = Math.max(leftToRight[j], rightToLeft[j]);
            }
        }

        System.out.println(dp[N-1][M-1]);
    }
}
