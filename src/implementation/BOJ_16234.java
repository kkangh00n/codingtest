package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * N*N 크기의 땅이 있고, 1*1크기의 나라가 존재한다.
 * 인구 이동 조건은 다음과 같다.
 * 1. 국경선을 공유하는 두 나라의 인구 차이가 L명 이상 R명 이하라면, 두 나라가 공유하는 국경선을 오늘 하루 연다.
 * 2. 위의 조건에 의해 국경선을 모두 열게 되면, 인구 이동을 시작한다.
 * 3. 국경선이 열려 이동할 수 있으면, 그 나라들을 연합이라고 한다.
 * 4. 연합의 각 칸의 인구수는 (연합 인구 수/연합 칸의 갯수)이다.
 * 5. 연합을 해체한다.
 *
 * 입력
 * 지도 길이 N, 인구 조건 L R
 * 지도
 *
 * 출력
 * 인구 이동이 발생하는 일 수
 *
 * 풀이
 * 1. 지도의 모든 땅을 돌면서 인접한 나라와 인구 차이가 L 이상 R 이하인지 확인
 * - 이미 방문한 땅이라면, continue
 * - BFS
 * - queue.poll() 할 때, 연합 인구 수 합을 갱신 및 리스트에 좌표 저장
 * 2. 1번 반복
 * - 만약 1번에서 모든 땅을 돌았을 때, 인접한 나라와 인구 차이가 L 이상 R 이하가 없다면 break
 *
 */
public class BOJ_16234 {

    static int N, L, R;
    static int[][] A;
    static boolean[][] visited;
    static Queue<int[]> queue;

    static int[][] moved = new int[][]{{0, 1},{0, -1},{1, 0},{-1, 0}};

    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        A = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //반복적으로 인구 이동을 실시
        while(true) {

            boolean isNotMoved = true;

            int[][] temp = new int[N + 1][N + 1];

            for (int i=1; i<=N; i++) {
                for (int j=1; j<=N; j++) {
                    temp[i][j] = A[i][j];
                }
            }

            movePopulation();

            //인구 이동 후, 이전과 같은지 비교
            for (int i=1; i<=N; i++) {
                for (int j=1; j<=N; j++) {
                    if (temp[i][j] != A[i][j]) {
                        isNotMoved = false;
                    }
                }
            }

            //인구를 이동을 시도했는데, 이전과 같다면 break
            if (isNotMoved) {
                break;
            }

            //인구 이동 시, 날짜 증가
            answer++;
        }

        System.out.println(answer);
    }

    //인구 이동
    public static void movePopulation() {
        visited = new boolean[N+1][N+1];
        queue = new LinkedList<>();

        //지도의 각 좌표를 확인한다.
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                //만약 해당 좌표가 이미 방문되었다면, 넘어간다.
                if (visited[i][j]) {
                    continue;
                }

                //해당 나라를 기준으로 연합국을 BFS로 탐색한다.
                BFS(i, j);
            }
        }
    }

    //연합 탐색
    private static void BFS(int x, int y) {
        visited[x][y] = true;
        queue.add(new int[]{x,y});

        List<int[]> guilds = new ArrayList<>();
        int guildPopulations = 0;

        //해당 나라를 기준으로 사방 확인
        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            //연합 삽입
            guilds.add(current);
            //현재 총 연합국에 인원 갱신
            guildPopulations += A[current[0]][current[1]];

            //해당 나라의 사방 확인
            for (int[] m : moved) {
                int nextX = current[0] + m[0];
                int nextY = current[1] + m[1];

                //인접한 방향의 좌표가 지도를 벗어나면 continue
                if (nextX < 1 || nextX > N || nextY < 1 || nextY > N) {
                    continue;
                }

                //이미 방문했다면 continue
                if (visited[nextX][nextY]) {
                    continue;
                }

                //해당 좌표에서 사방을 확인하여, 인구 수 차이가 L 이상 R 이하인 나라가 존재하는지 확인한다.
                //존재한다면, 해당 나라를 queue에 삽입한다.
                int difference = Math.abs(A[current[0]][current[1]] - A[nextX][nextY]);
                if (difference >= L && difference <= R) {
                    visited[nextX][nextY] = true;
                    queue.add(new int[]{nextX, nextY});
                }
            }
        }

        //모든나라에 대해 연합 및 인구조사가 끝났다.
        //인구 이동을 실시
        for (int i = 0; i < guilds.size(); i++) {
            int[] country = guilds.get(i);
            A[country[0]][country[1]] = guildPopulations/guilds.size();
        }
    }
}
