package graph.union_find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 유니온 파인드
 * <p>
 * 찬솔이가 친분 관계를 이용해서 교육 자료를 전달한다. 찬솔이는 교육 자료를 전달하는 인원이 최소가 되기를 원한다.
 * <p>
 * 풀이 1. 각 집합을 묶어준다. 2. 각 집합의 구성원의 갯수를 센다. -> 각 집합의 자료 전달 경우의 수 3. 각 집합의 구성원의 수를 곱한다.
 */
public class BOJ_24542 {

    static int[] unf;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Map<Integer, Integer> map = new HashMap<>();

        //교육생의 수
        int N = Integer.parseInt(st.nextToken());

        //친분 관계의 수
        int M = Integer.parseInt(st.nextToken());

        unf = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            unf[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int student1 = Integer.parseInt(st.nextToken());
            int student2 = Integer.parseInt(st.nextToken());

            union(student1, student2);
        }

        for (int i = 1; i < unf.length; i++) {
            int root = find(unf[i]);
            Integer value = map.getOrDefault(root, 0);
            map.put(root, value + 1);
        }

        long answer = 1;
        for (Integer value : map.keySet()) {
            answer = (answer * (long) map.get(value)) % 1000000007;
        }

        System.out.println(answer % 1000000007);
    }

    public static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);

        if (fa != fb) {
            unf[fa] = fb;
        }
    }

    public static int find(int a) {
        if (a == unf[a]) {
            return a;
        }

        unf[a] = find(unf[a]);
        return unf[a];
    }
}
