package dfs;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_1707 {

    static int[] vertexesVisited;
    static ArrayList<Integer>[] edge;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        while (T-- > 0) {
            //정점의 갯수
            int V = sc.nextInt();
            //간선의 갯수
            int E = sc.nextInt();

            //정점
            vertexesVisited = new int[V + 1];
            //간선
            edge = new ArrayList[V + 1];
            for (int i = 1; i < V + 1; i++) {
                edge[i] = new ArrayList<>();
            }

            //간선 입력
            for (int i = 0; i < E; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();

                edge[u].add(v);
                edge[v].add(u);
            }

            //모든 정점을 대상으로 DFS
            for (int i = 1; i < vertexesVisited.length; i++) {
                if (vertexesVisited[i] == 0) {
                    vertexesVisited[i] = 1;
                }
                DFS(i);
            }

            boolean isBipartiteGraph = true;

            for (int i = 1; i < vertexesVisited.length; i++) {
                if (vertexesVisited[i] == 2) {
                    isBipartiteGraph = false;
                    break;
                }
            }

            System.out.println(isBipartiteGraph?"YES":"NO");
        }
    }

    public static void DFS(int currentVertex) {
        for (Integer nextVertex : edge[currentVertex]) {

            //다음 노드중 하나를 이미 방문했다면
            if (vertexesVisited[nextVertex] !=0) {
                //그런데 다음 노드의 색깔이 현재 노드의 색깔과 같다면
                if (vertexesVisited[currentVertex] == vertexesVisited[nextVertex]) {
                    vertexesVisited[nextVertex] = 2;
                    return;
                }

                continue;
            }

            vertexesVisited[nextVertex] = vertexesVisited[currentVertex] * -1;
            DFS(nextVertex);
        }
    }

}
