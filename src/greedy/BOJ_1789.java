package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1789 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());

        int current = 1;
        int answer = 0;
        while (N > 0) {
            if (current > N) {
                break;
            }
            N-=current;
            current++;
            answer++;
        }

        System.out.println(answer);
    }
}
