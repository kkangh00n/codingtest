package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 1. 친구와 친구 관계 수가 주어진다.
 * 2. 친구 관계가 주어진다.
 *
 * 풀이
 * 1. 친구 관계를 양방향 인접 리스트로 선언
 * 2. DFS를 통해 모든 친구를 시작점으로 해서 탐색
 * 3. 만약 탐색 깊이가 4가 되면 존재
 * 4. 찾지 못하면 존재하지 않음
 */
public class BOJ_13023 {

    static ArrayList<Integer>[] friends;
    static boolean[] visited;

    static boolean isExists = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        visited = new boolean[N];
        friends = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            friends[i] = new ArrayList<>();
        }

        //친구 관계 셋팅
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int friend1 = Integer.parseInt(st.nextToken());
            int friend2 = Integer.parseInt(st.nextToken());

            friends[friend1].add(friend2);
            friends[friend2].add(friend1);
        }

        //각 친구 별로 탐색
        for (int i = 0; i < N; i++) {
            DFS(i, 1);

            if (isExists) {
                System.out.println(1);
                return;
            }
        }

        System.out.println(0);
    }

    public static void DFS(int currentFriend, int depth) {
        if (depth == 5) {
            isExists = true;
            return;
        }

        visited[currentFriend] = true;
        for (Integer nextFriend : friends[currentFriend]) {

            if (!visited[nextFriend]) {
                DFS(nextFriend, depth + 1);
            }

            if (isExists) {
                return;
            }
        }
        visited[currentFriend] = false;
    }
}
