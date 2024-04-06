package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class BOJ_1758 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Integer[] peopleTips = new Integer[N];

        //전체 tip 입력
        for (int i = 0; i < N; i++) {
            peopleTips[i] = Integer.parseInt(br.readLine());
        }

        //내림차순 정렬
        Arrays.sort(peopleTips, Collections.reverseOrder());

        long realTipSum=0;
        for (int j = 0; j < N; j++) {
            int realTip = peopleTips[j] - j;

            if(realTip <=0) {
                break;
            }

            realTipSum+=realTip;
        }

        System.out.println(realTipSum);

    }
}
