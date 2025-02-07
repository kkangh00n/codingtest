package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2776 {

    static int T;

    static int N;

    static int[] ARR;

    static int M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            ARR = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                ARR[j] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(ARR);

            M = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int start = 0;
                int end = N-1;
                int question = Integer.parseInt(st.nextToken());
                boolean find = false;

                while (start <= end) {
                    int mid = (start + end) / 2;

                    if (ARR[mid] == question) {
                        find = true;
                        break;
                    }
                    else if (ARR[mid] < question) {
                        start = mid + 1;
                    } else {
                        end = mid - 1;
                    }
                }

                sb.append(find ? 1 : 0).append("\n");
            }
        }

        System.out.print(sb);

    }
}
