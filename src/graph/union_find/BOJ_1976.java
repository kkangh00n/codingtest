package graph.union_find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1976 {

    static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //도시의 갯수
        int N = Integer.parseInt(br.readLine());
        //여행 갈 도시의 수
        int M = Integer.parseInt(br.readLine());

        //도시 정보 저장
        int[][] dosi = new int[N+1][N+1];
        //여행 정보 저장
        int[] route = new int[M+1];

        //집합 저장
        parent = new int[N+1];
        for (int i = 1; i < N+1; i++) {
            parent[i] = i;
        }

        //도시 정보 저장
        StringTokenizer st;
        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N+1; j++) {
                dosi[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //여행 정보 저장
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < M+1; i++) {
            route[i] = Integer.parseInt(st.nextToken());
        }

        //union 연산
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                if(dosi[i][j] == 1) {
                    union(i, j);
                }
            }
        }

        int start = find(route[1]);
        for (int i = 2; i < M+1; i++) {
            if(start != find(route[i])) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a != b) {
            parent[b] = a;
        }
    }

    public static int find(int a) {
        if(parent[a] == a) {
            return a;
        }
        parent[a] = find(parent[a]);
        return parent[a];
    }
}
