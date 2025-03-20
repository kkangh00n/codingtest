package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 폴리오미노란?
 * 1. 1*1 정사각형을 여러 개 이어 붙인 도형
 * 2. 정사각형은 변끼리 연결되어 있어야 함
 *
 * 테트로미노란?
 * 정사각형 4개를 이어 붙인 폴리오미노
 *
 * 문제
 * N*M 종이 위에 테트로미노를 하나 놓을 때, 놓인 칸에 쓰여있는 수들의 합이 최대를 출력
 *
 * 입력
 * 종이의 세로 N와 가로 M
 * 종이의 쓰여 있는 수
 *
 * 풀이
 * 완전탐색인거 같음
 * 종이의 칸을 시작점으로 해서 전체를 DFS
 *
 * 예외 케이스
 * ㅗ 모양은 DFS로 불가능
 *
 * 어떻게 해야할까?
 * -> depth가 2인 DFS() 호출 시, 이전 좌표에서 호출하도록 추가 구현
 */
public class BOJ_14500 {

    static int[][] moved = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};

    static int[][] map;
    static boolean[][] visited;
    static int answer = 0;

    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //지도 및 방문배열 초기화
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //탐색
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                DFS(i,j,1, map[i][j]);
                visited[i][j] = false;
            }
        }

        System.out.println(answer);
    }

    public static void DFS(int x, int y, int depth, int total) {
        if (depth == 4) {
            answer = Math.max(answer, total);
            return;
        }

        for (int[] m : moved) {
            int nextX = x + m[0];
            int nextY = y + m[1];

            //좌표가 지도를 벗어났다면
            if (nextX<0||nextX>=N||nextY<0||nextY>=M) {
                continue;
            }

            //이미 방문한 곳이라면
            if(visited[nextX][nextY]) {
                continue;
            }

            //3번째 DFS 호출은 , 이전 좌표에서 DFS 탐색
            //ㅗ 모양을 해결하기 위함
            if (depth == 2) {
                visited[nextX][nextY] = true;
                DFS(x, y, depth+1, total + map[nextX][nextY]);
                visited[nextX][nextY] = false;
            }

            visited[nextX][nextY] = true;
            DFS(nextX, nextY, depth+1, total + map[nextX][nextY]);
            visited[nextX][nextY] = false;
        }
    }
}
