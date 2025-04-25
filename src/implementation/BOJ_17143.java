package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1. 낚시왕이 오른쪽으로 이동한다.
 *      - 해당 열에 상어가 존재한다면 상어를 잡는다.
 *      - 해당 열에 상어가 2마리 이상 존재한다면, 가장 가까운 상어를 잡는다.
 * 2. 상어가 각자에게 주어진 방향과 속력으로 이동한다.
 *
 * 입력
 * R*C격자판 M 상어의 수
 * r,c,s,d,z -> 상어의 위치, s->속력, d->이동방향, z->크기
 * d -> 1 위, 2 아래, 3 오른쪽, 4 왼쪽
 *
 * 출력
 * - 낚시왕이 잡은 상어 크기의 합을 구한다.
 *
 * 풀이
 * 1. 격자판을 초기화한다.
 * 2. 낚시왕이 열에서 C까지 움직인다.
 * 3. 해당 열에 상어가 존재하면, 가장 가까운 상어를 포획한다.
 *      - 해당 상어는 삭제하고, 포획 크기를 더한다.
 * 4. 나머지 상어들은 이동한다.
 *      a. 해당 방향으로 s만큼 이동한다.
 *      b. 만약 벽을 만난다면, 방향을 반대로 바꾸고 나머지 속력만큼 이동한다.
 */
public class BOJ_17143 {

    static int[][] moved = new int[][] {{0,0}, {-1,0},{1,0},{0,1},{0,-1}};

    public static void main(String[] args) throws IOException {

        int answer = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Shark[][] map = new Shark[R + 1][C + 1];

        //1. 격자판을 초기화한다.
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            map[r][c] = new Shark(r,c,s,d,z);
        }

        //2. 낚시왕이 열에서 C까지 움직인다.
        int king = 0;
        while(++king<=C) {
            //3. 해당 열에 상어가 존재하면,
            for (int i = 1; i <= R; i++) {
                //해당 상어는 삭제하고, 포획 크기를 더한다.
                if (map[i][king] != null) {
                    Shark shark = map[i][king];
                    answer += shark.z;
                    map[i][king] = null;
                    break;
                }
            }

            //4. 나머지 상어들은 이동한다.
            //상어들을 큐에 삽입한다.
            Queue<Shark> queue = new LinkedList<>();
            for (int i = 1; i <= R; i++) {
                for (int j = 1; j <= C; j++) {
                    if (map[i][j] != null) {
                        queue.add(map[i][j]);
                        map[i][j] = null;
                    }
                }
            }

            while(!queue.isEmpty()) {
                Shark shark = queue.poll();
                int r = shark.r;
                int c = shark.c;
                int s = shark.s;
                int d = shark.d;
                int[] m = moved[d];

                //주기만큼 반복되는 이동을 줄인다.
                if (d == 1 || d == 2) {
                    s%=(R-1)*2;
                }
                else {
                    s%=(C-1)*2;
                }

                //해당 방향으로 s만큼 이동한다.
                while(s-->0) {

                    int nextR = r + m[0];
                    int nextC = c + m[1];

                    //만약 진행 방향이 벽이라면,
                    if (nextR > R || nextR <= 0 || nextC > C || nextC <= 0) {
                        //방향을 반대로 바꾸어 이동한다.
                        if (d%2==0) {
                            d--;
                        }
                        else {
                            d++;
                        }

                        m = moved[d];

                        nextR = r + m[0];
                        nextC = c + m[1];
                    }

                    r = nextR;
                    c = nextC;
                }

                shark.r = r;
                shark.c = c;
                shark.d = d;

                if (map[r][c] != null) {
                    if (map[r][c].z > shark.z) {
                        continue;
                    }
                }

                map[r][c] = shark;
            }
        }

        System.out.println(answer);
    }
}

class Shark{
    int r;
    int c;
    int s;
    int d;
    int z;

    Shark(int r, int c, int s, int d, int z) {
        this.r = r;
        this.c = c;
        this.s = s;
        this.d = d;
        this.z = z;
    }
}
