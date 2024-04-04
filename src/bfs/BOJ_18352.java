package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_18352 {

    static int[] visited;
    static List<Integer> answers = new ArrayList<>();
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //전체 도시의 갯수
        N = Integer.parseInt(st.nextToken());
        //연결 선 갯수
        int M = Integer.parseInt(st.nextToken());
        //거리 정보
        int K = Integer.parseInt(st.nextToken());
        //출발 도시 번호
        int X = Integer.parseInt(st.nextToken());

        // 방문 배열을 초기화
        visited = new int[N+1];
        for(int i=0; i<=N; i++){
            visited[i] = -1;
        }

        //지도 그래프 설정
        ArrayList<Integer>[] map = new ArrayList[N+1];
        for (int i = 0; i < N+1; i++) {
            map[i]=new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            map[from].add(to);
        }

        BFS(map, K, X);

        for (int i=0; i< visited.length; i++) {
            if(visited[i]==K) {
                answers.add(i);
            }
        }

        if (answers.isEmpty()) {
            System.out.println(-1);
        }
        else {
            Collections.sort(answers);
            for (Integer answer : answers) {
                System.out.println(answer);
            }
        }

    }

    //K -> 가야할 거리
    //X -> 시작 정보
    public static void BFS(ArrayList<Integer>[] map, int K, int X) {

        Queue<Integer> queue = new LinkedList<>();
        queue.add(X);
        visited[X] = 0;

        while(!queue.isEmpty()) {

            //현재 위치
            Integer current = queue.poll();

            //현재 위치에서 갈 수 있는 다음 도시들
            ArrayList<Integer> nextCities = map[current];

            //인접 위치 확인
            for(int nextCity : nextCities) {
                //방문한 곳이 아니라면, 큐에 저장
                if(visited[nextCity]==-1) {
                    queue.add(nextCity);
                    visited[nextCity] = visited[current]+1;
                }
            }
        }

    }

}
