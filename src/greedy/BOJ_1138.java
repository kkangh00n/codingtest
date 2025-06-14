package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 배열을 0으로 초기화
 * 2. 1부터 차례대로 채워나간다.
 * - 이 때, 앞에 빈 칸은 무조건 큰 수로 간주한다.
 */
public class BOJ_1138 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] nums = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            int num = i;
            int order = Integer.parseInt(st.nextToken());

            int temp = 0;
            for (int j = 0; j < N; j++) {
                if (nums[j] == 0) {

                    if (temp != order) {
                        temp++;
                        continue;
                    }

                    nums[j] = num;
                    break;
                }
            }
        }

        for (int num : nums) {
            System.out.print(num + " ");
        }

    }
}
