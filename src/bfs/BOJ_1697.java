package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 현재 수빈이 위치 : N
 * 동생의 위치 : K
 * 수빈이가 동생의 위치까지 갈 수 있는 가장 빠른 시간
 *
 * BFS로 풀어야 빠름
 * 1초 -> x-1, x+1, 2*x
 */
public class BOJ_1697 {

    static boolean[] visited = new boolean[100001];
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //현재 수빈이 위치
        int N = Integer.parseInt(st.nextToken());
        //동생의 위치
        int K = Integer.parseInt(st.nextToken());

        BFS(N, K);

        System.out.println(answer);
    }

    public static void BFS(int start, int goal) {
        Queue<Point4> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(new Point4(start, 0));

        while(!queue.isEmpty()) {
            Point4 currentPoint = queue.poll();
            int value = currentPoint.value;
            int time = currentPoint.time;

            if (value == goal) {
                answer = time;
                break;
            }

            visited[value] = true;

            for (Integer next : nextValue(value)) {
                //위치가 범위 밖을 나가지 않도록 함
                if (next<0 || next > 100000) {
                    continue;
                }

                if (!visited[next]) {
                    queue.add(new Point4(next, time+1));
                }
            }
        }
    }

    public static List<Integer> nextValue(int current) {
        return List.of(current-1, current+1, current*2);
    }
}

class Point4{
    int value;
    int time;

    Point4(int value, int time) {
        this.value = value;
        this.time = time;
    }
}
