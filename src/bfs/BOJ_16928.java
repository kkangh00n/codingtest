package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제
 * 1. 1부터 100까지 적힌 100개의 칸 보드판에서 게임이 진행
 * 2. 주사위를 통해 나온 칸만큼 이동
 * 3. 도착한 칸에 사다리가 있다면, 사다리를 타고 위로 올라간다. (숫자가 올라간다.)
 * 4. 도착한 칸에 뱀이 있다면, 뱀을 따라서 아래로 내려간다. (숫자가 낮아진다.)
 * 5. 100번 칸에 도착하기 위해 주사위를 굴려야 하는 횟수의 최솟값을 구하자.
 *
 * 입력
 * 사다리 수, 뱀의 수
 * 사다리 정보 x,y
 * 뱀의 정보 u,v
 */
public class BOJ_16928 {

    static Map<Integer, Integer> ladders;
    static Map<Integer, Integer> snakes;

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //사다리 수
        int N = Integer.parseInt(st.nextToken());

        ladders = new HashMap<>();

        //뱀의 수
        int M = Integer.parseInt(st.nextToken());

        snakes = new HashMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            ladders.put(from, to);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            snakes.put(from, to);
        }

        BFS();

        System.out.println(answer);
    }

    static void BFS() {

        Queue<X> queue = new LinkedList<>();
        boolean[] visited = new boolean[101];

        int start = 1;
        queue.add(new X(start, 0));
        visited[start] = true;

        while(!queue.isEmpty()) {
            X current = queue.poll();

            //만약 100에 딱 도착할 경우,
            //정답 저장 후 종료
            if (current.index == 100) {
                answer = current.count;
                break;
            }

            for (int i = 1; i <= 6; i++) {
                int next = current.index + i;

                //다음 값이 100을 넘어갈 경우, 생략
                if (next > 100) {
                    continue;
                }

                //다음 값이 뱀 또는 사다리가 있을 경우, 값 이동
                if (ladders.containsKey(next)) {
                    next = ladders.get(next);
                }

                if (snakes.containsKey(next)) {
                    next = snakes.get(next);
                }

                if (!visited[next]) {
                    queue.add(new X(next, current.count+1));
                    visited[next] = true;
                }
            }
        }
    }
}

class X {
    //위치
    int index;
    //던진 횟수
    int count;

    public X(int index, int count) {
        this.index = index;
        this.count = count;
    }
}
