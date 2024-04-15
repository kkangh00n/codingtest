package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14940 {
    static int N;
    static int M;
    static int[] goal;

    static int[][] map;
    static int[][] distance;
    static boolean[][] visited;

    static int[][] moved = new int[][]{
        {-1,0}, {0,-1}, {1,0}, {0,1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //지도
        map = new int[N][M];
        //시작지점으로부터의 거리
        distance = new int[N][M];
        //방문 현황
        visited = new boolean[N][M];

        //지도 형성
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                //목표지점 위치 저장
                if (map[i][j] == 2) {
                    goal = new int[]{i, j};
                }
            }
        }

        //목표지점에서 너비우선탐색 진행한다!
        BFS(new int[]{goal[0], goal[1]});

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int temp;
                if(map[i][j] == 1 && !visited[i][j]) {
                    temp = -1;
                }
                else{
                    temp = distance[i][j];
                }
                sb.append(temp+" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void BFS(int[] start) {
        //목표지점
        int x = start[0];
        int y = start[1];

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;

        while(!queue.isEmpty()) {
            //현재 위치
            int[] current = queue.poll();
            int currentX = current[0];
            int currentY  = current[1];

            int nextX;
            int nextY;

            //사방으로 이동
            for (int[] m : moved) {
                nextX = currentX + m[0];
                nextY = currentY + m[1];

                //다음 지역이 지도를 벗어나면 패스
                if(nextX<0 || nextX>=N || nextY<0 || nextY>=M) {
                    continue;
                }
                //이미 방문했다면
                if(visited[nextX][nextY]) {
                    continue;
                }
                //다음 지역이 벽이라면 패스
                if(map[nextX][nextY]==0) {
                    continue;
                }

                //다음 지역 걸리는 거리 설정
                distance[nextX][nextY] = distance[currentX][currentY] + 1;
                visited[nextX][nextY] = true;
                queue.add(new int[]{nextX, nextY});
            }
        }

    }
}
