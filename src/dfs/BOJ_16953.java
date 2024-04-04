package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16953 {

    static long ans;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //초기 입력 값
        long A = Long.parseLong(st.nextToken());
        //결과값
        long B = Long.parseLong(st.nextToken());

        DFS(A, B, 1);

        long answer = flag?ans:-1;
        System.out.println(answer);
    }

    public static void DFS(long current, long goal, long count) {

        if(current>goal) {
            return;
        }

        if(current==goal) {
            ans=count;
            flag=true;
            return;
        }

        DFS(current*2, goal, count+1);
        String s = current+"1";
        DFS(Long.parseLong(s), goal, count+1);

    }
}
