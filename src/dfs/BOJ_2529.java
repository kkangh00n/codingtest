package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2529 {

    static String max = "" + Long.MIN_VALUE;
    static String min = "" + Long.MAX_VALUE;

    static int N;

    static int[] nums = new int[]{0,1,2,3,4,5,6,7,8,9};
    static boolean[] visited = new boolean[10];
    static char[] inputs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //입력
        N = Integer.parseInt(br.readLine());
        inputs = br.readLine().replace(" ", "").toCharArray();

        //0부터 차례대로 시작
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];

            visited[num] = true;
            DFS(num, 0, "");
            visited[num] = false;
        }

        System.out.println(max);
        System.out.println(min);
    }

    public static void DFS(int num, int depth, String current) {
        String next = current + num;

        //모든 부등호를 다 사용했다면
        if (depth >= N) {
            if (Long.parseLong(max)<Long.parseLong(next)) {
                max = next;
            }
            if (Long.parseLong(min)>Long.parseLong(next)) {
                min = next;
            }
            return;
        }

        if (inputs[depth] == '>') {
            for (int i = num-1; i >=0 ; i--) {
                if (visited[i]) {
                    continue;
                }
                visited[i] = true;
                DFS(i, depth+1, next);
                visited[i] = false;
            }
        }
        else {
            for (int i = num+1; i <nums.length; i++) {
                if (visited[i]) {
                    continue;
                }
                visited[i] = true;
                DFS(i, depth+1, next);
                visited[i] = false;
            }
        }
    }
}
