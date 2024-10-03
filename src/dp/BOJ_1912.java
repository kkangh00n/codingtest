package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1912 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //숫자의 갯수
        int N = Integer.parseInt(st.nextToken());

        //숫자를 담아 놓는 배열
        int[] arr = new int[N];
        //i번째의 최대 연속합 배열
        int[] dp = new int[N];

        //숫자 저장
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = arr[0];

        for (int i = 1; i < N; i++) {
            //i번째의 최대 연속합 = (이전 최대 연속합 + 현재 값) or 현재 값
            dp[i] = Math.max(dp[i-1] + arr[i], arr[i]);
        }

        int answer = Arrays.stream(dp).max().getAsInt();

        System.out.println(answer);
    }
}
