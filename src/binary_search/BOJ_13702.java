package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 막걸리 수 (N) <= 친구들 수 (K)
 *
 *
 *
 */
public class BOJ_13702 {

    static long[] makgulries;

    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 막걸리 수
        N = Integer.parseInt(st.nextToken());

        // 나눠줄 친구 수
        K = Integer.parseInt(st.nextToken());

        makgulries = new long[N];

        //막걸리 정보 저장
        for (int i = 0; i < makgulries.length; i++) {
            st = new StringTokenizer(br.readLine());

            makgulries[i] = Long.parseLong(st.nextToken());
        }

        if (Arrays.stream(makgulries).sum() == 0) {
            System.out.println(0);
            return;
        }

        //막걸리 정렬
        Arrays.sort(makgulries);

        //나눠줄 막걸리 용량 최대 최소
        long answer = 0;
        long left = 1;
        long right = makgulries[makgulries.length-1];

        //이분 탐색
        while(left<=right) {

            //막걸리 용량
            long mid = (left+right)/2;

            //3명에게 나눠줄 수 있으면, 저장 후 막걸리 용량 늘인다.
            if (canShare(mid)) {
                answer = mid;
                left = mid+1;
            }
            //3명에게 나눠줄 수 없다면, 막걸리 용량을 줄인다.
            else {
                right = mid-1;
            }
        }

        System.out.println(answer);
    }

    public static boolean canShare(long size) {
        long result = 0;

        for (int i = 0; i < makgulries.length; i++) {
            result += makgulries[i]/size;
        }

        return result>=K;
    }
}
