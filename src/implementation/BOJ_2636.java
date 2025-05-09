package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 표에는 치즈의 모양이 놓여져 있따.
 * 공기와 맞닿은 치즈는 한 시간이 지나면 녹아서 없어진다.
 * 단 치즈 내부에는 공기가 없지만, 치즈가 녹아서 구멍이 열리면 공기가 내부로 들어간다.
 *
 * 입력
 * 사각형 모양의 판 및 치즈의 모양
 * - 가로 세로 각각 최대 100칸
 * - 치즈 = 1, 치즈X = 0
 * 출력
 * 1. 공기 중에서 치즈가 모두 녹아 없어지는데 걸리는 시간
 * 2. 모두 녹기 한 시간 전에 남아있는 치즈 조각 수
 *
 * 풀이 - BFS
 * 1. (0.0)부터 BFS 탐색한다.
 * 2. BFS를 통해 치즈(1)와 만나게 된다면, 해당 부분을 2로 변경한다.
 * 3. 2를 0으로 일괄 변경한다.
 * 4. 반복한다.
 *
 */
public class BOJ_2636 {

    static int N, M;
    static int[][] cheese;
    static boolean[][] visited;

    static int[][] moved = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cheese = new int[N][M];

        int cheeseSize = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                cheese[i][j] = Integer.parseInt(st.nextToken());

                if (cheese[i][j] == 1) {
                    cheeseSize++;
                }
            }
        }

        int time = 0;
        int previousCheeseSize = 0;

        while(cheeseSize > 0) {
            time++;
            previousCheeseSize = cheeseSize;
            visited = new boolean[N][M];

            //1. 공기와 맞닿아있는 치즈 탐색
            int outsideCheese = findOutsideCheese(0, 0);

            //2. 현재 cheese크기에서 공기와 맞닿아 있는 치즈를 빼준다
            cheeseSize -= outsideCheese;
        }

        System.out.println(time);
        System.out.println(previousCheeseSize);




    }

    public static int findOutsideCheese(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,y});
        visited[x][y] = true;

        while(!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int[] m : moved) {
                int nextX = current[0] + m[0];
                int nextY = current[1] + m[1];

                if (nextX<0 || nextX>=N || nextY<0 || nextY>=M) {
                    continue;
                }

                if (visited[nextX][nextY]) {
                    continue;
                }

                if (cheese[nextX][nextY] == 2) {
                    continue;
                }

                //공기와 맞닿는 치즈일 경우, 바꿔주고 continue
                if (cheese[nextX][nextY] == 1) {
                    cheese[nextX][nextY] = 2;
                    continue;
                }

                //공기일 경우, 큐 삽입
                queue.add(new int[]{nextX, nextY});
                visited[nextX][nextY] = true;
            }
        }

        int outSideCheese = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (cheese[i][j] == 2) {
                    outSideCheese++;
                    cheese[i][j] = 0;
                }
            }
        }


        return outSideCheese;
    }
}
