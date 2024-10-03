package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_17124 {

    static int[] A;
    static int[] B;

    static int n;
    static int m;

    static List<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        // 테스트 케이스만큼 실행
        for (int i = 0; i < T; i++) {

            // 배열 A,B,C 초기화
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            A = new int[n];
            B = new int[m];

            //배열 A,B 삽입
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                A[j] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                B[j] = Integer.parseInt(st.nextToken());
            }

            //배열 정렬
            Arrays.sort(B);

            //배열 A의 가장 가까운 값 이분 탐색
            int[] c = new int[n];
            for (int j = 0; j < n; j++) {

                boolean check = false;
                int start = 0;
                int end = m - 1;
                int number = A[j];

                //배열 A의 j번째 숫자 -> 가장 가까운 수 찾는 이분 탐색
                while (start + 1 < end) {
                    int middle = (start + end) / 2;

                    if (number == B[middle]) {
                        c[j] = B[middle];
                        check = true;
                        break;
                    } else if (B[middle] < number) {
                        start = middle;
                    } else if (B[middle] > number) {
                        end = middle;
                    }
                }

                if (!check) {
                    if (Math.abs(number - B[start]) == Math.abs(number - B[end])) {
                        c[j] = B[start];
                    } else if (Math.abs(number - B[start]) > Math.abs(number - B[end])) {
                        c[j] = B[end];
                    } else if (Math.abs(number - B[start]) < Math.abs(number - B[end])) {
                        c[j] = B[start];
                    }
                }
            }
            answer.add(Arrays.stream(c).sum());
        }

        for (Integer i : answer) {
            System.out.println(i);
        }

    }
}
