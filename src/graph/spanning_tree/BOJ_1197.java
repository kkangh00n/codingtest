package graph.spanning_tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_1197 {

    static int V;
    static int E;

    static int[] unf;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        V = sc.nextInt();
        E = sc.nextInt();

        System.out.println(Kruskal(sc));
        System.out.println(Prim(sc));
    }


    public static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);

        if (fa != fb) {
            unf[fa] = fb;
        }
    }

    public static int find(int i) {
        if (i == unf[i]) {
            return i;
        }
        unf[i] = find(unf[i]);
        return unf[i];
    }

    public static int Kruskal(Scanner sc) {
        unf = new int[V + 1];
        for (int i = 1; i < V + 1; i++) {
            unf[i] = i;
        }

        List<Edge4> list = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();

            list.add(new Edge4(from, to, cost));
        }

        Collections.sort(list);

        int answer = 0;

        for (Edge4 edge4 : list) {
            int from = edge4.from;
            int to = edge4.to;
            int cost = edge4.cost;

            if (find(from) != find(to)) {
                union(from, to);
                answer += cost;
            }
        }

        return answer;
    }

    public static int Prim(Scanner sc) {
        ArrayList<Edge3>[] vertexes = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            vertexes[i] = new ArrayList<>();
        }
        boolean[] visited = new boolean[V + 1];

        for (int i = 0; i < E; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();

            vertexes[from].add(new Edge3(to, cost));
            vertexes[to].add(new Edge3(from, cost));
        }

        PriorityQueue<Edge3> queue = new PriorityQueue<>();
        queue.add(new Edge3(1, 0));

        int answer = 0;

        while (!queue.isEmpty()) {
            Edge3 currentEdge = queue.poll();
            int currentVertex = currentEdge.vertex;
            int currentCost = currentEdge.cost;

            if (visited[currentVertex]) {
                continue;
            }

            visited[currentVertex] = true;
            answer += currentCost;

            for (Edge3 nextEdge : vertexes[currentVertex]) {
                queue.add(nextEdge);
            }
        }

        return answer;
    }
}

class Edge3 implements Comparable<Edge3> {

    int vertex;
    int cost;

    Edge3(int vertex, int cost) {
        this.vertex = vertex;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge3 o) {
        return this.cost - o.cost;
    }
}

class Edge4 implements Comparable<Edge4> {

    int from;
    int to;
    int cost;

    Edge4(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge4 o) {
        return this.cost - o.cost;
    }
}
