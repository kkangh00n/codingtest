package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1920 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int target;

        int middle;
        int middleValue;

        for (int i = 0; i < M; i++) {
            boolean find = false;

            target = Integer.parseInt(st.nextToken());
            int start = 0;
            int end = N-1;

            while (start<=end){
                middle = (start+end)/2;
                middleValue = A[middle];

                if(target>middleValue){
                    start = middle+1;
                }
                else if(target<A[middle]){
                    end = middle-1;
                }
                else {
                    find = true;
                    break;
                }
            }

            if(find) {
                System.out.println(1);
            }
            else{
                System.out.println(0);
            }

        }
    }
}
