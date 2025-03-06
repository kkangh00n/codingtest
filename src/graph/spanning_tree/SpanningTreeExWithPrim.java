package graph.spanning_tree;

//https://cote.inflearn.com/contest/10/problem/09-07

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 프림 알고리즘
 */
public class SpanningTreeExWithPrim {

    static ArrayList<Edge2>[] list;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();

        list = new ArrayList[V+1];
        for (int i = 0; i < list.length; i++) {
            list[i] = new ArrayList<>();
        }
        visited = new boolean[V + 1];

        for (int i = 0; i < E; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int cost = sc.nextInt();

            list[a].add(new Edge2(b, cost));
            list[b].add(new Edge2(a, cost));
        }

        System.out.println(Prim());
    }

    public static int Prim() {
        PriorityQueue<Edge2> queue = new PriorityQueue<>();
        queue.add(new Edge2(1, 0));

        int answer = 0;

        while(!queue.isEmpty()) {
            Edge2 current = queue.poll();

            if (visited[current.vertex]) {
                continue;
            }

            visited[current.vertex] = true;
            answer += current.cost;

            ArrayList<Edge2> edges = list[current.vertex];
            for (Edge2 edge : edges) {
                queue.add(edge);
            }
        }

        return answer;
    }
}

class Edge2 implements Comparable<Edge2>{

    int vertex;
    int cost;

    Edge2(int vertex, int cost) {
        this.vertex = vertex;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge2 o) {
        return this.cost - o.cost;
    }
}