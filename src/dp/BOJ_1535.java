package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 냅색 알고리즘
 *
 * N : 사람의 수
 * H[] -> 각 사람에게 인사할 때 잃는 체력
 * J[] -> 각 사람에게 인사할 때 얻는 기쁨
 *
 * 1. dp 배열 선언
 * - i -> 잃은 체력
 * - dp[i] -> 얻는 기쁨
 */
public class BOJ_1535 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] H = new int[N];
        int[] J = new int[N];

        //체력 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            H[i] = Integer.parseInt(st.nextToken());
        }
        //기쁨 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            J[i] = Integer.parseInt(st.nextToken());
        }

        //dp 배열 선언
        int[] dp = new int[100];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 0;
        }

        //사람 탐색
        for (int i = 0; i < N; i++) {
            int currentHealth = H[i];
            int currentJoy = J[i];

            //★ 체력 별 최대 기쁨 저장
            for (int j = dp.length-1; j >= currentHealth; j--) {
                dp[j] = Math.max(dp[j], dp[j-currentHealth] + currentJoy);
            }
        }

        System.out.println(Arrays.stream(dp).max().getAsInt());
    }
}