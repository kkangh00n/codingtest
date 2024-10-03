package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14672 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //시장에서 사 온 파의 갯수
        int S = Integer.parseInt(st.nextToken());
        //주문 받은 파닭의 수
        int C = Integer.parseInt(st.nextToken());

        int start = 1;
        int end = 1_000_000_000;
        long total = 0;

        //파 길이 저장 목록
        int[] P = new int[S];

        //파 길이 저장
        for (int i = 0; i < S; i++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());

            P[i] = L;
        }

        //파의 총 길이
        for (int i : P) {
            total += i;
        }

        //이분 탐색
        while (start <= end) {
            //현재 파의 길이
            int middle = (start + end) / 2;

            //현재 파의 길이를 통해 각 파 통해서 얻을 수 있는 파닭 수
            int cnt = 0;
            for (int i = 0; i < S; i++) {
                cnt += P[i]/middle;
            }

            //측정 파닭 수가 주문 파닭 수보다 많을 경우, 파의 길이를 늘림
            if (cnt >= C) {
                start = middle + 1;
            }
            else {
                end = middle - 1;
            }
        }

        System.out.println(total - end * (long) C);
    }
}
