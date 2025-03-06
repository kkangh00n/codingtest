package graph.spanning_tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//https://cote.inflearn.com/contest/10/problem/09-07

/**
 * 크루스칼 알고리즘 (union_find)
 */
public class SpanningTreeExWithKruskal {

    static ArrayList<Edge> list;
    static int[] unf;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();

        //유니온 파인드 배열 초기화
        unf = new int[V + 1];
        for (int i = 0; i < unf.length; i++) {
            unf[i] = i;
        }

        list = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int cost = sc.nextInt();

            list.add(new Edge(a, b, cost));
        }

        System.out.println(Kruskal());
    }

    public static int Kruskal() {

        Collections.sort(list);

        int answer = 0;

        for (Edge edge : list) {
            int a = edge.vertex1;
            int b = edge.vertex2;

            int fa = find(a);
            int fb = find(b);

            if (fa == fb) {
                continue;
            }

            union(a, b);
            answer += edge.cost;
        }

        return answer;
    }

    public static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);

        if(fa!=fb) {
            unf[fa] = fb;
        }
    }

    public static int find(int i) {
        if (i == unf[i]) {
            return i;
        }
        return find(unf[i]);
    }
}

class Edge implements Comparable<Edge> {

    int vertex1;
    int vertex2;
    int cost;

    Edge(int vertex1, int vertex2, int cost) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }
}
