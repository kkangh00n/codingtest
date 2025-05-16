package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 1. N*N 크기의 시험관이 있다. 바이러스는 1~K
 * 2. 1초마다 각 바이러스는 상하좌우로 증식해 나간다. 만약 어떤 바이러스가 존재하면 그 곳에는 들어갈 수 없다.
 *
 * 입력
 * N 지도 크기, K 바이러스 종류
 * 바이러스의 위치 및 종류
 * 맨 마지막 -> S초  X,Y 위치
 *
 * 출력
 * S초 뒤의 X,Y의 바이러스 종류 출력
 *
 * 풀이
 * 1. 배열 셋팅
 * 2. 배열의 있는 바이러스 우선순위 큐에 삽입
 * 3. 우선순위 큐에 있는 가장 작은 바이러스들 증식
 * 4. 증식된 바이러스의 종류와 위치를 저장
 * 5. 반복
 */
public class BOJ_18405 {

    static int[][] moved = new int[][]{{0, 1},{0, -1},{1, 0},{-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N + 1][N + 1];
        PriorityQueue<Virus> queue = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int v = Integer.parseInt(st.nextToken());
                if (v!=0) {
                    map[i][j] = v;
                    queue.add(new Virus(0, v, i, j));
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        int time = 0;

        while(!queue.isEmpty()) {
            //현재 시간의 virus
            Virus currentVirus = queue.poll();

            //시간이 1초 지났다면
            if (time < currentVirus.time) {
                time = currentVirus.time;
            }

            //현재 시간이 목표 시간보다 1초 지났다면, break
            if (time == S) {
                break;
            }

            //증식
            for (int[] m : moved) {
                int nextX = currentVirus.x + m[0];
                int nextY = currentVirus.y + m[1];

                //지도 바깥이라면
                if (nextX <= 0 || nextX > N || nextY <= 0 || nextY > N) {
                    continue;
                }

                //이미 바이러스가 있다면
                if (map[nextX][nextY] != 0) {
                    continue;
                }

                map[nextX][nextY] = currentVirus.virus;
                queue.add(new Virus(currentVirus.time+1, currentVirus.virus, nextX, nextY));
            }
        }

        System.out.println(map[X][Y]);
    }
}

class Virus implements Comparable<Virus>{
    int time;
    int virus;
    int x;
    int y;

    public Virus(int time, int virus, int x, int y) {
        this.time = time;
        this.virus = virus;
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Virus o) {
        if (this.time == o.time) {
            return this.virus - o.virus;
        }

        return this.time - o.time;
    }
}
