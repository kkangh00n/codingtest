package sliding_window;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15565 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //라이온이 있는 위치
        List<Integer> lionNums = new ArrayList<>();

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            //라이온이라면 위치 저장
            int num = Integer.parseInt(st.nextToken());
            if (num == 1) {
                lionNums.add(i);
            }
        }

        int index = K-1;
        int answer = Integer.MAX_VALUE;

        if (K>lionNums.size()) {
            System.out.println(-1);
            return;
        }

        while(index<lionNums.size()) {
            int start = lionNums.get(index-K+1);
            int end = lionNums.get(index);

            answer = Math.min(answer, end-start+1);
            index++;
        }

        System.out.println(answer);
    }
}
