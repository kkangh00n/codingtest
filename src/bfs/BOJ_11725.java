package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_11725 {

    static ArrayList<Integer>[] lines;
    static int[] parents;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //노드 갯수
        int N = Integer.parseInt(br.readLine());
        //노드 연결을 나타낼 배열
        lines = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            lines[i] = new ArrayList<>();
        }
        //부모를 저장할 배열
        parents = new int[N + 1];
        //방문 현황
        visited = new boolean[N + 1];

        //노드 연결 정보 저장
        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            lines[start].add(end);
            lines[end].add(start);
        }

//        BFS(1);
        DFS(1);

        for (int i = 2; i <= N; i++) {
            System.out.println(parents[i]);
        }

    }

    public static void DFS(int start) {
        visited[start] = true;
        for (Integer next : lines[start]) {
            if(!visited[next]) {
                parents[next] = start;
                DFS(next);
            }
        }
    }


    public static void BFS(int start) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            Integer current = queue.poll();

            for (int next : lines[current]) {
                if (!visited[next]) {
                    queue.add(next);
                    parents[next] = current;
                    visited[next] = true;
                }
            }

        }
    }
}
