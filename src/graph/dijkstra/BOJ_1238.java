package graph.dijkstra;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_1238 {

    static ArrayList<Edge3>[] edges;
    static int N;
    static int X;

    static int[] times;
    static boolean[] visited;

    static int[] timesBeta;
    static boolean[] visitedBeta;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        int M = sc.nextInt();
        X = sc.nextInt();

        edges = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int time = sc.nextInt();

            edges[from].add(new Edge3(to, time));
        }

        DijkstraBeta();

        int max = Integer.MIN_VALUE;

        for (int i = 1; i <= N; i++) {
            if (i == X) {
                continue;
            }

            int goToParty = Dijkstra(i, X);
            int goHome = timesBeta[i];
            int temp = goToParty + goHome;

            max = Math.max(max, temp);
        }

        System.out.println(max);
    }

    public static int Dijkstra(int from, int to) {
        visited = new boolean[N + 1];
        times = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            times[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Edge3> queue = new PriorityQueue<>();
        queue.add(new Edge3(from, 0));

        while (!queue.isEmpty()) {
            Edge3 current = queue.poll();
            int vertex = current.vertex;
            int time = current.time;

            if (visited[vertex]) {
                continue;
            }

            visited[vertex] = true;
            times[vertex] = time;

            for (Edge3 nextEdge : edges[vertex]) {
                int nextVertex = nextEdge.vertex;
                int nextTime = nextEdge.time + time;

                queue.add(new Edge3(nextVertex,
                        Math.min(nextTime, times[nextVertex])));
            }
        }

        return times[to];
    }

    public static void DijkstraBeta() {
        visitedBeta = new boolean[N + 1];
        timesBeta = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            timesBeta[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Edge3> queue = new PriorityQueue<>();
        queue.add(new Edge3(X, 0));

        while (!queue.isEmpty()) {
            Edge3 current = queue.poll();
            int vertex = current.vertex;
            int time = current.time;

            if (visitedBeta[vertex]) {
                continue;
            }

            visitedBeta[vertex] = true;
            timesBeta[vertex] = time;

            for (Edge3 nextEdge : edges[vertex]) {
                int nextVertex = nextEdge.vertex;
                int nextTime = nextEdge.time + time;

                queue.add(new Edge3(nextVertex,
                        Math.min(nextTime, timesBeta[nextVertex])));
            }
        }
    }
}

class Edge3 implements Comparable<Edge3> {

    int vertex;
    int time;

    Edge3(int vertex, int time) {
        this.vertex = vertex;
        this.time = time;
    }

    @Override
    public int compareTo(Edge3 o) {
        return this.time - o.time;
    }
}
