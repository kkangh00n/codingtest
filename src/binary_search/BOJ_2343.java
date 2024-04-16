package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2343 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //강의 갯수
        int N = Integer.parseInt(st.nextToken());
        //블루레이 희망 갯수
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int start=0;
        int end=0;
        //강의들의 크기
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            //start -> 강의 중 크기의 최대값
            if(start<arr[i]) start = arr[i];
            //end -> 강의의 합
            end = end + arr[i];
        }

        int middle;
        while(start<=end) {
            middle = (start+end)/2;
            int count = 0;
            int sum = 0;

            for (int i = 0; i < N; i++) {
                //강의의 합이 블루레이의 크기를 넘어갈 경우 초기화
                if(sum+arr[i]>middle) {
                    sum=0;
                    count++;
                }
                sum += arr[i];
            }

            //마지막에 잔여 강의가 남아있다면, 블루레이 갯수 증가
            if(sum!=0) {
                count++;
            }
            //블루레이 갯수가 희망 갯수보다 크다면, 블루레이 크기를 증가
            if(count>M) {
                start = middle+1;
            }
            //블루레이 갯수가 희망 갯수보다 작다면, 블루레이 크기 감소
            else {
                end = middle-1;
            }
        }

        System.out.println(start);
    }
}
