package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * 투 포인터 & 이분 탐색
 *
 * 입력
 * 수열의 길이 N, 차이 M
 * 수열 N개
 *
 * 출력
 * M 이상이면서 가장 작은 차이를 출력
 */
public class BOJ_2230 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Integer[] A = new Integer[N];

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }

        //내림차순 정렬
        Arrays.sort(A, Collections.reverseOrder());

        //최솟값
        int min = Integer.MAX_VALUE;

        //모든 배열 원소에 대해 이분탐색
        for (int i = 0; i < A.length-1; i++) {
            int current = A[i];

            int left = i;
            int right = A.length-1;

            while(left<=right) {
                int mid = (left + right) / 2;
                int midNum = A[mid];

                int difference = current - midNum;

                //두 수의 차가 M보다 크다면, 갱신 후
                if (difference >= M) {
                    min = Math.min(min, difference);
                    right = mid - 1;
                }
                else {
                    left = mid + 1;
                }
            }
        }
        System.out.println(min);
    }
}
