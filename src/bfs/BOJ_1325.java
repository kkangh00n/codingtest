package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1325 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //컴퓨터 갯수
        int N = Integer.parseInt(st.nextToken());
        //신뢰 관계 수
        int M = Integer.parseInt(st.nextToken());

        //컴퓨터마다 신뢰관계깊이 저장
        int[] answer = new int[N + 1];

        //관계도 세팅
        ArrayList<Integer>[] map = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            map[i] = new ArrayList<>();
        }

        //관계 세팅
        for (int i = 1; i < M + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            //from이 to를 신뢰 -> to가 해킹당하면 from도 해킹 당함
            map[from].add(to);
        }

        boolean[] visited;
        //컴퓨터 시작 점을 돌아가면서 측정
        for (int j = 1; j <= N; j++) {
            //시작점 세팅
            Queue<Integer> queue = new LinkedList<>();
            queue.add(j);
            //방문 현황 세팅
            visited = new boolean[N + 1];
            //시작점 -> 방문
            visited[j] = true;

            //BFS
            while (!queue.isEmpty()) {
                Integer current = queue.poll();

                for(Integer next : map[current]) {
                    if (!visited[next]) {
                        queue.add(next);
                        visited[next] = true;
                        answer[next]++;
                    }
                }
            }
        }

        int max = Integer.MIN_VALUE;

        //최댓값 찾기
        for(int i=1; i<=N;i++) {
            max = Math.max(max, answer[i]);
        }

        for(int i=1; i <=N ; i++) {
            if(answer[i]==max) {
                System.out.print(i+" ");
            }
        }

    }
}
