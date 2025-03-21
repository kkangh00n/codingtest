package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 배열의 두 정수를 골라서 더할 때, 가장 0에 가까운 조합을 골라라
 * 입력은 오름차순으로 입력된다.
 * 출력은 조합 두 수를 출력한다.
 *
 * 20억개이므로 단순 반복문 불가
 * 이분탐색으로 시간복잡도 줄이기
 */
public class BOJ_2467 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int currentMin = Integer.MAX_VALUE;
        int minLeft = Integer.MIN_VALUE;
        int minRight = Integer.MAX_VALUE;

        int leftIndex = 0;
        int rightIndex = N-1;
        while(leftIndex<rightIndex) {
            //절댓값
            int result = arr[leftIndex] + arr[rightIndex];
            int resultAbs = Math.abs(result);

            //만약 0에 더 가깝다면, 갱신
            if (resultAbs<currentMin) {
                currentMin = resultAbs;
                minLeft = arr[leftIndex];
                minRight = arr[rightIndex];
            }

            if (result > 0) {
                rightIndex--;
            }
            else if (result < 0) {
                leftIndex++;
            }
            else {
                break;
            }
        }

        System.out.println(minLeft + " " + minRight);
    }
}
