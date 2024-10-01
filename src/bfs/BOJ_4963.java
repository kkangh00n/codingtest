package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_4963 {

    static int[][] MAP;
    static boolean[][] VISITED;

    static int w;
    static int h;

    static int[][] MOVED = new int[][] {
            {1,0}, {-1,0}, {0,1}, {0,-1},
            {1,1}, {-1,1}, {1,-1}, {-1,-1},
    };

    public static void main(String[] args) throws IOException {

        List<Integer> answer = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //지도의 너비와 높이가 0일 때 종료
        while(true) {
            st = new StringTokenizer(br.readLine());

            //지도 너비 -> y
            w = Integer.parseInt(st.nextToken());
            //지도 높이 -> x
            h = Integer.parseInt(st.nextToken());
            int result = 0;

            if (w ==0 && h==0) break;

            //지도 초기화
            MAP = new int[h][w];
            VISITED = new boolean[h][w];

            //지도 설정
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    MAP[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            //지도 탐색
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (MAP[i][j] == 1 && !VISITED[i][j]) {
                        result++;
                        BFS(new int[]{i,j});
                    }
                }
            }
            answer.add(result);
        }

        for (Integer i : answer) {
            System.out.println(i);
        }
    }

    static void BFS(int[] start) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        VISITED[start[0]][start[1]] = true;

        while(!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int[] m : MOVED) {

                int x = current[0] + m[0];
                int y = current[1] + m[1];

                //지도 바깥이라면
                if (x>=h || y>=w || x<0 || y<0) {
                    continue;
                }

                //이미 방문했다면
                if (VISITED[x][y]) {
                    continue;
                }

                //육지가 아니라면
                if (MAP[x][y] == 0) {
                    continue;
                }

                queue.add(new int[]{x,y});
                VISITED[x][y] = true;
            }
        }
    }
}
