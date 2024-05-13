package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11663 {

    static int[] dots;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        dots = new int[N];
        int M = Integer.parseInt(st.nextToken());

        //점 정보 저장
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int dot = Integer.parseInt(st.nextToken());
            dots[i] = dot;
        }

        Arrays.sort(dots);

        StringBuilder answer = new StringBuilder();
        //선분 정보
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            //선분의 처음과 끝의 인덱스의 차이를 구한다.
            int result = binarySearch(x, y);
            answer.append(result + "\n");
        }
        System.out.println(answer);
    }

    public static int binarySearch(int x, int y) {
        int start = 0;
        int end = dots.length - 1;

        while (start <= end) {
            int middle = (start + end) / 2;

            if (dots[middle] > y) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }
        int endIndex = end;

        start = 0;
        end = dots.length - 1;
        while (start <= end) {
            int middle = (start + end) / 2;

            if (dots[middle] < x) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }

        }
        int startIndex = start;

        return endIndex - startIndex + 1;
    }
}
