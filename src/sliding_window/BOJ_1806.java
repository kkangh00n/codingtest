package sliding_window;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 10000 이하의 수로 이루어진 수열이 있다.
 * 2. 해당 수열에서 S 이상이 되는, 가장 짧은 것의 길이를 구한다.
 *
 * 슬라이딩 윈도우
 *
 */
public class BOJ_1806 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;

        int total = 0;
        int answer = Integer.MAX_VALUE;

        while(left<=N && right<=N) {
            //만약 현재 부분합이 S 이상이라면
            if (total >= S) {
                answer = Math.min(answer, right-left);
                total -= arr[left++];
            }
            //현재 부분합이 S가 되지 않는다면
            else {
                total += arr[right++];
            }
        }

        System.out.println(answer == Integer.MAX_VALUE?0:answer);
    }
}
