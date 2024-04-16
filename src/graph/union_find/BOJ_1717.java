package graph.union_find;

import java.util.Scanner;

public class BOJ_1717 {

    static int[] parent;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //원소 갯수
        int n = sc.nextInt();
        //질의 갯수
        int m = sc.nextInt();

        parent = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            //질의 유형
            int question = sc.nextInt();
            int a = sc.nextInt();
            int b = sc.nextInt();

            //집합 합치기
            if (question == 0) {
                union(a, b);
            }
            //같은 집합인지 확인
            else {
                if (checkSame(a, b)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
    }

    //집합을 합친다.
    public static void union(int a, int b) {
        //a의 부모 노드
        a = find(a);
        //b의 부모 노드
        b = find(b);

        if (a != b) {
            parent[b] = a;
        }
    }

    //루트 노드를 찾는다.
    public static int find(int x) {
        //자신이 루트 노드라면 자기 자신 반환
        if (parent[x] == x) {
            return x;
        } else {
            parent[x] = find(parent[x]);
            return parent[x];
        }
    }

    public static boolean checkSame(int a, int b) {
        a = find(a);
        b = find(b);

        return a == b;
    }
}
