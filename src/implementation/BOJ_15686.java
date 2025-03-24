package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * N : 지도 크기
 * M : 살려야 할 치킨 집 갯수
 *
 * 치킨 집을 M개를 고를 때, 나올 수 있는 도시의 최대 치킨 거리를 구하라
 *
 * 1. DFS를 통해, 치킨 집 M개를 고를 수 있는 경우의 수를 완전 탐색 한다.
 * - 치킨집 좌표를 리스트 저장한다.
 * - 치킨집 좌표 리스트에서 깊이가 M이 될 때 까지, 치킨집을 고를 수 있는 경우의 수를 탐색한다.
 * 2. 1번 과정에서 치킨 집 M개를 고르게 될 경우, BFS를 통해서 모든 지역에 대해 치킨 최단 거리를 구한다. -> BFS X 그냥 반복문으로 해결 가능
 *
 * 3. 0은 빈칸, 1은 집, 2는 치킨집
 */
public class BOJ_15686 {

    static List<Point> chickenList = new ArrayList<>();
    static List<Point> homeList = new ArrayList<>();

    static int[][] map;
    static boolean[] chickenChecked;

    static int N, M;
    static int minChickenDistance = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        //지도 셋팅 및 치킨 집 저장
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    homeList.add(new Point(i, j));
                }
                if (map[i][j] == 2) {
                    chickenList.add(new Point(i, j));
                }
            }
        }

        chickenChecked = new boolean[chickenList.size()];
        //폐업 시키지 않을 치킨집을 고른다.
        chickenSelect(0,0);

        System.out.println(minChickenDistance);
    }

    public static void chickenSelect(int start, int count) {
        //치킨 집을 모두 골랐다면, 집에서의 치킨 거리 최소 값을 구해서 더한다.
        if (count == M) {
            calculateChickenDistance();
        }

        //치킨집을 모두 고르지 않았다면, 백트래킹으로 고르러 간다.
        for(int i=start; i<chickenList.size(); i++) {
            chickenChecked[i] = true;
            chickenSelect(i+1, count+1);
            chickenChecked[i] = false;
        }
    }

    public static void calculateChickenDistance() {
        int result = 0;

        //각 집 별로
        for(int i=0; i<homeList.size(); i++) {
            Point home = homeList.get(i);

            //가장 가까운 치킨거리를 구한다.
            int minDistance = Integer.MAX_VALUE;
            for (int j=0; j<chickenList.size(); j++) {
                Point chickenPoint = chickenList.get(j);
                if (chickenChecked[j]) {
                    int dist = Math.abs(home.x - chickenPoint.x) + Math.abs(home.y - chickenPoint.y);
                    minDistance = Math.min(minDistance, dist);
                }
            }

            result += minDistance;
        }

        minChickenDistance = Math.min(result, minChickenDistance);
    }
}

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}