package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13398 {

    public static void main(String[] args) throws IOException {
        int answer;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //맨 왼쪽부터 i까지 연속 합 최대 값 저장
        int[] L = new int[N];
        L[0] = arr[0];
        answer = L[0];
        //맨 오른쪽부터 i까지 연속 합 최대 값 저장
        int[] R = new int[N];
        R[N - 1] = arr[N - 1];

        //i-1번째 연속 합 최대 값에서 현재 값을 더해본다
        //만약 현재 arr[i]보다 작다면, 연속 최대합은 끊긴다.
        for (int i = 1; i < N; i++) {
            L[i] = Math.max(L[i - 1] + arr[i], arr[i]);
            answer = Math.max(answer, L[i]);
        }
        for (int i = N - 2; i >= 0; i--) {
            R[i] = Math.max(R[i + 1] + arr[i], arr[i]);
        }

        //i번째 값을 지웠다고 가정하고,
        //i번째까지 왼쪽에서의 연속 합 최대값과, 오른쪽에서의 연속 합 최대값을 더한다
        //기존의 answer와 비교한다.
        for (int i = 1; i < N - 1; i++) {
            int temp = L[i - 1] + R[i + 1];

            answer = Math.max(answer, temp);
        }

        System.out.println(answer);


        //https://www.acmicpc.net/problem/13398
    }
}
