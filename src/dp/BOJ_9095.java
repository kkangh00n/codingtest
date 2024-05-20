package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9095 {

    public static void main(String[] args) throws IOException {

        int[] D = new int[12];

        D[1] = 1;
        D[2] = 2;
        D[3] = 4;

        for (int i = 4; i <= 11; i++) {
            D[i] = D[i-1] + D[i-2] + D[i-3];
        }


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        int[] temp = new int[T];

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            temp[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < T; i++) {
            System.out.println(D[temp[i]]);
        }
    }
}
