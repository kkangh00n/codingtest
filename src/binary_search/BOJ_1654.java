package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1654 {

    //이미 가지고 있는 랜선의 갯수
    static int K;
    static int[] lans;

    //필요한 랜선의 갯수
    static int N;

    public static void main(String[] args) throws IOException {

        init();
        long result = find();

        System.out.println(result);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //이미 가지고 있는 랜선의 갯수
        K = Integer.parseInt(st.nextToken());
        lans = new int[K];
        //필요한 랜선의 갯수
        N = Integer.parseInt(st.nextToken());

        //이미 가지고 있는 랜선 정보 저장
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int lan = Integer.parseInt(st.nextToken());
            lans[i] = lan;
        }
        Arrays.sort(lans);
    }

    private static long find() {
        //최소는 0, 최대는 랜선 중 가장 짧은 녀석
        long start = 1;
        long end = lans[K-1];
        long middle;
        long count;

        while(start<=end) {
            count = 0;
            middle = (start + end)/2;

            //랜선을 잘 나누어서 나올 수 있는 수를 구한다.
            for (int i = 0; i < K; i++) {
                count += lans[i]/middle;
            }

            //필요한 랜선 갯수보다 많이 나온다면, 좀 더 길게 자른다.
            if(count<N) {
                end = middle - 1;
            }
            //필요한 갯수보다 덜 나온다면, 좀 더 짧게 자른다.
            else {
                start = middle + 1;
            }
        }
        return end;
    }
}
