package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_15903 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        PriorityQueue<Long> queue = new PriorityQueue<>();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            queue.add(Long.parseLong(st.nextToken()));
        }

        while(M-->0) {
            Long min1 = queue.poll();
            Long min2 = queue.poll();

            queue.add(min1+min2);
            queue.add(min1+min2);
        }

        long result = 0;
        while(!queue.isEmpty()) {
            result += queue.poll();
        }

        System.out.println(result);
    }
}
