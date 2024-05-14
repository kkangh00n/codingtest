package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1260 {
    static int N;
    static int M;
    static int V;
    static ArrayList<Integer>[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        //정점의 개수
        N = Integer.parseInt(st.nextToken());
        arr = new ArrayList[N+1];
        visited = new boolean[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }
        //간선의 개수
        M = Integer.parseInt(st.nextToken());
        //탐색을 시작할 정점의 번호
        V = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr[x].add(y);
            arr[y].add(x);
        }

        //정렬
        for (int i = 1; i <= N; i++) {
            Collections.sort(arr[i]);
        }

        printDFS(V);
        System.out.println();
        visited = new boolean[N+1];
        printBFS(V);
    }

    public static void printDFS(int current) {
        if(!visited[current]) {
            visited[current] = true;
            System.out.print(current + " ");
            for (Integer next : arr[current]) {
                printDFS(next);
            }
        }
    }

    public static void printBFS(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        System.out.print(start + " ");

        while(!queue.isEmpty()) {
            Integer current = queue.poll();
            for (Integer next : arr[current]) {
                if(!visited[next]) {
                    queue.add(next);
                    visited[next] = true;
                    System.out.print(next + " ");
                }
            }
        }
    }
}
