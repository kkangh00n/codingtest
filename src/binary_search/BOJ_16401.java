package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 조카에게 과자를 가장 길고 공평하게 나눠주도록 해야 함
 */
public class BOJ_16401 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //조카의 수
        int M = Integer.parseInt(st.nextToken());

        //과자의 수
        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] snacks = new int[N];
        for (int i = 0; i < N; i++) {
            snacks[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(snacks);

        int left = 1;
        int right = snacks[snacks.length-1];

        while(left<=right) {
            int mid = (left+right)/2;

            //과자 수
            int cnt = 0;

            for (int i = 0; i < N; i++) {
                if (snacks[i]>=mid) {
                    cnt += snacks[i]/mid;
                }
            }

            //과자의 수가 조카의 수보다 크거나 같다면, 자르는 길이를 늘인다.
            if (cnt>=M) {
                left = mid + 1;
            }
            //과자의 수가
            else {
                right = mid - 1;
            }
        }

        System.out.println(right);
    }
}
