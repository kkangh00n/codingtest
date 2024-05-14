package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_11403 {

    static int N;
    static int[][] arr;
    static boolean[] visited;
    static int[][] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        //정점의 갯수
        N = Integer.parseInt(st.nextToken());
        //인접 배열 초기화
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int number = Integer.parseInt(st.nextToken());
                if(number==1) {
                    arr[i][j] = 1;
                }
            }
        }
        //정답 저장 배열
        answer = new int[N][N];

        //DFS 탐색
        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            for (int j = 0; j < N; j++) {
                if(arr[i][j] == 1 && !visited[j]) {
                    DFS(i, j);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(answer[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void DFS(int x, int y) {
        visited[y] = true;
        answer[x][y] = 1;
        for (int i = 0; i < N; i++) {
            if(arr[y][i] == 1 && !visited[i]) {
                DFS(x, i);
            }
        }
    }
}
