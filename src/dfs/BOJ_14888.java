package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14888 {

    static int[] nums;
    static int[] yeon;

    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        //수 입력
        st = new StringTokenizer(br.readLine());
        nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        //연산자 입력
        st = new StringTokenizer(br.readLine());
        yeon = new int[4];
        for (int i = 0; i < 4; i++) {
            yeon[i] = Integer.parseInt(st.nextToken());
        }

        DFS(0,0, 0, 0);

        System.out.println(max);
        System.out.println(min);
    }

    //value의 numIndex 번호를 yeonIndex 연산한다.
    public static void DFS(int numIndex, int yeonIndex, int value, int depth) {
        if (yeonIndex == 0) {
            value = value + nums[numIndex];
        }
        else if(yeonIndex == 1) {
            value = value - nums[numIndex];
        }
        else if(yeonIndex == 2) {
            value = value * nums[numIndex];
        }
        else if(yeonIndex == 3) {
            value = value / nums[numIndex];
        }

        if (depth == N-1) {
            min = Math.min(min, value);
            max = Math.max(max, value);
        }

        //연산자 탐색
        for (int i=0; i<4; i++) {
            if (yeon[i]>0) {
                yeon[i] -= 1;
                DFS(numIndex+1, i, value, depth+1);
                yeon[i] += 1;
            }
        }


    }
}
