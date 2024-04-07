package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_2606 {

    static int answer = -1;
    static boolean[] visited;
    static ArrayList<Integer>[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //컴퓨터 갯수
        int N = Integer.parseInt(br.readLine());
        //네트워크 연결 수
        int M = Integer.parseInt(br.readLine());

        //방문 현황
        visited = new boolean[N + 1];

        //관계도
        arr = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            arr[i] = new ArrayList<>();
        }

        //관계도 세팅
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            arr[from].add(to);
            arr[to].add(from);
        }

        DFS(1);

        System.out.println(answer);
    }

    public static void DFS(int startNode) {
        //이미 방문했다면 return
        if(visited[startNode]) return;
        //방문 체크
        visited[startNode] = true;
        //방문 수 증가
        answer++;

        ArrayList<Integer> nextNodes = arr[startNode];

        for (Integer nextNode : nextNodes) {
            DFS(nextNode);
        }

    }
}
