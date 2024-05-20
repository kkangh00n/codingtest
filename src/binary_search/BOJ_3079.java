package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3079 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //심사대 수
        int N = Integer.parseInt(st.nextToken());
        //사람 수
        long M = Long.parseLong(st.nextToken());

        //심사대 시간
        long[] times = new long[N];
        //심사대 시간 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            times[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(times);

        //심사 받는 시간 최소 * 최대
        long left = 0;
        long right = times[N-1] * M;

        long answer = 0;

        while(left<=right) {
            long middle = (left + right)/2;

            long count = 0;

            //middle 시간 안에 심사 받을 수 있는 사람 수 총합
            for (long time : times) {

                /**
                 * 키포인트 -> 이미 심사 받아야 할 사람 수를 넘었는데 더 반복할 필요가 없음
                 */
                if(count >= M) {
                    break;
                }

                count += middle/time;

            }

            //심사 받을 수 있는 사람 수가 현재 사람 수보다 많다면
            if(count >= M) {
                //시간을 줄여본다.
                right = middle - 1;
                answer = middle;
            }
            else{
                left = middle + 1;
            }
        }

        System.out.println(answer);
    }
}
