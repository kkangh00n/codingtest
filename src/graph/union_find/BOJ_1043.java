package graph.union_find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1043 {

    static ArrayList<Integer>[] party;
    static int[] parent;
    static int[] trueP;

    public static void main(String[] args) throws IOException {

        int answer=0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //사람 수
        int n = Integer.parseInt(st.nextToken());
        //파티 수
        int m = Integer.parseInt(st.nextToken());

        //집합 배열
        parent = new int[n+1];
        for (int i = 1; i < n+1; i++) {
            parent[i] = i;
        }


        //진실 아는 사람의 수
        st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        trueP = new int[t];
        for (int i = 0; i < t; i++) {
            trueP[i] = Integer.parseInt(st.nextToken());
        }

        //파티 리스트
        party = new ArrayList[m];

        //파티 리스트 정리
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int partyPeople = Integer.parseInt(st.nextToken());
            party[i] = new ArrayList<>();
            for (int j = 0; j < partyPeople; j++) {
                party[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        //같은 파티에 참가하면, 같은 집합
        for (int i = 0; i < m; i++) {
            Integer firstPeople = party[i].get(0);
            for (int j = 1; j < party[i].size(); j++) {
                union(firstPeople, party[i].get(j));
            }
        }

        //각 파티별로
        for (int i = 0; i < m; i++) {
            boolean isPossible = true;
            Integer current = party[i].get(0);

            //진실을 아는 사람들이 참여했는지 확인
            for (int j = 0; j < trueP.length; j++) {

                //현재 파티의 대표노드와 진실을 아는 사람의 노드가 같다면 거짓말 못함
                if(find(current) == find(trueP[j])) {
                    isPossible = false;
                    break;
                }

            }
            //거짓말을 할 수 있음
            if(isPossible) {
                answer++;
            }
        }
        System.out.println(answer);
    }


    public static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a != b) {
            parent[b] = a;
        }
    }

    public static int find(int x) {
        if(x == parent[x]) {
            return x;
        }

        parent[x] = find(parent[x]);
        return parent[x];
    }
}
