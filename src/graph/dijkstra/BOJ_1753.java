package graph.dijkstra;

import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_1753 {

    static int[] dis;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int V = sc.nextInt();
        int E = sc.nextInt();

        int start = sc.nextInt();

        dis = new int[V+1];
        for (int i = 0; i < V + 1; i++) {
            dis[i] = Integer.MAX_VALUE;
        }
        visited = new boolean[V + 1];

        ArrayList<Edge>[] edges = new ArrayList[V+1];
        for (int i = 0; i < V + 1; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();

            edges[from].add(new Edge(to, cost));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start,0));

        while(!pq.isEmpty()) {
            Edge edge = pq.poll();
            int current = edge.vertex;
            int currentCost = edge.cost;

            if (visited[current]) {
                continue;
            }

            visited[current] = true;
            dis[current] = currentCost;

            ArrayList<Edge> nextEdges = edges[current];
            for (Edge nextEdge : nextEdges) {
                int next = nextEdge.vertex;
                int nextCost = currentCost + nextEdge.cost;

                pq.add(new Edge(next, Math.min(dis[next], nextCost)));
            }
        }

        for (int i = 1; i < dis.length; i++) {
            if (dis[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
                continue;
            }
            System.out.println(dis[i]);
        }

    }
}

class Edge implements Comparable<Edge>{
    int vertex;
    int cost;

    Edge(int vertex, int cost){
        this.vertex = vertex;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return this.cost-o.cost;
    }
}