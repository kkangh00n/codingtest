package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15900 {

    static int N;
    static List<Integer>[] trees;
    static boolean[] visited;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //트리 정점의 갯수
        N = Integer.parseInt(st.nextToken());

        //간선(인접리스트) 초기화
        trees = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            trees[i] = new ArrayList<>();
        }

        //인접리스트 초기화
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            trees[a].add(b);
            trees[b].add(a);
        }

        DFS(1, 0);

        System.out.println(answer%2!=0?"Yes":"No");
    }

    static void DFS(int current, int cnt) {
        //방문 완료
        visited[current] = true;

        //자식 노드 방문
        for (Integer next : trees[current]) {
            if (!visited[next]) {
                DFS(next, cnt+1);
            }
        }

        if (current != 1 && trees[current].size() == 1) {
            answer += cnt;
        }
    }
}
