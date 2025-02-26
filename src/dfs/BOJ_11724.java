package dfs;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_11724 {

    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static boolean[] visited;
    static int answer = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //정점 갯수
        int N = sc.nextInt();
        //간선 갯수
        int M = sc.nextInt();

        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }
        visited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();

            list.get(from).add(to);
            list.get(to).add(from);
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                answer++;
                DFS(i);
            }
        }

        System.out.println(answer);
    }

    public static void DFS(int v) {
        ArrayList<Integer> next = list.get(v);

        for (Integer n : next) {
            if (!visited[n]) {
                visited[n] = true;
                DFS(n);
            }
        }
    }

}
