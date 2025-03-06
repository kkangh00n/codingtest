package dijkstra;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_1504 {

    static final int INF = 200000000;

    static ArrayList<Edge2>[] vertexes;
    static int[] dis;
    static boolean[] visited;

    static int N;
    static int E;

    static int n1;
    static int n2;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        E = sc.nextInt();

        vertexes = new ArrayList[N + 1];
        for (int i = 0; i < vertexes.length; i++) {
            vertexes[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int d = sc.nextInt();

            vertexes[from].add(new Edge2(to, d));
            vertexes[to].add(new Edge2(from, d));
        }

        n1 = sc.nextInt();
        n2 = sc.nextInt();

        int solution1 = Dijkstra(1, n1) + Dijkstra(n1, n2) + Dijkstra(n2,N);
        int solution2 = Dijkstra(1, n2) + Dijkstra(n2, n1) + Dijkstra(n1, N);

        int answer;

        if (solution1 >= INF || solution2 >= INF) {
            answer = -1;
        } else {
            answer = Math.min(solution1, solution2);
        }

        System.out.println(answer);
    }

    public static int Dijkstra(int from, int to) {
        dis = new int[N + 1];
        visited = new boolean[N + 1];
        for (int i = 0; i < dis.length; i++) {
            dis[i] = INF;
        }

        PriorityQueue<Edge2> queue = new PriorityQueue<>();
        queue.add(new Edge2(from, 0));

        while (!queue.isEmpty()) {
            Edge2 edge = queue.poll();
            int vertex = edge.vertex;

            if (visited[vertex]) {
                continue;
            }

            visited[vertex] = true;
            dis[vertex] = edge.distance;

            ArrayList<Edge2> edges = vertexes[vertex];
            for (Edge2 nextEdge : edges) {
                int nextVertex = nextEdge.vertex;
                int nextDistance = nextEdge.distance + edge.distance;

                queue.add(new Edge2(nextVertex,
                        Math.min(dis[nextVertex], nextDistance)));
            }
        }

        return dis[to];
    }
}

class Edge2 implements Comparable<Edge2> {

    int vertex;
    int distance;

    Edge2(int vertex, int distance) {
        this.vertex = vertex;
        this.distance = distance;
    }

    @Override
    public int compareTo(Edge2 o) {
        return this.distance - o.distance;
    }
}
