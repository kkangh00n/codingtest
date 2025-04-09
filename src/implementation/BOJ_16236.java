package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1. 아기 상어는 1초에 상화좌우로 인접한 한 칸씩 이동한다.
 * 2. 아기 상어는 처음 크기가 2이다.
 * 3. 아기 상어는 자기보다 큰 물고기가 있는 칸은 지나갈 수 없다.
 *  - 크기가 같은 물고기가 있는 칸은 지나갈 수 있지만, 물고기를 잡아먹을 수 없다.
 *  - 크기가 작은 물고기가 있는 칸은 지나감과 동시에 물고기를 잡아먹는다.
 * 4. 이동 방법
 *  - 먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
 *  - 먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러 간다.
 *  - 이 때, 가장 위쪽 -> 가장 왼쪽부터 먹는다.
 * 5. 아기 상어는 자기의 크기와 같은 수의 물고기를 먹을 때마다 크기가 1 증가한다.
 *
 * 입력
 * 공간의 크기 N
 * 공간 상태 배열
 *
 * 출력
 * 아기 상어가 더 이상 움직이지 못 할 떄까지 시간
 *
 * 풀이 - BFS
 * 1. 아기 상어 위치에서 가장 가까운 위치의 물고기를 찾아 먹으러 간다. (가장 위쪽, 가장 왼쪽)
 * - 이 때, 자기가 먹을 수 있는 물고기는 크기보다 작아야 한다.
 * - 1초 씩 증가한다.
 * 2. 자기 크기보다 작은 물고기를 발견하면 물고기를 먹는다.
 * - 물고기의 갯수를 증가한다.
 *  - 현재 크기와 물고기의 갯수가 같다면, 현재 크기를 증가 후 물고기 갯수 초기화
 * 3. 물고기를 먹게 되면 해당 위치에서 BFS 탐색을 수행한다.
 * 4. 더 이상 물고기를 먹을 수 없다면, 시간 반환
 *
 * 참고
 * 가장 위쪽 -> 가장 왼쪽 조건을 지키는 것은 moved 순서를 조정한다고 해서 해결될 일이 아니였다.
 *
 */
public class BOJ_16236 {

    static int[][] moved = new int[][]{{-1, 0},{0, -1},{0, 1},{1, 0}};

    static int N;
    static int[][] map;

    static int sharkSize = 2;
    static int sharkEatCount = 0;

    static int totalTime = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        int currentX=0, currentY=0;

        //지도 저장 및 아기 상어 위치 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 9) {
                    currentX = i;
                    currentY = j;
                }
            }
        }

        while(true) {
            map[currentX][currentY] = 0;

            //현재 포인트에서 BFS 탐색을 통해 가장 가까운 먹을 수 있는 물고기를 탐색한다.
            SharkPoint result = BFS(currentX, currentY);

            //만약 아기상어의 위치가 변하지 않았다면, 먹을 물고기가 없는 상태이므로 반복문을 종료한다.
            if (result.x == currentX && result.y == currentY) {
                break;
            }

            //아기 상어 성장
            sharkEatCount++;

            if (sharkEatCount == sharkSize) {
                sharkSize++;
                sharkEatCount = 0;
            }

            //아기 상어의 현재 위치를 갱신
            currentX = result.x;
            currentY = result.y;
            map[currentX][currentY] = 9;
            totalTime += result.time;

//            for (int i = 0; i < N; i++) {
//                for (int j = 0; j < N; j++) {
//                    System.out.print(map[i][j] + " ");
//                }
//                System.out.println();
//            }

//            System.out.println("time= " + result.time);
//            System.out.println("sharkSize= " + sharkSize);
//            System.out.println("sharkEatCount= " + sharkEatCount);
//            System.out.println();
        }

        System.out.println(totalTime);
    }

    public static SharkPoint BFS(int startX, int startY) {
        int resultX = startX;
        int resultY = startY;
        int resultTime = 0;

        List<SharkPoint> possibleSharkPoint = new ArrayList<>();

        Queue<SharkPoint> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        queue.add(new SharkPoint(startX, startY, 0));
        visited[startX][startY] = true;

        while(!queue.isEmpty()) {
            SharkPoint sharkPoint = queue.poll();

            //만약 현재 도달한 위치의 물고기가 먹을 수 있다면, 후보군 저장
            if (map[sharkPoint.x][sharkPoint.y] != 0 && map[sharkPoint.x][sharkPoint.y] < sharkSize) {
                possibleSharkPoint.add(sharkPoint);
            }

            //다음 위치 탐색
            for (int[] m : moved) {

                int nextX = sharkPoint.x + m[0];
                int nextY = sharkPoint.y + m[1];

                //지도를 벗어나면 continue
                if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) {
                    continue;
                }

                //이미 지나온 곳이라면 continue
                if (visited[nextX][nextY]) {
                    continue;
                }

                //물고기의 크기가 아기상어보다 크다면 continue
                if (map[nextX][nextY] > sharkSize) {
                    continue;
                }

                queue.add(new SharkPoint(nextX, nextY, sharkPoint.time + 1));
                visited[nextX][nextY] = true;
            }
        }

        //정렬 기준
        //1. 가장 짧은 거리
        //2. 가장 위쪽
        //3. 가장 왼쪽
        Collections.sort(possibleSharkPoint);

        if (!possibleSharkPoint.isEmpty()) {
            SharkPoint minDistanceSharkPoint = possibleSharkPoint.get(0);
            resultX = minDistanceSharkPoint.x;
            resultY = minDistanceSharkPoint.y;
            resultTime = minDistanceSharkPoint.time;
        }

        return new SharkPoint(resultX, resultY, resultTime);
    }
}

class SharkPoint implements Comparable<SharkPoint>{
    int x;
    int y;
    int time;

    SharkPoint(int x, int y, int time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }

    //1. 시간이 가장 짧은 곳
    @Override
    public int compareTo(SharkPoint o) {
        if (this.time == o.time) {
            if (this.x == o.x) {
                return this.y - o.y;
            }
            return this.x - o.x;
        }
        return this.time - o.time;
    }
}
