package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2805 {

    static int N;
    static int M;
    static int[] trees;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //나무 갯수
        N = Integer.parseInt(st.nextToken());
        trees = new int[N];
        //가져가야 할 나무 길이
        M = Integer.parseInt(st.nextToken());

        //나무 길이 저장
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(trees);

        System.out.println(binarySearch());
    }

    public static int binarySearch() {
        int start = 0;
        int end = trees[N-1];

        while(start<=end) {
            int middle = (start+end)/2;
            long getTree = calculate(middle);

            //목표치보다 적게 얻었다면
            if(getTree<M) {
                //자르는 길이를 줄인다.
                end = middle - 1;
            }
            else {
                start = middle + 1;
            }
        }
        return start - 1;
    }

    public static long calculate(int middle) {
        long result = 0;
        for (int i = 0; i < N; i++) {
            if(trees[i]>middle) {
                result += (trees[i]-middle);
            }
        }
        return result;
    }
}
