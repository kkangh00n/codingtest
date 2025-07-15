package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 백트래킹
 *
 * 풀이
 * 1. antic 고정 방문 처리
 * 2. K-5 만큼 DFS
 * 3. K-5 만큼 DFS 완료 시, 알파벳이 모두 방문처리 된 단어 갯수 저장
 * 4. 출력
 */
public class BOJ_1062 {

    static int answer = 0;
    static List<String> inputs;

    static boolean[] visited = new boolean[26];

    static char[] fixAlphabet = new char[]{'a', 'n', 't', 'i', 'c'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //배울 수 있는 단어의 갯수
        int N = Integer.parseInt(st.nextToken());
        //배울 수 있는 글자의 갯수
        int K = Integer.parseInt(st.nextToken());

        inputs = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            inputs.add(br.readLine());
        }

        if (K < 5) {
            System.out.println(0);
            return;
        }

        //a,n,t,i,c, 방문 처리
        K-=5;
        for (char alphabet : fixAlphabet) {
            visited[alphabet - 'a'] = true;
        }

        //알파벳 DFS
        DFS(0, 0, K);

        System.out.println(answer);

    }

    static void DFS(int startIndex, int currentDepth, int goalDepth) {

        if (goalDepth == currentDepth) {

            int result = 0;

            //각 단어 확인
            for (String word : inputs) {

                boolean flag = true;
                char[] charArray = word.toCharArray();

                //각 단어의 알파벳 확인
                for (char c : charArray) {
                    //알파벳 중 하나라도 방문하지 않았다면, break
                    if (!visited[c-'a']) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    result++;
                }

            }

            answer = Math.max(answer, result);

            return;
        }

        for (int i = startIndex; i < 26; i++) {

            if (!visited[i]) {
                visited[i] = true;
                DFS(i+1, currentDepth+1, goalDepth);
                visited[i] = false;
            }

        }
    }
}
